package code.entity.creature.ghost;

import code.entity.creature.Pacman;
import code.maze.Maze;

public class SmartGhost extends TargetGhost {
    public SmartGhost(Maze maze, Pacman target){
        super(maze, target);
    }

    @Override
    protected boolean[] sense() {
        boolean[] isClear = new boolean[4]; //0: LEFT, 1: UP, 2: DOWN, 3: RIGHT
        //see Pacman
        if(this.getMaze().isVisibleLeft(this.getPosition(), target.getPosition()) && this.checkLeft()) {  //LEFT{
            isClear[0] = true;
            return isClear;
        }
        else if(this.getMaze().isVisibleRight(this.getPosition(), target.getPosition()) && this.checkRight()) { //RIGHT
            isClear[3] = true;
            return isClear;
        }
        else if(this.getMaze().isVisibleUp(this.getPosition(), target.getPosition()) && this.checkUp()) { //UP
            isClear[1] = true;
            return isClear;
        }
        else if(this.getMaze().isVisibleDown(this.getPosition(), target.getPosition()) && this.checkDown()) { //DOWN
            isClear[2] = true;
            return isClear;
        }

        //cannot see Pacman
        if(this.checkDown())
            isClear[2] = true;

        if(this.checkUp())
            isClear[1] = true;

        if(this.checkRight())
            isClear[3] = true;

        if(this.checkLeft())
            isClear[0] = true;

        return isClear;
    }
}
