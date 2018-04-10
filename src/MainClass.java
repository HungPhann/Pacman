import group2.manager.Game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainClass extends JFrame {

    public MainClass() {

        initUI();
    }

    private void initUI() {
        Game game = new Game();
        add(game);

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(670, 758);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainClass ex = new MainClass();
            ex.setVisible(true);
        });
    }
}