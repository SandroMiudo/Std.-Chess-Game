package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lauefer extends Figur{

    private int posX;
    private int posY;
    private Farbe farbe;
    private BufferedImage image;

    public Lauefer(int posX, int posY, Farbe farbe) {
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
        else if(felder[newPosY][newPosX] != null){
            if(!checkSameColor(posX,posY,newPosX,newPosY,feld)){
                felder[newPosY][newPosX].setAlive();
                felder[newPosY][newPosX] = this;
                felder[posY][posX] = null;
                posY = newPosY;
                posX = newPosX;
            }
        }
    }

    public boolean checkPosition(int newPosX,int newPosY){
        int currentPos_Y = posY;
        int inc = 1;
        int dec = -1;
        for(int i = posX+1; i < 8; i++){
            if(i == newPosX && currentPos_Y+dec == newPosY){
                return true;
            }
            else if(i == newPosX && currentPos_Y+inc == newPosY){
                return true;
            }
            dec--;
            inc++;
        }
        dec = -1;
        inc = 1;
        for(int i = posX-1; i >= 0; i--){
            if(i == newPosX && currentPos_Y+dec == newPosY){
                return true;
            }
            else if(i == newPosX && currentPos_Y+inc == newPosY){
                return true;
            }
            dec--;
            inc++;
        }
        System.out.println("position false");
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
        return this.farbe;
    }

    @Override
    public void loadImage() {
        String str = "";
        if(farbe == Farbe.WHITE){
            str += "bischopW.png";
        }
        else{
            str += "bischopB.png";
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
