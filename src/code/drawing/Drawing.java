package code.drawing;

import code.entity.creature.ghost.Ghost;
import code.entity.creature.Pacman;
import code.score.ScoreBoard;
import code.setting.Setting;
import code.maze.Maze;
import code.manager.GameManager;

import javax.swing.*;
import java.awt.*;

public class Drawing {

    public static void drawMaze(Graphics g, Maze maze, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Setting.backgroundColor);
        g2d.fillRect(0, 0, Setting.SCREEN_WIDTH, Setting.SCRREEN_HEIGHT);

        for(int y = 0; y < Setting.SCRREEN_HEIGHT; y += Setting.BLOCK_SIZE){
            for(int x = 0; x < Setting.SCREEN_WIDTH; x += Setting.BLOCK_SIZE){
                int i = y / Setting.BLOCK_SIZE;
                int j = x / Setting.BLOCK_SIZE;
                g2d.setColor(Setting.mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if((maze.getValue(i, j) & (short) 2) != 0){
                    g2d.drawLine(x, y + Setting.BLOCK_SIZE-1, x + Setting.BLOCK_SIZE-1, y + Setting.BLOCK_SIZE-1);
                }

                if((maze.getValue(i, j) & (short) 4) != 0){
                    g2d.drawLine(x, y, x + Setting.BLOCK_SIZE-1, y);
                }


                if((maze.getValue(i, j) & (short) 8) != 0){
                    g2d.drawLine(x + Setting.BLOCK_SIZE-1, y, x + Setting.BLOCK_SIZE-1, y + Setting.BLOCK_SIZE-1);
                }

                if((maze.getValue(i, j) & (short) 16) != 0){
                    g2d.drawLine(x, y, x, y + Setting.BLOCK_SIZE-1);
                }

                if((maze.getValue(i, j) & (short) 128) != 0){
                    g2d.drawLine(x, y, x + Setting.BLOCK_SIZE-1, y + Setting.BLOCK_SIZE-1);
                }

                if((maze.getValue(i, j) & (short) 256) != 0){
                    g2d.drawLine(x + Setting.BLOCK_SIZE-1, y, x + Setting.BLOCK_SIZE-1, y + (Setting.BLOCK_SIZE-1)/2);

                    g2d.drawLine(x + Setting.BLOCK_SIZE-1, y + (Setting.BLOCK_SIZE-1)/2, x, y + Setting.BLOCK_SIZE-1);

                }

                if((maze.getValue(i, j) & (short) 32) != 0){
                    g2d.setColor(Setting.dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                if((maze.getValue(i, j) & (short) 64) != 0){
                 //   g2d.setColor(Setting.dotColor);
                   // g2d.fillRect(x + 11, y + 11, 8, 8);
                    //g2d.drawImage(strawberry, x + 1, y + 1, panel);
                    Drawing.drawStrawberry(g, panel, x + 1, y + 1);
                }

            }
        }
    }

    public static void drawStrawberry(Graphics g, JPanel panel, int x, int y){
        Image strawberry = new ImageIcon(Drawing.class.getResource("/data/images/strawberry.jpg")).getImage();
        //Image strawberry = new ImageIcon("data/images/strawberry.jpg").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(strawberry, x, y, panel);
    }

    public static void drawPacman(Graphics g, Pacman pacman, JPanel panel, int x, int y){
        if(pacman.getDirection().isLeft()){
            drawPacmanLeft(g, panel, x, y);
        }
        else if(pacman.getDirection().isRight()){
            drawPacmanRight(g, panel, x, y);
        }
        else if(pacman.getDirection().isUp()){
            drawPacmanUp(g, panel, x, y);
        }
        else if(pacman.getDirection().isDown()){
            drawPacmanDown(g, panel, x, y);
        }

    }

    public static void drawPacmanLeft(Graphics g, JPanel panel, int x, int y){
        switch (GameManager.viewAnimation){
            case 1: drawPacmanLeft1(g, panel, x, y);
                break;
            case 2: drawPacmanLeft2(g, panel, x, y);
                break;
            case 3: drawPacmanLeft3(g, panel, x, y);
                break;
            default:
                break;
        }
    }

    public static void drawPacmanRight(Graphics g, JPanel panel, int x, int y){
        switch (GameManager.viewAnimation){
            case 1: drawPacmanRight1(g, panel, x, y);
                break;
            case 2: drawPacmanRight2(g, panel, x, y);
                break;
            case 3: drawPacmanRight3(g, panel, x, y);
                break;
            default:
                break;
        }
    }

    public static void drawPacmanUp(Graphics g, JPanel panel, int x, int y){
        switch (GameManager.viewAnimation){
            case 1: drawPacmanUp1(g, panel, x, y);
                break;
            case 2: drawPacmanUp2(g, panel, x, y);
                break;
            case 3: drawPacmanUp3(g, panel, x, y);
                break;
            default:
                break;
        }
    }

    public static void drawPacmanDown(Graphics g, JPanel panel, int x, int y){
        switch (GameManager.viewAnimation){
            case 1: drawPacmanDown1(g, panel, x, y);
                break;
            case 2: drawPacmanDown2(g, panel, x, y);
                break;
            case 3: drawPacmanDown3(g, panel, x, y);
                break;
            default:
                break;
        }
    }

    public static void drawPacmanLeft1(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/left1.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/left1.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanLeft2(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/left2.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/left2.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanLeft3(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/left3.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/left3.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanRight1(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/right1.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/right1.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanRight2(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/right2.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/right2.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanRight3(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/right3.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/right3.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanUp1(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/up1.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/up1.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanUp2(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/up2.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/up2.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanUp3(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/up3.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/up3.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanDown1(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/down1.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/down1.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanDown2(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/down2.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/down2.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawPacmanDown3(Graphics g, JPanel panel, int x, int y){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/down3.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/down3.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, x, y, panel);
    }

    public static void drawInstruction(Graphics g, JPanel panel){
        g.setFont(Setting.smallFont);
        g.setColor(new Color(96, 128, 255));

        g.drawString("INSTRUCTION", Setting.SCREEN_WIDTH + 20, Setting.SCRREEN_HEIGHT - 70);
        g.drawString("Pause/Break: Pause", Setting.SCREEN_WIDTH + 20, Setting.SCRREEN_HEIGHT - 40);
        g.drawString("Esc: Quit Game", Setting.SCREEN_WIDTH + 20, Setting.SCRREEN_HEIGHT - 20);
    }

    public static void drawScoreBoard(Graphics g, JPanel panel){
        g.setFont(Setting.smallFont);
        g.setColor(new Color(96, 128, 255));
        String highScore = "High Score: " + ScoreBoard.getHighScore();
        String currentScore = "Score: " + ScoreBoard.getCurrentScore();

        g.drawString(highScore, Setting.SCREEN_WIDTH + 20, 16);
        g.drawString(currentScore, Setting.SCREEN_WIDTH + 20, 50);
        for (int i = 0; i < GameManager.lifeLeft; i ++){
            drawPacmanRight3(g, panel, Setting.SCREEN_WIDTH + 20 + 30*i, 70);
        }
    }

    public static void drawIntroScreen(Graphics g, JPanel panel){
        Graphics2D g2d = (Graphics2D) g;
        //Image backgroundScreen = new ImageIcon("data/images/giphy.gif").getImage();
        Image backgroundScreen = new ImageIcon(Drawing.class.getResource("/data/images/giphy.gif")).getImage();
        g2d.drawImage(backgroundScreen, -13, Setting.SCRREEN_HEIGHT - 500,panel);

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, Setting.SCRREEN_HEIGHT / 2 - 30, Setting.SCREEN_WIDTH - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, Setting.SCRREEN_HEIGHT / 2 - 30, Setting.SCREEN_WIDTH - 100, 50);

        String s = "Press s to start";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = panel.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (Setting.SCREEN_WIDTH - metr.stringWidth(s)) / 2, Setting.SCRREEN_HEIGHT / 2);
    }

    public static void drawPauseScreen(Graphics g, JPanel panel){
        Graphics2D g2d = (Graphics2D) g;
        //Image backgroundScreen = new ImageIcon("data/images/giphy.gif").getImage();
        Image backgroundScreen = new ImageIcon(Drawing.class.getResource("/data/images/giphy.gif")).getImage();
        g2d.drawImage(backgroundScreen, -13, Setting.SCRREEN_HEIGHT - 500,panel);

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, Setting.SCRREEN_HEIGHT / 2 - 30, Setting.SCREEN_WIDTH - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, Setting.SCRREEN_HEIGHT / 2 - 30, Setting.SCREEN_WIDTH - 100, 50);

        String s = "Press Pause to continue";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = panel.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (Setting.SCREEN_WIDTH - metr.stringWidth(s)) / 2, Setting.SCRREEN_HEIGHT / 2);
    }

    public static void drawGhosts(Graphics g, Ghost[] ghosts, JPanel panel){
        for (int i = 0; i < Setting.NUMBER_OF_GHOSTS; i ++){
            if (i % 10 == 0)
                drawLazyGhost(g, ghosts[i], panel);
            else if (i % 10 == 1 || i % 10 == 2)
                drawSmartGhost(g, ghosts[i], panel);
            else
                drawNormalGhost(g, ghosts[i], panel);

        }
    }

    public static void drawNormalGhost(Graphics g, Ghost ghost, JPanel panel){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/normal-ghost.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/normal-ghost.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, ghost.getPosition().getX(), ghost.getPosition().getY(), panel);
    }

    public static void drawLazyGhost(Graphics g, Ghost ghost, JPanel panel){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/lazy-ghost.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/lazy-ghost.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, ghost.getPosition().getX(), ghost.getPosition().getY(), panel);
    }

    public static void drawSmartGhost(Graphics g, Ghost ghost, JPanel panel){
        Image pacman = new ImageIcon(Drawing.class.getResource("/data/images/smart-ghost.gif")).getImage();
        //Image pacman = new ImageIcon("data/images/smart-ghost.gif").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pacman, ghost.getPosition().getX(), ghost.getPosition().getY(), panel);
    }
}
