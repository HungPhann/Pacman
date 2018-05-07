package entity.creature.ghost;

import entity.creature.Pacman;
import maze.Maze;

public class LazyGhost extends TargetGhost {
    public LazyGhost(Maze maze, Pacman target){
        super(maze, target);
    }

    @Override
    protected boolean[] sense() {
        boolean[] isClear = new boolean[4]; //0: LEFT, 1: UP, 2: DOWN, 3: RIGHT
        if(this.getMaze().isVisibleLeft(this.getPosition(), target.getPosition()) && this.checkLeft())   //LEFT{
            isClear[0] = true;
        else if(this.getMaze().isVisibleRight(this.getPosition(), target.getPosition()) && this.checkRight())  //RIGHT
            isClear[3] = true;
        else if(this.getMaze().isVisibleUp(this.getPosition(), target.getPosition()) && this.checkUp())  //UP
            isClear[1] = true;
        else if(this.getMaze().isVisibleDown(this.getPosition(), target.getPosition()) && this.checkDown())  //DOWN
            isClear[2] = true;
        return isClear;
    }
}
