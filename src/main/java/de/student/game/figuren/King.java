package de.student.game.figuren;

import de.student.game.spielfeld.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class King extends Figur{

    private boolean isMate = false;
    private int posX;
    private int posY;
    private final Farbe farbe;
    private BufferedImage image;

    public King(int posX , int posY, Farbe farbe) {
        this.posX = posX;
        this.posY = posY;
        this.farbe = farbe;
        loadImage();
    }

    @Override
    public void move(int newPosX, int newPosY, Feld feld) {
        if(!checkPosition(newPosX, newPosY)){
            return;
        }

        Figur[][] felder = feld.getFelder();
        if(felder[newPosY][newPosX] == null){
            if(!isNotAllowedPosition(newPosX,newPosY,feld)) {
                felder[newPosY][newPosX] = this;
                felder[posY][posX] = null;
                posX = newPosX;
                posY = newPosY;
            }
        }
        else if(felder[newPosY][newPosX] != null){
            if(!checkSameColor(posX,posY,newPosX,newPosY,feld)){
                if(!isNotAllowedPosition(newPosX,newPosY,feld)) {
                    felder[newPosY][newPosX].setAlive();
                    felder[newPosY][newPosX] = this;
                    felder[posY][posX] = null;
                    posX = newPosX;
                    posY = newPosY;
                }
            }
        }
    }

    public boolean checkPosition(int newPosX,int newPosY){
        if(this.posX+1 == newPosX){
            if(this.posY == newPosY){
                return true;
            }
            else if(this.posY == newPosY-1){
                return true;
            }
            else return this.posY == newPosY + 1;
        }
        else if(this.posX-1 == newPosX){
            if(this.posY == newPosY){
                return true;
            }
            if(this.posY == newPosY-1){
                return true;
            }
            return this.posY == newPosY + 1;

        }
        else if(this.posY+1 == newPosY){
            return this.posX == newPosX;
        }
        else if(this.posY-1 == newPosY){
            return this.posX == newPosX;
        }
        return false;
    }

    public boolean isMate(Feld feld){
        if(posY == 0 && posX <= 0){
            // Randfälle
            return isNotAllowedPosition(posX+1,posY,feld) && isNotAllowedPosition(posX,posY+1,feld) &&
            isNotAllowedPosition(posX+1,posY+1,feld);
        }
        if(posY == 0 && posX >= 7){
            return isNotAllowedPosition(posX-1,posY,feld) && isNotAllowedPosition(posX,posY+1,feld) &&
                    isNotAllowedPosition(posX-1,posY+1,feld);
        }
        if(posY == 7 && posX >= 7){
            return isNotAllowedPosition(posX-1,posY,feld) && isNotAllowedPosition(posX,posY-1,feld) &&
                    isNotAllowedPosition(posX-1,posY-1,feld);
        }
        if(posY == 7 && posX <= 0){
            return isNotAllowedPosition(posX+1,posY,feld) && isNotAllowedPosition(posX,posY-1,feld) &&
                    isNotAllowedPosition(posX+1,posY-1,feld);
        }
        if(posY == 0){
            return isNotAllowedPosition(posX+1,posY,feld) && isNotAllowedPosition(posX,posY+1,feld) &&
                    isNotAllowedPosition(posX+1,posY+1,feld) &&
                    isNotAllowedPosition(posX-1,posY,feld) &&
                    isNotAllowedPosition(posX-1,posY+1,feld);
        }
        if(posY == 7){
            return isNotAllowedPosition(posX+1,posY,feld) && isNotAllowedPosition(posX,posY-1,feld) &&
                    isNotAllowedPosition(posX+1,posY-1,feld) &&
                    isNotAllowedPosition(posX-1,posY,feld) &&
                    isNotAllowedPosition(posX-1,posY-1,feld);
        }

        if(posX == 0){
            return isNotAllowedPosition(posX+1,posY,feld) && isNotAllowedPosition(posX,posY+1,feld)
                    && isNotAllowedPosition(posX,posY-1,feld) && isNotAllowedPosition(posX+1,posY-1,feld)
                    && isNotAllowedPosition(posX+1,posY+1,feld);
        }
        if(posX == 7){
            return isNotAllowedPosition(posX-1,posY,feld) && isNotAllowedPosition(posX,posY+1,feld)
                    && isNotAllowedPosition(posX,posY-1,feld) && isNotAllowedPosition(posX-1,posY-1,feld)
                    && isNotAllowedPosition(posX-1,posY+1,feld);
        }
        return isNotAllowedPosition(posX,posY+1,feld)
        && isNotAllowedPosition(posX+1,posY-1,feld)
        && isNotAllowedPosition(posX-1,posY-1,feld)
        && isNotAllowedPosition(posX-1,posY,feld) && isNotAllowedPosition(posX+1,posY,feld)
        && isNotAllowedPosition(posX,posY+1,feld) && isNotAllowedPosition(posX-1,posY+1,feld)
        && isNotAllowedPosition(posX+1,posY+1,feld);
    }

    // also have to look if a current piece is at the spot -> if so the king can't move to that direction!!!

    public boolean isNotAllowedPosition(int newPosX, int newPosY, Feld feld){
        boolean b = checkMateByTurm(newPosX, newPosY, feld) || checkMateByPferd(newPosX, newPosY, feld)
                || checkMateByBischop(newPosX, newPosY, feld);
        System.out.println(b);
        return b;
    }

    private boolean checkMateByTurm(int newPosX, int newPosY,Feld f) {
        Figur[][] felder = f.getFelder();

        for (int i = newPosX; i < 8; i++) {   // ->
            if (felder[newPosY][i] != null && !checkColorHorizontal(i, newPosY, f)) {
                if (felder[newPosY][i].getClass() == Queen.class || felder[newPosY][i].getClass() == Turm.class) {
                    return true;
                }
            }
        }
        for (int i = newPosX; i >= 0; i--) {  // <-
            if (felder[newPosY][i] != null && !checkColorHorizontal(i, newPosY, f)) {
                if (felder[newPosY][i].getClass() == Queen.class || felder[newPosY][i].getClass() == Turm.class) {
                    return true;
                }
            }
        }
        for (int i = newPosY; i < 8; i++) {
            if (felder[i][newPosX] != null && !checkColorVertical(newPosX, i, f)) {
                if (felder[i][newPosX].getClass() == Queen.class || felder[i][newPosX].getClass() == Turm.class) {
                    return true;
                }
            }
        }
        for (int i = newPosY; i >= 0; i--) {
            if (felder[i][newPosX] != null && !checkColorVertical(newPosX, i, f)) {
                if (felder[i][newPosX].getClass() == Queen.class || felder[i][newPosX].getClass() == Turm.class) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColorVertical(int newPosX, int i, Feld f){
       return checkSameColor(posX, posY, newPosX, i, f);
    }

    private boolean checkColorHorizontal(int i, int newPosY, Feld f){
        return checkSameColor(posX, posY, i, newPosY, f);
    }

    private boolean checkMateByBischop(int newPosX, int newPosY, Feld feld){
        int dec = -1;
        int inc = 1;
        for (int i = newPosX+1; i < 8; i++){
            if(checkPosition(feld,i,newPosY,dec,inc)){
                return true;
            }
            inc++;
            dec--;
        }
        dec = -1;
        inc = 1;
        for(int i = newPosX-1; i >= 0; i--){
            if(checkPosition(feld,i,newPosY,dec,inc)){
                return true;
            }
            inc++;
            dec--;
        }
        return false;
    }

    private boolean checkPosition(Feld feld, int i, int newPosY, int dec, int inc){
        Figur[][] felder = feld.getFelder();
        if(newPosY+dec >= 0 && felder[newPosY+dec][i] != null){
            if(!checkSameColor(posX,posY,i,newPosY+dec,feld)){
                if(felder[newPosY + dec][i].getClass() == Queen.class ||
                        felder[newPosY + dec][i].getClass() == Lauefer.class){
                    return true;
                }
            }
        }
        if(newPosY+inc <= 7 && felder[newPosY+inc][i] != null){
            if(!checkSameColor(posX,posY,i,newPosY+inc,feld)){
                return felder[newPosY + inc][i].getClass() == Queen.class ||
                        felder[newPosY + inc][i].getClass() == Lauefer.class;
            }
        }
        return false;
    }

    private boolean checkMateByPferd(int newPosX, int newPosY, Feld feld){
        return check_2Y_1X(feld, newPosY, newPosX) && check_2X_1Y(feld, newPosX, newPosY);
    }

    private boolean check_2Y_1X(Feld feld,int newPosY, int newPosX){
        Figur[][] felder = feld.getFelder();
        if(newPosY >= 2 && newPosY < 6) {
            if (newPosX >= 1 && newPosX < 7) {
                if (felder[newPosY-2][newPosX-1] != null && felder[newPosY-2][newPosX-1].getClass() == Pferd.class) {
                    if(!checkSameColor(posX, posY, newPosX - 1, newPosY - 2, feld)){
                        return true;
                    }
                }
                if (felder[newPosY-2][newPosX+1] != null && felder[newPosY-2][newPosX-1].getClass() == Pferd.class) {
                    if(!checkSameColor(posX, posY, newPosX + 1, newPosY - 2, feld)){
                        return true;
                    }
                }
                if(felder[newPosY+2][newPosX+1] != null && felder[newPosY+2][newPosX+1].getClass() == Pferd.class){
                    if(!checkSameColor(posX,posY,newPosX+1,newPosY+2,feld)){
                        return true;
                    }
                }
                if (felder[newPosY+2][newPosX-1] != null && felder[newPosY+2][newPosX-1].getClass() == Pferd.class){
                    return !checkSameColor(posX, posY, newPosX - 1, newPosY + 2, feld);
                }
            }
        }
        return false;
    }

    private boolean check_2X_1Y(Feld feld, int newPosX , int newPosY){
        Figur[][] felder = feld.getFelder();
        if(newPosX >= 2 && newPosX < 6){
            if(newPosY >= 1 && newPosY < 7){
                if (felder[newPosY-1][newPosX-2] != null && felder[newPosY-1][newPosX-2].getClass() == Pferd.class) {
                    if(!checkSameColor(posX, posY, newPosX - 2, newPosY - 1, feld)){
                        return true;
                    }
                }
                if (felder[newPosY-1][newPosX+2] != null && felder[newPosY-1][newPosX+2].getClass() == Pferd.class) {
                    if(!checkSameColor(posX, posY, newPosX + 2, newPosY - 1, feld)){
                        return true;
                    }
                }
                if(felder[newPosY+1][newPosX+2] != null && felder[newPosY-1][newPosX+2].getClass() == Pferd.class){
                    if(!checkSameColor(posX, posY, newPosX + 2, newPosY + 1, feld)){
                        return true;
                    }
                }
                if(felder[newPosY+1][newPosX-2] != null && felder[newPosY-1][newPosX+2].getClass() == Pferd.class){
                    return checkSameColor(posX, posY, newPosX - 2, newPosY + 1, feld);
                }
            }
        }
        return false;
    }

    private boolean checkMateByBauer(Feld feld, int newPosX, int newPosY) {
        // check white Bauer
        // Bauer sind Min. PosY = 1 und können wir zur 7 vorgehen

        Figur[][] felder = feld.getFelder();

        // check != null than allow Zugriff sonst gibt es eine NullPointer
        if (newPosX >= 1 && newPosY >= 1 && felder[newPosY - 1][newPosX - 1].getClass() == Bauer.class &&
                felder[newPosY - 1][newPosX - 1].getFarbe() == Farbe.WHITE) {
            if (!checkSameColor(posX, posY, newPosX - 1, newPosY - 1, feld)) {
                return true;
            }
        } else if (newPosX <= 6 && newPosY >= 1 && felder[newPosY - 1][newPosX + 1].getClass() == Bauer.class &&
                felder[newPosY - 1][newPosX + 1].getFarbe() == Farbe.WHITE) {
            if (!checkSameColor(posX, posY, newPosX + 1, newPosY - 1, feld)) {
                return true;
            }
        }

        // check black Bauer
        // Bauer sind Min. PosY = 6 und können wir zur 0 vorgehen

        if (newPosY <= 6 && newPosX >= 1 && felder[newPosY + 1][newPosX - 1].getClass() == Bauer.class &&
                felder[newPosY + 1][newPosX - 1].getFarbe() == Farbe.BLACK) {
            return !checkSameColor(posX, posY, newPosX - 1, newPosY + 1, feld);
        }
        else if (newPosX <= 6 && newPosY <= 6 && felder[newPosY + 1][newPosX + 1].getClass() == Bauer.class &&
                felder[newPosY + 1][newPosX + 1].getFarbe() == Farbe.BLACK) {
            return !checkSameColor(posX, posY, newPosX + 1, newPosY + 1, feld);
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
            str += "kingW.png";
        }
        else{
            str += "kingB.png";
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
        if(checkSchachmat()){
            isMate = true;
        }
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    private boolean checkSchachmat(){
        return false;
    }
}
