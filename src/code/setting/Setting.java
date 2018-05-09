package code.setting;

import java.awt.*;

public class Setting {
    public static final Font smallFont = new Font("Helvetica", Font.BOLD, 14);
    public static final Color dotColor = new Color(192, 192, 0);
    public static final Color mazeColor = new Color(0, 80, 120);;
    public static final Color backgroundColor = Color.BLACK;
    public static final int BLOCK_SIZE = 24;
    public static final int N_BLOCKS_HORIZON = 20;
    public static final int N_BLOCKS_VERTICAL = 30;
    public static final int SCREEN_WIDTH = N_BLOCKS_HORIZON * BLOCK_SIZE;
    public static final int SCRREEN_HEIGHT = N_BLOCKS_VERTICAL * BLOCK_SIZE;
    public static final int NUMBER_OF_GHOSTS = 6;
}
