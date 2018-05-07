package position;

public class Direction {
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int UNDIFINED = 4;
    private int direction;

    public Direction(){
        direction = UNDIFINED;
    }

    public int getDirection(){
        return direction;
    }

    public void setDirection(int direction){
        switch (direction){
            case LEFT:
                this.setLeft();
                break;
            case RIGHT:
                this.setRight();
                break;
            case UP:
                this.setUp();
                break;
            case DOWN:
                this.setDown();
                break;
            case UNDIFINED:
                this.resetDirection();
                break;
            default:
                    break;
        }
    }

    public void resetDirection(){
        this.direction = UNDIFINED;
    }

    public void setLeft(){
        direction = LEFT;
    }

    public void setRight(){
        direction = RIGHT;
    }

    public void setUp(){
        direction = UP;
    }

    public void setDown(){
        direction = DOWN;
    }

    public boolean isLeft(){
        return (direction == LEFT);
    }

    public boolean isRight(){
        return (direction == RIGHT);
    }

    public boolean isUp(){
        return (direction == UP);
    }

    public boolean isDown(){
        return (direction == DOWN);
    }
}
