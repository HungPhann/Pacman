import code.manager.Game;

import javax.swing.JFrame;

public class MainClass extends JFrame {
    public MainClass() {
        //new Game
        Game game = new Game();
        this.add(game);

        //set up Frame
        this.setTitle("Pacman");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(670, 758);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainClass main = new MainClass();
    }
}