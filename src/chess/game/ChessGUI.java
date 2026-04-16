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
    private Position selectedPosition = null;
    private Board board;
    private JButton[][] squares;
    private JFrame frame;
    private boolean whiteTurn = true;

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

        /* Menu setup */
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem saveGameItem = new JMenuItem("Save Game");
        JMenuItem loadGameItem = new JMenuItem("Load Game");

        newGameItem.addActionListener(e -> resetGame());
        saveGameItem.addActionListener(e -> saveGame()));
        loadGameItem.addActionListener(e -> loadGame()));

        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(loadGameItem);
        menuBar.add(gameMenu);

        frame.setJMenuBar(menuBar);

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

        board.movePiece(from, to);

        if (targetPiece != null && 
        (targetPiece.getSymbol().equals("bK") || targetPiece.getSymbol().equals("wK"))) {
        refreshBoard();
        String winner = movingPiece.getColor().equals("white") ? "White" : "Black";
        JOptionPane.showMessageDialog(null, winner + " wins! King captured.");
        System.exit(0);
    }

        clearSelection();
        refreshBoard();
    }

     private void clearSelection() {
        if (selectedPosition != null) {
            int row = selectedPosition.getRow();
            int col = selectedPosition.getCol();
            squares[row][col].setBorder(null);
        }
        selectedPosition = null;
    }

    private void refreshBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                squares[row][col].setText(piece == null ? "" : piece.getSymbol());
            }
        }
    }

     private void resetGame() {
        board.resetBoard();
        selectedPosition = null;
        whiteTurn = true;
        clearSelection();
        refreshBoard();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}