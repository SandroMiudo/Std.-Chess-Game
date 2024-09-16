package de.student.game.figuren;

import de.student.game.figuren.*;

import java.util.ArrayList;
import java.util.List;

public class Spielfiguren {
    private List<Figur> figuren = new ArrayList<>();

    public void setFiguren(){
        addBauern();
        addTurm();
        addPferd();
        addLauefer();
        addKing();
        addQueen();
    }

    private void addBauern(){
        int i = 1;
        for(int j = 0; j < 8; j++){
            figuren.add(new Bauer(j,i,Farbe.WHITE));
        }
        i = 6;
        for(int j = 0; j < 8; j++){
            figuren.add(new Bauer(j,i,Farbe.BLACK));
        }
    }

    private void addTurm(){
       figuren.add(new Turm(0,7,Farbe.BLACK));
       figuren.add(new Turm(7,7,Farbe.BLACK));
       figuren.add(new Turm(0,0,Farbe.WHITE));
       figuren.add(new Turm(7,0,Farbe.WHITE));
    }

    private void addPferd(){
        figuren.add(new Pferd(1,0,Farbe.WHITE));
        figuren.add(new Pferd(6,0,Farbe.WHITE));
        figuren.add(new Pferd(1,7,Farbe.BLACK));
        figuren.add(new Pferd(6,7,Farbe.BLACK));
    }

    private void addLauefer(){
        figuren.add(new Lauefer(2,0,Farbe.WHITE));
        figuren.add(new Lauefer(5,0,Farbe.WHITE));
        figuren.add(new Lauefer(2,7,Farbe.BLACK));
        figuren.add(new Lauefer(5,7,Farbe.BLACK));
    }

    private void addKing(){
        figuren.add(new King(3,0,Farbe.WHITE));
        figuren.add(new King(4,7,Farbe.BLACK));
    }

    private void addQueen(){
        figuren.add(new Queen(4,0,Farbe.WHITE));
        figuren.add(new Queen(3,7,Farbe.BLACK));
    }

    public List<Figur> getFiguren() {
        return figuren;
    }
}
