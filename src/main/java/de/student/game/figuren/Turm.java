package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Turm extends Figur{

    private boolean isAlive = true;
    private int posX;
    private int posY;
    private final Farbe farbe;
    private BufferedImage image;

    public Turm(int posX, int posY, Farbe farbe) {
        this.posX = posX;
        this.posY = posY;
        this.farbe = farbe;
        loadImage();
    }

    @Override
    public void move(int newPosX, int newPosY, Feld feld) {
        Figur[][] felder = feld.getFelder();
        if(!checkNoJumps(posX,posY,newPosX,newPosY,feld)){
            return;
        }

        if(newPosX == this.posX || newPosY == this.posY){
            if(felder[newPosY][newPosX] == null){
                felder[newPosY][newPosX] = this;
                felder[posY][posX] = null;
                this.posY = newPosY;
            }
            else{
                if(!checkSameColor(this.posX,this.posY,newPosX,newPosY,feld)){
                    felder[newPosY][newPosX].setAlive();
                    System.out.println(felder[newPosY][newPosX].getFarbe());
                    felder[newPosY][newPosX] = this;
                    felder[posY][posX] = null;
                    this.posX = newPosX;
                    this.posY = newPosY;
                    System.out.println(this.getFarbe());
                }
            }
        }
    }

    @Override
    public boolean attack(int posX, int posY, Feld feld) {
        return true;
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
            str += "turmW.png";
        }
        else{
            str += "turmB.png";
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
