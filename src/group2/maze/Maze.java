package group2.maze;

import java.io.*;
import java.util.*;

public class Maze {
    private short[][] maze;

    public  Maze(){
        maze = new short[30][20];
        try {
            File file = new File("mazes/maze1.txt");
            Scanner scanner = new Scanner(file);
            for(int i = 0; i < 30; i ++){
                for(int j = 0; j < 20 ; j ++){
                    maze[i][j] = scanner.nextShort();
                }
            }
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void setValue(int row, int col, short value){
        maze[row][col] = value;
    }
    public short getValue(int row, int col){
        return maze[row][col];
    }
}
