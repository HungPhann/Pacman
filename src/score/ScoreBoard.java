package score;

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
            File file = new File("scoreboard/high_score.txt");
            Scanner scanner = new Scanner(file);
            try {
                ScoreBoard.highScore = scanner.nextInt();
            }
            catch(InputMismatchException ex){
                ex.printStackTrace();
            }
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static void storeHighScore(){
        try {
            Writer writer = new FileWriter("scoreboard/high_score.txt");
            writer.write(Integer.toString(ScoreBoard.getHighScore()));
            writer.close();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
