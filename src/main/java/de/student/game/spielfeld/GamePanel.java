package de.student.game.spielfeld;

import de.student.game.figuren.Farbe;
import de.student.game.figuren.Figur;
import de.student.game.figuren.King;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel{
    private final Feld feld;
    private King king_White;
    private King king_Black;
    private int king_Counter = 0;

    public GamePanel(Feld feld){
        this.feld = feld;
        setProperties();
    }

    private void setProperties(){
        this.setLayout(null);
        this.setBounds(0,0,900,900);
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintFeld(g);
        paintBORDER(g);
        paintStrings(g);
        paintFiguren(g);
    }

    private void paintFeld(Graphics g){
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        g.setColor(Color.WHITE);
                    }
                    else{
                        g.setColor(Color.LIGHT_GRAY);
                    }
                }
                else{
                    if(j % 2 == 0){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                }
                g.fillRect(j*100,i*100,100,100);
            }
        }
    }

    private void paintFiguren(Graphics graphics) {
        Figur[][] felder = feld.getFelder();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(felder[i][j] == null){
                    continue;
                }
                Figur figur = felder[i][j];
                if(king_Counter < 2){
                    if(figur.getClass() == King.class){
                        if(figur.getFarbe() == Farbe.BLACK){
                            king_Black = (King) figur;
                        }
                        else{
                            king_White = (King) figur;
                        }
                        king_Counter++;
                    }
                }
                BufferedImage image = figur.getImage();
                graphics.drawImage(image,figur.getPosX()*100,figur.getPosY()*100,null);
            }
        }
    }

    private void paintBORDER(Graphics graphics){
        graphics.setColor(Color.PINK);
        for(int i = 0; i < 800; i += 100){
            graphics.drawRect(i,800,100,50);
            graphics.drawRect(800,i,50,100);
        }
    }

    private void paintStrings(Graphics graphics){
        String str = "01234567";
        String str2 = "ABCDEFGH";
        int counter = 0;
        graphics.setColor(Color.PINK);
        graphics.setFont(new Font("",Font.BOLD,25));
        for(int i = 0; i < 800; i += 100){
            graphics.drawString(""+str.charAt(counter),i+40,830);
            graphics.drawString(""+str2.charAt(counter),815,i+50);
            counter++;
        }
    }

    public void moveFigur(int posX, int posY, int posX_Target, int posY_Target) {
        Figur[][] felder = feld.getFelder();
        if(felder[posY][posX] == null){
            return;
        }
        Figur figur = felder[posY][posX];
        figur.move(posX_Target,posY_Target,feld);
        if(king_White.isMate(feld)){
            // Black Wins
            System.out.println("White wins");
        }
        if(king_Black.isMate(feld)){
            // White Wins
            System.out.println("Black wins");
        }
    }
}
