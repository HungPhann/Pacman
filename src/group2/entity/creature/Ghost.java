package group2.entity.creature;

import group2.maze.Maze;
import group2.position.Direction;
import group2.position.Position;
import group2.setting.Setting;

import javax.xml.bind.ValidationEvent;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ghost extends Creature {
    private static boolean activated = true;
    private final static int[] VALID_SPEED = {1, 2, 3, 4};

    public Ghost(Maze maze){
        this.setSpeed(VALID_SPEED[(int)(Math.random()*VALID_SPEED.length)]);
        this.setMaze(maze);
        this.initPosition();
        this.setDirection(new Direction());
    }

    public void initPosition(){
        for(int i = 0; i < Setting.N_BLOCKS_VERTICAL; i++){
            for(int j = 0; j < Setting.N_BLOCKS_HORIZON; j++){
                if ((this.getMaze().getValue(i, j) & (short) 1) != 0){
                    this.setPosition(new Position(j * Setting.BLOCK_SIZE, i * Setting.BLOCK_SIZE));
                   return;
                }
            }
        }
    }

    private boolean[] sence(){
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
            switch (this.getDirection().getDirection()){
                case Direction.LEFT:
                    this.goLeft();
                    break;
                case Direction.RIGHT:
                    this.goRight();
                    break;
                case Direction.UP:
                    this.goUp();
                    break;
                case Direction.DOWN:
                    this.goDown();
                    break;
            }
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
