package entity.creature.ghost;

import entity.creature.Pacman;
import maze.Maze;

public abstract class TargetGhost extends Ghost {
    protected Pacman target;

    public TargetGhost(Maze maze, Pacman target){
        super(maze);
        this.target = target;
    }
}
