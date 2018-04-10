package group2.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import group2.drawing.Draw;
import group2.entity.creature.Ghost;
import group2.entity.creature.Pacman;
import group2.entity.food.Coin;
import group2.entity.food.Food;
import group2.entity.food.Strawberry;
import group2.maze.Maze;
import group2.setting.Setting;
import group2.sound.Sound;

public class Game extends JPanel implements ActionListener{
    Maze maze;
    Pacman pacman;
    Ghost ghosts[];
    Food[][] foods;
    Timer timer;

    public Game(){
        addKeyListener(new Game.TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        this.initAttribute();
        //GameManager.initGame(this);
        timer = new Timer(40, this);
        timer.start();
        Sound.playBackGroundMusic();
    }

    void initAttribute(){
        maze = new Maze();
        pacman = new Pacman(maze);
        this.initFoods();
        ghosts = new Ghost[Setting.NUMBER_OF_GHOSTS];
        for(int i = 0; i < Setting.NUMBER_OF_GHOSTS; i++)
            ghosts[i] = new Ghost(maze);
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
        else Draw.drawIntroScreen(g, this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Draw.drawMaze(g, maze, this);
        Draw.drawScoreBoard(g, this);
        Draw.drawPacman(g, pacman, this, pacman.getPosition().getX()+1, pacman.getPosition().getY()+1);
        Draw.drawGhosts(g, ghosts, this);

        if(GameManager.inGame) {
            if(!GameManager.isPause)
                play(g);
            else Draw.drawPauseScreen(g, this);
        }
        else
            Draw.drawIntroScreen(g, this);
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
