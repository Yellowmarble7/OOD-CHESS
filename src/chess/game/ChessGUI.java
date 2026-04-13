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

                final int r = row;
                final int c = col;

                square.addActionListener(e -> handleSquareClick(r, c));

                squares[row][col] = square;
                boardPanel.add(square);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
        refreshBoard();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

 private void handleSquareClick(int row, int col) {
        Position clicked = new Position(row, col);
        Piece clickedPiece = board.getPiece(clicked);

        if (selectedPosition == null) {
            if (clickedPiece == null) {
                return;
            }
            selectedPosition = clicked;
            squares[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            return;
        }

        Position from = selectedPosition;
        Position to = clicked;

        Piece movingPiece = board.getPiece(from);
        Piece targetPiece = board.getPiece(to);

        if (movingPiece == null) {
            clearSelection();
            return;
        }
