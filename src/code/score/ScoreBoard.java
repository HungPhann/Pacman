package code.score;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScoreBoard {
    private static int highScore;
    private static int currentScore;

    public static int getHighScore() {
        return highScore;
    }

    public static int getCurrentScore() {
        return currentScore;
    }

    public static void increaseCurrentScore(int score) {
        if (score >= 0)
            currentScore += score;
    }

    public static void resetCurrentScore() {
        currentScore = 0;
    }

    public static void updateHighScore() {
        if (highScore < currentScore)
            highScore = currentScore;
    }

    public static void loadHighScore(){
        try {
            //File file = new File("high_score.dat");
            //FileInputStream inputStream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(new FileInputStream("high_score.dat"));
            ScoreBoard.highScore = in.readInt();
            in.close();
        }
        catch (FileNotFoundException ex){
            try {
                File file = new File("high_score.dat");
                file.createNewFile();
                file.setWritable(false, true);
            }
            catch(IOException e){
                ex.printStackTrace();
            }
        }
        catch(Exception ex){
            File file = new File("high_score.dat");
            file.delete();

        }
    }

    public static void storeHighScore(){
        try{
            //FileOutputStream out = new FileOutputStream("high_score.dat");
            DataOutputStream out = new DataOutputStream(new FileOutputStream("high_score.dat"));
            out.writeInt(highScore);
            out.flush();
            out.close();
        }
        catch (FileNotFoundException ex){
            try {
                File file = new File("high_score.dat");
                file.createNewFile();
                file.setWritable(false, true);
                //FileOutputStream out = new FileOutputStream(file);
                DataOutputStream out = new DataOutputStream(new FileOutputStream("high_score.dat"));
                out.writeInt(highScore);
                out.flush();
                out.close();
            }
            catch(IOException e){
                ex.printStackTrace();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        /*
        try {
            Writer writer = new FileWriter("data/scoreboard/high_score.txt");
            writer.write(Integer.toString(ScoreBoard.getHighScore()));
            writer.close();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        */
    }

}
