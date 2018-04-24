package group2.maze;

import group2.position.Position;
import group2.setting.Setting;

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

    private boolean checkVisibleVertical(Position pos_1, Position pos_2){ // pos_1 is above pos_2
        if(pos_1.getY() > pos_2.getY()) return  false;

        if(pos_1.getY()/Setting.BLOCK_SIZE + 1 == pos_2.getY()/Setting.BLOCK_SIZE){ //adjacent block
            if ((maze[pos_1.getY()/Setting.BLOCK_SIZE][pos_1.getX()/Setting.BLOCK_SIZE] & 2) != 0){
                return false;
            }
        }
        else {  //separated by at least 1 block
            for (int row = pos_1.getY() / Setting.BLOCK_SIZE + 1; row < pos_2.getY() / Setting.BLOCK_SIZE; row += 1) {
                if (((maze[row][pos_1.getX() / Setting.BLOCK_SIZE] & 4) != 0) ||
                        ((maze[row][pos_1.getX() / Setting.BLOCK_SIZE] & 2) != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkVisibleHorizontal(Position pos_1, Position pos_2){ // pos_1 is on the left of pos_2
        if(pos_1.getX() > pos_2.getX()) return  false;

        if(pos_1.getX()/Setting.BLOCK_SIZE + 1 == pos_2.getX()/Setting.BLOCK_SIZE){ //adjacent block
            if ((maze[pos_1.getY()/Setting.BLOCK_SIZE][pos_1.getX()/Setting.BLOCK_SIZE] & 8) != 0){
                return false;
            }
        }
        else {  //separated by at least 1 block
            for (int col = pos_1.getX() / Setting.BLOCK_SIZE + 1; col < pos_2.getX() / Setting.BLOCK_SIZE; col += 1) {
                if (((maze[pos_1.getY() / Setting.BLOCK_SIZE][col] & 16) != 0) ||
                        ((maze[pos_1.getY() / Setting.BLOCK_SIZE][col] & 8) != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isVisibleLeft(Position pos_1, Position pos_2){   //pos_1 can see pos_2 in the left
        if(pos_1.getY() == pos_2.getY())
            return checkVisibleHorizontal(pos_2, pos_1);
        return false;
    }

    public boolean isVisibleRight(Position pos_1, Position pos_2){   //pos_1 can see pos_2 in the right
        if(pos_1.getY() == pos_2.getY())
            return checkVisibleHorizontal(pos_1, pos_2);
        return false;
    }

    public boolean isVisibleUp(Position pos_1, Position pos_2){   //pos_1 can see pos_2 in the Up
        if(pos_1.getX() == pos_2.getX())
            return checkVisibleVertical(pos_2, pos_1);
        return false;
    }

    public boolean isVisibleDown(Position pos_1, Position pos_2){   //pos_1 can see pos_2 in the Down
        if(pos_1.getX() == pos_2.getX())
            return checkVisibleVertical(pos_1, pos_2);
        return false;
    }
}
