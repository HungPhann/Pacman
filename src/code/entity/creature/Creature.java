package code.entity.creature;

import code.maze.Maze;
import code.position.Direction;
import code.position.Position;
import code.setting.Setting;

public abstract class Creature {
    private int speed;
    private Position position;
    private Maze maze;
    private Direction direction;

    public int getSpeed() {
        return speed;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    public Position getPosition() {
        return position;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }

    public Maze getMaze(){
        return maze;
    }

    protected void setMaze(Maze maze){
        this.maze = maze;
    }

    public Direction getDirection() {
        return this.direction;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public  void goLeft(){
        this.position.setX(this.position.getX() - speed);
    }

    public void goRight(){
        this.position.setX(this.position.getX() + speed);
    }

    public void goUp(){
        this.position.setY(this.position.getY() - speed);
    }

    public void goDown(){
        this.position.setY(this.position.getY() + speed);
    }

    public boolean checkLeft(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if((x % Setting.BLOCK_SIZE == 0) &&
                (((this.getMaze().getValue(y/Setting.BLOCK_SIZE, x/Setting.BLOCK_SIZE) & 16) != 0) ||
                        ((this.getMaze().getValue((y+Setting.BLOCK_SIZE-1)/Setting.BLOCK_SIZE, x/Setting.BLOCK_SIZE) & 16) != 0))
                ) return false;
        return true;
    }

    public boolean checkRight(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if(((this.getMaze().getValue(y/Setting.BLOCK_SIZE, x/Setting.BLOCK_SIZE) & 8) != 0) ||
                ((this.getMaze().getValue((y+Setting.BLOCK_SIZE-1)/Setting.BLOCK_SIZE, x/Setting.BLOCK_SIZE) & 8) != 0))
            return false;
        return true;
    }

    public boolean checkUp(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if((y % Setting.BLOCK_SIZE == 0) &&
                ((this.getMaze().getValue(y/Setting.BLOCK_SIZE, x/Setting.BLOCK_SIZE) & 4) != 0 ||
                        ((this.getMaze().getValue(y/Setting.BLOCK_SIZE, (x+Setting.BLOCK_SIZE-1)/Setting.BLOCK_SIZE) & 4) != 0)
                ))
            return false;
        return true;
    }

    public boolean checkDown(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if((this.getMaze().getValue(y/Setting.BLOCK_SIZE, x/Setting.BLOCK_SIZE) & 2) != 0 ||
                ((this.getMaze().getValue(y/Setting.BLOCK_SIZE, (x+Setting.BLOCK_SIZE-1)/Setting.BLOCK_SIZE) & 2) != 0))
            return false;
        return true;
    }

    public abstract void initPosition();
}
