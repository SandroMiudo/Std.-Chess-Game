package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pferd extends Figur{

    private int posX;
    private int posY;
    private final Farbe farbe;
    private boolean isAlive = true;
    private BufferedImage image;

    public Pferd(int posX , int posY, Farbe farbe) {
        this.posX = posX;
        this.posY = posY;
        this.farbe = farbe;
        loadImage();
    }

    @Override
    public void move(int newPosX, int newPosY, Feld feld) {
        if(!checkPosition(newPosX,newPosY)){
            return;
        }
        Figur[][] felder = feld.getFelder();
        if(felder[newPosY][newPosX] == null){
            felder[newPosY][newPosX] = this;
            felder[this.posY][this.posX] = null;
            posX = newPosX;
            posY = newPosY;
        }
        else{
            if(!checkSameColor(this.posX,this.posY,newPosX,newPosY,feld)){
                felder[newPosY][newPosX].setAlive();
                felder[newPosY][newPosX] = this;
                felder[posY][posX] = null;
                posX = newPosX;
                posY = newPosY;
            }
        }
    }

    public boolean checkPosition(int newPosX, int newPosY){
       if(this.posX+1 == newPosX || this.posX-1 == newPosX){
           if(this.posY+2 == newPosY){
                return true;
           }
           else if(this.posY-2 == newPosY){
               return true;
           }
       }
       else if(this.posX+2 == newPosX || this.posX-2 == newPosX){
           if(this.posY+1 == newPosY){
               return true;
           }
           else if(this.posY-1 == newPosY){
               return true;
           }
       }
       return false;
    }

    @Override
    public boolean attack(int posX, int posY, Feld feld) {
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
        return farbe;
    }

    @Override
    public void loadImage() {
        String str = "";
        if(farbe == Farbe.WHITE){
            str += "pferdW.png";
        }
        else{
            str += "pferdB.png";
        }
        File file = new File("src/main/resources/ChessFigures/" + str);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAlive() {
        isAlive = false;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
