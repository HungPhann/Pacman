package group2.entity.creature;

import group2.maze.Maze;

public abstract class TargetGhost extends Ghost{
    protected Pacman target;

    public TargetGhost(Maze maze, Pacman target){
        super(maze);
        this.target = target;
    }
}
