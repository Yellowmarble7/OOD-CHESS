package chess.game;

import javax.swing.JFrame;

/**
 * Main GUI window for the chess game.
 */
public class ChessGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");

        frame.setSize(600, 600); // window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }
}