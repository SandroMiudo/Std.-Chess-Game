package de.student.game.spielfeld;

import de.student.game.figuren.Figur;

import java.util.List;

public class Feld {
    private Figur[][] felder = new Figur[8][8];

    public void initFeld(List<Figur> figuren){
        for(Figur figur : figuren){
            felder[figur.getPosY()][figur.getPosX()] = figur;
        }
    }

    public Figur[][] getFelder() {
        return felder;
    }
}
