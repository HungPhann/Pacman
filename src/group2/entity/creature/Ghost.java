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

public abstract class Ghost extends Creature {
    protected static boolean activated = true;
    protected final static int[] VALID_SPEED = {1, 2, 3, 4};

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

    protected abstract boolean[] sence();

    public void move(){
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
