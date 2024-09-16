package de.student.game.mapper;

public class MapInput {

    private static int A = 0;
    private static int B = 1;
    private static int C = 2;
    private static int D = 3;
    private static int E = 4;
    private static int F = 5;
    private static int G = 6;
    private static int H = 7;

    public static String map(String string){
        if(string.length() != 2){
            return "ERROR";
        }
        if(string.charAt(0) < 97 || string.charAt(0) > 122){
            return "Invalid Input!";
        }
        else{
            if(string.charAt(1) < 48 || string.charAt(1) > 57){
                return "Invalid Input!";
            }
        }
        String[] pos = string.split("");
        int posY = compare(pos[0]);
        int posX = Integer.parseInt(pos[1]);
        return ""+posY+""+posX;
    }

    public static int compare(String s){
        if(s.equals("a")){
            return A;
        }
        else if(s.equals("b")){
            return B;
        }
        else if(s.equals("c")){
            return C;
        }
        else if(s.equals("d")){
            return D;
        }
        else if(s.equals("e")){
            return E;
        }
        else if(s.equals("f")){
            return F;
        }
        else if(s.equals("g")){
            return G;
        }
        else{
            return H;
        }
    }
}
