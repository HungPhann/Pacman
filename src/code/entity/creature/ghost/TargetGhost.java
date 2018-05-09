package code.entity.creature.ghost;

import code.entity.creature.Pacman;
import code.maze.Maze;

public abstract class TargetGhost extends Ghost {
    protected Pacman target;

    public TargetGhost(Maze maze, Pacman target){
        super(maze);
        this.target = target;
    }
}
