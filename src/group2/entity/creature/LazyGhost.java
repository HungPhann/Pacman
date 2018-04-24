package group2.entity.creature;

import group2.maze.Maze;
import group2.position.Direction;
import group2.setting.Setting;

public class LazyGhost extends TargetGhost {
    public LazyGhost(Maze maze, Pacman target){
        super(maze, target);
    }

    @Override
    protected boolean[] sence() {
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

    @Override
    public void move() {
        if(Ghost.activated){

            boolean[] isClear = this.sence();
            if (this.getPosition().getX() % Setting.BLOCK_SIZE == 0 &&
                    this.getPosition().getX() % Setting.BLOCK_SIZE == 0) {
                this.getDirection().setDirection(Direction.UNDIFINED);
                for(int i = 0; i < isClear.length; i ++) {
                    if (isClear[i]) {
                        this.getDirection().setDirection(i);
                        break;
                    }
                }
            }
            super.move();
        }
    }
}
