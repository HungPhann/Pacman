package entity.creature.ghost;

import maze.Maze;

public class NormalGhost extends Ghost {

    public NormalGhost(Maze maze){
        super(maze);
    }

    @Override
    protected boolean[] sense(){
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
}
