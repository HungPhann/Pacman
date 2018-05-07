package entity.creature.ghost;

import entity.creature.Creature;
import maze.Maze;
import position.Direction;
import position.Position;
import setting.Setting;

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

    protected abstract boolean[] sense();

    protected void decideDirection(){

        boolean[] isClear = this.sense();
        int previousDirection = this.getDirection().getDirection();
        int numberOfClearDirection = 0;
        if (this.getPosition().getX() % Setting.BLOCK_SIZE == 0 &&
                this.getPosition().getY() % Setting.BLOCK_SIZE == 0) {
            for (int i = 0; i < isClear.length; i++) {
                if (isClear[i])
                    numberOfClearDirection++;
            }

            if (numberOfClearDirection == 0)
                this.getDirection().setDirection(Direction.UNDIFINED);
            else if (numberOfClearDirection == 1) {
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
    }

    public void move(){
        if(Ghost.activated){
            this.decideDirection();
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
