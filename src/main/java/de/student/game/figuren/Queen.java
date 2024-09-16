package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Queen extends Figur{

    private int posX;
    private int posY;
    private final Farbe farbe;
    private BufferedImage image;

    public Queen(int posX , int posY,Farbe farbe) {
        this.posX = posX;
        this.posY = posY;
        this.farbe = farbe;
        loadImage();
    }

    @Override
    public void move(int newPosX, int newPosY, Feld feld) {
        if(!checkPos(newPosX,newPosY)){
            return;
        }
        if(!checkNoJumps(posX,posY,newPosX,newPosY,feld)){
            return;
        }
        Figur[][] felder = feld.getFelder();
        if(felder[newPosY][newPosX] == null){
            felder[newPosY][newPosX] = this;
            felder[posY][posX] = null;
            posX = newPosX;
            posY = newPosY;
        }
        else{
            if(!checkSameColor(posX,posY,newPosX,newPosY,feld)){
                felder[newPosY][newPosX].setAlive();
                felder[newPosY][newPosX] = this;
                felder[posY][posX] = null;
                posX = newPosX;
                posY = newPosY;
            }
        }
    }

    public boolean checkPos(int newPosX, int newPosY){
        boolean posLauefer = new Lauefer(this.posX, this.posY, this.farbe).checkPosition(newPosX, newPosY);
        if(posLauefer || newPosX == posX && newPosY != posY || newPosY == posY && newPosX != posX){
            return true;
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
            str += "queenW.png";
        }
        else{
            str += "queenB.png";
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

    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
