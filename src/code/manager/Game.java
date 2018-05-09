package code.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import code.drawing.Drawing;
import code.entity.creature.*;
import code.entity.creature.ghost.Ghost;
import code.entity.creature.ghost.LazyGhost;
import code.entity.creature.ghost.NormalGhost;
import code.entity.creature.ghost.SmartGhost;
import code.entity.food.Coin;
import code.entity.food.Food;
import code.entity.food.Strawberry;
import code.maze.Maze;
import code.setting.Setting;
import code.sound.Sound;

public class Game extends JPanel implements ActionListener{
    private Maze maze;
    private Pacman pacman;
    private Ghost ghosts[];
    private Food[][] foods;
    private Timer timer;

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public void setGhosts(Ghost[] ghosts) {
        this.ghosts = ghosts;
    }

    public Food[][] getFoods() {
        return foods;
    }

    public Game(){
        addKeyListener(new Game.TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        this.initAttribute();
        timer = new Timer(60, this);
        timer.start();
        Sound.playBackGroundMusic();
    }

    void initAttribute(){
        maze = new Maze();
        pacman = new Pacman(maze);
        this.initFoods();

        //init Ghosts
        ghosts = new Ghost[Setting.NUMBER_OF_GHOSTS];
        for (int i = 0; i < Setting.NUMBER_OF_GHOSTS; i ++){
            if (i % 10 == 0)
                ghosts[i] = new LazyGhost(maze, pacman);
            else if (i % 10 == 1 || i % 10 == 2)
                ghosts[i] = new SmartGhost(maze, pacman);
            else
                ghosts[i] = new NormalGhost(maze);
        }
    }

    private void initFoods(){
        foods = new Food[Setting.N_BLOCKS_VERTICAL][Setting.N_BLOCKS_HORIZON];
        for(int i = 0; i < Setting.N_BLOCKS_VERTICAL; i ++){
            for(int j = 0; j < Setting.N_BLOCKS_HORIZON; j ++){
                if((maze.getValue(i, j) & 32) != 0)
                    foods[i][j] = new Coin();
                if((maze.getValue(i, j) & 64) != 0)
                    foods[i][j] = new Strawberry();
            }
        }
    }

    public void play(Graphics g){
        if(!GameManager.isDead) {
            GameManager.movePacman(pacman);
            for (int i = 0; i < Setting.NUMBER_OF_GHOSTS; i++) {
                ghosts[i].move();
            }

            if (GameManager.checkDead(this))
                GameManager.dead(this);
            GameManager.updateViewAnimation();
            GameManager.checkPacmanEatingFood(this);
            if (GameManager.checkFinishLevel(this)) {
                GameManager.goNextLevel(this);
            }
            GameManager.updateHighScore();
        }
        else Drawing.drawIntroScreen(g, this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Drawing.drawMaze(g, maze, this);
        Drawing.drawScoreBoard(g, this);
        Drawing.drawInstruction(g, this);
        Drawing.drawPacman(g, pacman, this, pacman.getPosition().getX()+1, pacman.getPosition().getY()+1);
        Drawing.drawGhosts(g, ghosts, this);

        if(GameManager.inGame) {
            if(!GameManager.isPause)
                play(g);
            else Drawing.drawPauseScreen(g, this);
        }
        else
            Drawing.drawIntroScreen(g, this);
    }

    public void actionPerformed(ActionEvent ev){
        repaint();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (GameManager.inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    pacman.setDirectionLeft();
                } else if (key == KeyEvent.VK_RIGHT) {
                    pacman.setDirectionRight();
                } else if (key == KeyEvent.VK_UP) {
                    pacman.setDirectionUp();
                } else if (key == KeyEvent.VK_DOWN) {
                    pacman.setDirectionDown();
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    GameManager.inGame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                        GameManager.isPause = true;
                    } else {
                        timer.start();
                        GameManager.isPause = false;
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    GameManager.initGame(Game.this);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
