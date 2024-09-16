package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bauer extends Figur{

    private boolean isAlive = true;
    private boolean startPos = true;
    private int posX;
    private int posY;
    private Farbe farbe;
    private BufferedImage image;

    public Bauer(int posX, int posY,Farbe farbe){
        this.posX = posX;
        this.posY = posY;
        this.farbe = farbe;
        loadImage();
    }

    @Override
    public void move(int newPosX, int newPosY, Feld feld){
        Figur[][] felder = feld.getFelder();
        if(felder[newPosY][newPosX] == null){
            if(startPos){
                if(newPosX == this.posX){
                    if(newPosY == this.posY-1 || newPosY == this.posY-2){
                        felder[posY][posX] = null;
                        felder[newPosY][newPosX] = this;
                        posY = newPosY;
                        startPos = false;
                        return;
                    }
                }
            }
            else{
                if(newPosY == this.posY-1 && newPosX == this.posX){
                    felder[posY][posX] = null;
                    felder[newPosY][newPosX] = this;
                    posY = newPosY;
                    return;
                }
            }

        }
        if(felder[newPosY][newPosX] != null){
            if(attack(newPosX,newPosY,feld)){
                felder[posY][posX] = null;
                felder[newPosY][newPosX].setAlive();
                felder[newPosY][newPosX] = this;
                posX = newPosX;
                posY = newPosY;
            }
        }
        checkIfBauerIsOnOtherSide(feld);
    }

    // Figur muss schon gemoved sein um diese Methode aufrufen zu können !!!
    private void checkIfBauerIsOnOtherSide(Feld feld){
        Figur[][] felder = feld.getFelder();
        if(farbe == Farbe.BLACK){
            if(posY == 0){
                felder[posY][posX] = new Queen(posX,posY,Farbe.BLACK);
            }
        }
        else if(farbe == Farbe.WHITE){
            if(posY == 7){
                felder[posY][posX] = new Queen(posX,posY,Farbe.WHITE);
            }
        }
    }

    @Override
    public boolean attack(int newPosX, int newPosY,Feld feld) {
        if(this.farbe == Farbe.BLACK){
            if(posY-1 == newPosY && posX != newPosX && !checkSameColor(posX,posY,newPosX,newPosY,feld)){
                return true;
            }
        }
        if(this.farbe == Farbe.WHITE){
            return posY + 1 == newPosY && posX != newPosX && !checkSameColor(posX, posY, newPosX, newPosY, feld);
        }
        return false;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public Farbe getFarbe() {
        return this.farbe;
    }

    @Override
    public void loadImage() {
        String str = "";
        if(farbe == Farbe.WHITE){
            str += "bauerW.png";
        }
        else{
            str += "bauerB.png";
        }
        File file = new File("src/main/resources/ChessFigures/" + str);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAlive(){
        isAlive = false;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    private boolean otherSide(){
return true;
    }
}
