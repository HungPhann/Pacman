package group2.entity.creature;

import group2.maze.Maze;
import group2.position.Direction;
import group2.position.Position;
import group2.setting.Setting;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NormalGhost extends Ghost{

    public NormalGhost(Maze maze){
        super(maze);
    }

    @Override
    protected boolean[] sence(){
        boolean[] isClear = new boolean[4]; //0: LEFT, 1: UP, 2: DOWN, 3: RIGHT
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
    public void move(){
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

    public static void activate(){
        Ghost.activated = true;
    }

    public static void deactivate(){
        Ghost.activated = false;

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable() {
            public void run() {
                Ghost.activate();
            }
        };
        scheduler.schedule(task, 3, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
