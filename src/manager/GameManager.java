package manager;

import entity.creature.Pacman;
import entity.food.Food;
import maze.Maze;
import score.ScoreBoard;
import setting.Setting;
import sound.Sound;

public class GameManager {
    public static int viewAnimation;
    public static int lifeLeft;
    public static boolean isDead;
    public static boolean inGame;
    public static boolean isPause;

    public static void initGame(Game game){
        GameManager.lifeLeft = 3;
        GameManager.viewAnimation = 1;
        GameManager.isDead = false;
        GameManager.inGame = true;
        GameManager.isPause = false;
        ScoreBoard.resetCurrentScore();
        ScoreBoard.loadHighScore();
        game.initAttribute();
    }

    public static void goNextLevel(Game game){
        game.initAttribute();
    }

    public static boolean checkFinishLevel(Game game){
        Food[][] foods = game.getFoods();
        for(int i = 0; i < Setting.N_BLOCKS_VERTICAL; i ++)
            for(int j = 0; j < Setting.N_BLOCKS_HORIZON; j ++)
                if(foods[i][j] != null) return false;
        return true;
    }

    public static boolean checkDead(Game game){
        int pacman_x = game.getPacman().getPosition().getX();
        int pacman_y = game.getPacman().getPosition().getY();

        for(int i = 0; i < game.getGhosts().length; i ++){
            if (pacman_x > (game.getGhosts()[i].getPosition().getX() - Setting.BLOCK_SIZE/2) && pacman_x < (game.getGhosts()[i].getPosition().getX() + Setting.BLOCK_SIZE/2)
                    && pacman_y > (game.getGhosts()[i].getPosition().getY() - Setting.BLOCK_SIZE/2) && pacman_y < (game.getGhosts()[i].getPosition().getY() + Setting.BLOCK_SIZE/2)
                    && inGame) {
                return true;
            }
        }
        return false;
    }

    public static void dead(Game game){
        Sound.playDeadMusic();
        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        GameManager.lifeLeft --;
        if(GameManager.lifeLeft == 0){
            GameManager.isDead = true;
            GameManager.inGame = false;
            GameManager.storeHighScore();
        }
        else {
            game.getPacman().initPosition();
            for(int i = 0; i < game.getGhosts().length; i ++){
                game.getGhosts()[i].initPosition();
            }
        }


    }

    public static void updateViewAnimation(){
        GameManager.viewAnimation += 1;
        if (GameManager.viewAnimation > 3)
            GameManager.viewAnimation = 1;
    }

    public static void checkPacmanEatingFood(Game game){
        Pacman pacman = game.getPacman();
        Maze maze = game.getMaze();
        Food[][] foods = game.getFoods();
        int pacman_x = (pacman.getPosition().getX() + Setting.BLOCK_SIZE/2)/Setting.BLOCK_SIZE;
        int pacman_y = (pacman.getPosition().getY() + Setting.BLOCK_SIZE/2)/Setting.BLOCK_SIZE;

        if ((maze.getValue(pacman_y, pacman_x) & 32) != 0) {
            pacman.eat(foods[pacman_y][pacman_x]);
            foods[pacman_y][pacman_x] = null;
            maze.setValue(pacman_y, pacman_x, (short) (maze.getValue(pacman_y, pacman_x) - (short) 32));
            Sound.playEatMusic();
        }
        else if ((maze.getValue(pacman_y, pacman_x) & 64) != 0) {
            pacman.eat(foods[pacman_y][pacman_x]);
            foods[pacman_y][pacman_x] = null;
            maze.setValue(pacman_y, pacman_x, (short) (maze.getValue(pacman_y, pacman_x) - (short) 64));
            Sound.playEatMusic();
            Sound.playGhostDeactivatedMusic();
        }
    }

    public static void movePacman(Pacman pacman){
        if(pacman.getDirection().isLeft()) {
            if (pacman.checkLeft())
                pacman.goLeft();
        }
        else if(pacman.getDirection().isRight()) {
            if (pacman.checkRight())
                pacman.goRight();
        }
        else if(pacman.getDirection().isUp()) {
            if (pacman.checkUp())
                pacman.goUp();
        }
        else if(pacman.getDirection().isDown()) {
            if (pacman.checkDown())
                pacman.goDown();
        }
    }

    public static void updateHighScore(){
        ScoreBoard.updateHighScore();
    }

    public static void storeHighScore(){
        ScoreBoard.storeHighScore();
    }
}
