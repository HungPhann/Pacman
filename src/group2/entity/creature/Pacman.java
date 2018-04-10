package group2.entity.creature;

import group2.entity.food.Food;
import group2.maze.Maze;
import group2.position.Direction;
import group2.position.Position;
import group2.score.ScoreBoard;
import group2.setting.Setting;

public class Pacman extends Creature {
    private final static int SPEED = 4;

    public Pacman(Maze maze){
        this.setSpeed(Pacman.SPEED);
        this.setMaze(maze);
        this.setDirection(new Direction());
        this.getDirection().setLeft();
        this.initPosition();
    }

    public void initPosition(){
        for(int i = Setting.N_BLOCKS_VERTICAL - 1; i >= 0 ; i --){
            for(int j = Setting.N_BLOCKS_HORIZON - 1; j >= 0 ; j --){
                if ((this.getMaze().getValue(i, j) & (short) 1) != 0){
                    this.setPosition(new Position(j * Setting.BLOCK_SIZE, i * Setting.BLOCK_SIZE));
                    return;
                }
            }
        }
    }

    public void setDirectionLeft() {
        this.getDirection().setLeft();
    }

    public void setDirectionRight() {
        this.getDirection().setRight();
    }

    public void setDirectionUp() {
        this.getDirection().setUp();
    }

    public void setDirectionDown() {
        this.getDirection().setDown();
    }

    public void eat(Food food){
        ScoreBoard.increaseCurrentScore(food.getScore());
        if(food.isSpecial())
            Ghost.deactivate();
    }
}
