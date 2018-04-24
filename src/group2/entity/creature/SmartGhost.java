package group2.entity.creature;

import group2.maze.Maze;
import group2.position.Direction;
import group2.setting.Setting;

public class SmartGhost extends TargetGhost {
    public SmartGhost(Maze maze, Pacman target){
        super(maze, target);
    }

    @Override
    protected boolean[] sence() {
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

    @Override
    public void move() {
        if(Ghost.activated){

            boolean[] isClear = this.sence();
            int previousDirection = this.getDirection().getDirection();
            int numberOfClearDirection = 0;
            if (this.getPosition().getX() % Setting.BLOCK_SIZE == 0 &&
                    this.getPosition().getX() % Setting.BLOCK_SIZE == 0) {
                for (int i = 0; i < isClear.length; i++) {
                    if (isClear[i])
                        numberOfClearDirection++;
                }

                if (numberOfClearDirection == 1) {
                    for (int i = 0; i < isClear.length; i++) {
                        if (isClear[i])
                            this.getDirection().setDirection(i);
                    }
                } else {
                    while (true) {
                        int direction = (int) (Math.random() * 4);
                        if (!isClear[direction] || (direction + previousDirection == 3))
                            continue;
                        this.getDirection().setDirection(direction);
                        break;
                    }
                }
            }
            super.move();
        }
    }
}
