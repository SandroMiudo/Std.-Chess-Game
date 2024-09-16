package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import java.awt.image.BufferedImage;

public abstract class Figur{

    public abstract void move(int posX, int poxY, Feld feld);

    public abstract boolean attack(int posX,int posY,Feld feld);

    public abstract int getPosX();

    public abstract int getPosY();

    public abstract Farbe getFarbe();

    public abstract void loadImage();

    public boolean checkSameColor(int posX, int posY,int newPosX , int newPosY , Feld feld){
        Figur[][] felder = feld.getFelder();
        Figur figur = felder[posY][posX];
        Figur figurTarget = felder[newPosY][newPosX];
        if(figur.getFarbe() == figurTarget.getFarbe()){
            return true;
        }
        return false;
    }

    // pruefe das eine Figur nicht über andere Figuren springen kann
    public boolean checkNoJumps(int posX, int posY, int newPosX, int newPosY, Feld feld){
        Figur[][] felder = feld.getFelder();
        // prüfe zunächste waagerecht und horizontal
        if(newPosX == posX || newPosY == posY){
            if(newPosX == posX){
                if(newPosY < posY){
                    for(int i = posY-1; i > newPosY ; i--){
                        if(felder[i][newPosX] != null){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = posY+1; i < newPosY; i++){
                        if(felder[i][newPosX] != null){
                            return false;
                        }
                    }
                }
            }
            if(newPosY == posY){
                if(newPosX < posX){
                    for(int i = posX-1; i > newPosX; i--){
                        if(felder[newPosY][i] != null){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = posX+1; i < newPosX; i++){
                        if(felder[newPosY][i] != null){
                            return false;
                        }
                    }
                }
            }
        }
        // prüfe schräge jumps
        return checkSchraeg(posX, posY, newPosX, newPosY, feld);

    }

    private boolean checkSchraeg(int posX, int posY , int newPosX, int newPosY, Feld feld){     // Methode muss überarbeitet werden : sind noch bugs drinnen
        Figur[][] felder = feld.getFelder();
        if(newPosX != posX && newPosY != posY){
            int inc = 1;
            int dec = -1;
            if(newPosX > posX){
                for(int i = posX+1; i < newPosX; i++){
                    if(newPosY > posY){
                        if(posY+inc < 8 && felder[posY+inc][i] != null){
                            return false;
                        }
                    }
                    else{
                        if(posY+dec >= 0 && felder[posY+dec][i] != null){
                            return false;
                        }
                    }
                    dec--;
                    inc++;
                }
            }
            dec = -1;
            inc = 1;
            if(newPosX < posX){
                for(int i = posX-1; i > newPosX; i--){
                    if(newPosY > posY){
                        if(posY+inc < 8 && felder[posY+inc][i] != null){
                            return false;
                        }
                    }
                    else {
                        if(posY+dec >= 0 && felder[posY+dec][i] != null){
                            return false;
                        }
                    }
                    dec--;
                    inc++;
                }
            }
        }
        return true;
    }

    public abstract void setAlive();

    public abstract BufferedImage getImage();

}
