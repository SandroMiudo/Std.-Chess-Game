package de.student.game;

import de.student.game.events.ActionListenerImpl;
import de.student.game.figuren.Figur;
import de.student.game.figuren.Spielfiguren;
import de.student.game.mapper.MapInput;
import de.student.game.spielfeld.Feld;
import de.student.game.spielfeld.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Game {

    private GamePanel panel;
    private boolean gameIsOver = false;
    private JFrame jFrame = new JFrame();
    private Timer timer = new Timer(0,new ActionListenerImpl(jFrame));

    public Game(GamePanel panel) {
        this.panel = panel;
        setProperties();
    }

    private void setProperties(){
        jFrame.setSize(850,875);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(Color.DARK_GRAY);
        jFrame.add(panel);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public void run(Scanner scanner){
        while(!gameIsOver){
            String startPos = scanner.next();
            String targetPos = scanner.next();
            String translatedStartInput = MapInput.map(startPos);
            String translatedTargetInput = MapInput.map(targetPos);
            validateInput(translatedStartInput,translatedTargetInput);
        }
    }

    private void validateInput(String t1, String t2){
        String[] startInput = t1.split("");
        int posY = Integer.parseInt(startInput[0]);
        int posX = Integer.parseInt(startInput[1]);
        String[] targetInput = t2.split("");
        int posY_Target = Integer.parseInt(targetInput[0]);
        int posX_Target = Integer.parseInt(targetInput[1]);
        panel.moveFigur(posX,posY,posX_Target,posY_Target);
        timer.start();
        if(gameIsOver){
            jFrame.getContentPane().removeAll();
        }
    }

    public static void main(String[] args) {
        Spielfiguren spielfiguren = new Spielfiguren();
        spielfiguren.setFiguren();
        List<Figur> figuren = spielfiguren.getFiguren();
        Feld feld = new Feld();
        feld.initFeld(figuren);
        GamePanel gamePanel = new GamePanel(feld);
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(gamePanel);
        game.run(scanner);
    }
}
