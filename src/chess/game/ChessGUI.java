package chess.game;
import java.io.*;
import chess.board.Board;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.utils.Position;
import javax.swing.*;
import java.awt.*;

/**
 * Main GUI class for the chess game.
 * Displays the board, handles user interaction,
 * and manages game state and GUI features.
 */
public class ChessGUI {
    private Position selectedPosition = null;
    private Board board;
    private JButton[][] squares;
    private JFrame frame;
    private boolean whiteTurn = true;
    private String boardTheme = "Classic";
    private String pieceStyle = "Default";
    private String boardSize = "Medium";

/**
 * Creates the chess GUI window and initializes the board,
 * menu system, and visual components.
 */
    public ChessGUI() {
        board = new Board();
        squares = new JButton[8][8];

        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28));
                square.setFocusPainted(false);
                square.setMargin(new Insets(0, 0, 0, 0));
                square.setOpaque(true);
                square.setContentAreaFilled(true);
                square.setBorderPainted(false);

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
        JMenu gameMenu = new JMenu("Menu");

        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem saveGameItem = new JMenuItem("Save Game");
        JMenuItem loadGameItem = new JMenuItem("Load Game");
        JMenuItem settingsItem = new JMenuItem("Settings");

        newGameItem.addActionListener(e -> resetGame());
        saveGameItem.addActionListener(e -> saveGame());
        loadGameItem.addActionListener(e -> loadGame());
        settingsItem.addActionListener(e -> openSettings());

        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(loadGameItem);
        gameMenu.add(settingsItem);
        menuBar.add(gameMenu);

        frame.setJMenuBar(menuBar);

        applyBoardTheme();
        applyPieceStyle();
        applyBoardSize();

        

    }
/**
 * Handles user clicks on the chessboard.
 * Selects a piece or moves it to a destination square.
 */
 private void handleSquareClick(int row, int col) {
        Position clicked = new Position(row, col);
        Piece clickedPiece = board.getPiece(clicked);

        if (selectedPosition == null) {
            if (clickedPiece == null) {
                return;
            }

            if (whiteTurn && !clickedPiece.getColor().equals("white")) {
            return;
        }

        if (!whiteTurn && !clickedPiece.getColor().equals("black")) {
            return;
        }

        selectedPosition = clicked;
        squares[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        return;
        }

        Position from = selectedPosition;
        Position to = clicked;
        Piece movingPiece = board.getPiece(from);

        if (movingPiece == null) {
        clearSelection();
        return;
        }

        if (!board.isValidMove(from, to)) {
        JOptionPane.showMessageDialog(frame, "Illegal move.");
        clearSelection();
        refreshBoard();
        return;
        }

        board.movePiece(from, to);
        whiteTurn = !whiteTurn;

        clearSelection();
        refreshBoard();
}

/**
 * Clears the currently selected square and removes highlighting.
 */
    private void clearSelection() {
        if (selectedPosition != null) {
            int row = selectedPosition.getRow();
            int col = selectedPosition.getCol();
            squares[row][col].setBorder(null);
        }
        selectedPosition = null;
    }
/**
 * Updates the GUI board display based on the current board state.
 */
    private void refreshBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                squares[row][col].setText(piece == null ? "" : piece.getDisplaySymbol());
            }
        }
    }
/**
 * Resets the game to the initial state.
 */
     private void resetGame() {
        board.resetBoard();
        selectedPosition = null;
        whiteTurn = true;
        clearSelection();
        refreshBoard();
    }
/**
 * Saves the current game state to a file.
 */
    private void saveGame() {
    File file = new File("savegame.txt");

    try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
        out.println(whiteTurn ? "WHITE" : "BLACK");

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                out.print(piece == null ? "##" : piece.getSymbol());
                if (col < 7) out.print(" ");
            }
            out.println();
        }

        JOptionPane.showMessageDialog(frame, "Game saved to savegame.txt");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(frame, "Could not save game: " + ex.getMessage());
    }
}
/**
 * Loads a previously saved game state from a file.
 */
    private void loadGame() {   
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Load Game");

    if (chooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
        return;
    }

    try (BufferedReader in = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
        String turnLine = in.readLine();
        whiteTurn = "WHITE".equalsIgnoreCase(turnLine.trim());

        board.clearBoard();

        for (int row = 0; row < 8; row++) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException("File ended early.");
            }

            String[] tokens = line.trim().split("\\s+");
            for (int col = 0; col < 8; col++) {
                if (!tokens[col].equals("##")) {
                    board.setPiece(new Position(row, col), pieceFromSymbol(tokens[col]));
                }
            }
        }

        selectedPosition = null;
        clearSelection();
        refreshBoard();
        JOptionPane.showMessageDialog(frame, "Game loaded.");
    } catch (IOException | RuntimeException ex) {
        JOptionPane.showMessageDialog(frame, "Could not load game: " + ex.getMessage());
    }
}
/**
 * Creates a Piece object from its string representation.
 */
    private Piece pieceFromSymbol(String symbol) {

        String color = symbol.charAt(0) == 'w' ? "white" : "black";
        char type = symbol.charAt(1);

        return switch (type) {
            case 'p' -> new Pawn(color);
            case 'R' -> new Rook(color);
            case 'N' -> new Knight(color);
            case 'B' -> new Bishop(color);
            case 'Q' -> new Queen(color);
            case 'K' -> new King(color);
            default -> throw new IllegalArgumentException("Unknown piece symbol: " + symbol);
        };
}
/**
 * Opens the settings dialog window.
 */
    private void openSettings() {
        ChessSettingsDialog dialog = new ChessSettingsDialog(frame, this);
        dialog.setVisible(true);
    }
/**
 * Applies user-selected settings to the board and pieces.
 */
    public void applySettings(String theme, String pieceStyle, String size) {
        boardTheme = theme;
        this.pieceStyle = pieceStyle;
        boardSize = size;

        applyBoardTheme();
        applyPieceStyle();
        applyBoardSize();
        refreshBoard();
}
/**
 * Updates the board colors based onthe selected theme.
 */
    private void applyBoardTheme() {
        Color light;
        Color dark;

        switch (boardTheme) {
            case "Pink" -> {
                light = new Color(255, 220, 235);
                dark = new Color(230, 140, 180);
            }
            case "Blue" -> {
                light = new Color(210, 230, 255);
                dark = new Color(80, 120, 170);
            }
            default -> {
                light = new Color(240, 217, 181);
                dark = new Color(181, 136, 99);
            }
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setBackground((row + col) % 2 == 0 ? light : dark);
            }
        }
}
/**
 * Updates the appearance of the pieces (font and style).
 */
    private void applyPieceStyle() {
        Font font;

        switch (pieceStyle) {
            case "Bold" -> font = new Font("Segoe UI Symbol", Font.BOLD, 28);
            case "Large" -> font = new Font("Segoe UI Symbol", Font.BOLD, 36);
            default -> font = new Font("Segoe UI Symbol", Font.PLAIN, 28);
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setFont(font);
            }
        }
    }
/**
 * Adjusts the size of the game window based on user selection.
 */
    private void applyBoardSize() {
        int size;

        switch (boardSize) {
            case "Small" -> size = 550;
            case "Large" -> size = 850;
            default -> size = 700;
        }

        frame.setSize(size, size);
        frame.revalidate();
}
/**
 * Main method for launching the chess GUI application.
 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}