package chess.game;

import chess.board.Board;
import chess.pieces.Piece;
import chess.utils.Position;
import javax.swing.*;
import java.awt.*;

/**
 * Main GUI window for the chess game.
 */
public class ChessGUI {
    
    private final Board board;
    private final JButton[][] squares;

    public ChessGUI() {
        board = new Board();
        squares = new JButton[8][8];

        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setFont(new Font("Arial", Font.BOLD, 18));
                square.setFocusPainted(false);
                square.setMargin(new Insets(0, 0, 0, 0));

                if ((row + col) % 2 == 0) {
                    square.setBackground(new Color(240, 217, 181));
                } else {
                    square.setBackground(new Color(181, 136, 99));
                }

                Piece piece = board.getPiece(new Position(row, col));
                if (piece != null) {
                    square.setText(piece.getSymbol());
                }

                squares[row][col] = square;
                boardPanel.add(square);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}