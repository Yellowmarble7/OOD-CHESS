package chess.board;

import chess.pieces.*;
import chess.utils.Position;
import java.util.ArrayList;
import java.util.List;
import chess.utils.Move;

/**
 * Represents the chess board as an 8x8 grid.
 */
public class Board {
/**
 * 8x8 array representing the chess board.
 */
    private Piece[][] board;
    /**
     * Creates a new chess board and initializes it.
     */

public Board() {
    board = new Piece[8][8];
    setupBoard();
}
/**
 * Initializes the board with pieces in starting positions.
 */
private void setupBoard() {
        // black back row
        board[0][0] = new Rook("black");
        board[0][1] = new Knight("black");
        board[0][2] = new Bishop("black");
        board[0][3] = new Queen("black");
        board[0][4] = new King("black");
        board[0][5] = new Bishop("black");
        board[0][6] = new Knight("black");
        board[0][7] = new Rook("black");

        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn("black");
        }

        // white back row
        board[7][0] = new Rook("white");
        board[7][1] = new Knight("white");
        board[7][2] = new Bishop("white");
        board[7][3] = new Queen("white");
        board[7][4] = new King("white");
        board[7][5] = new Bishop("white");
        board[7][6] = new Knight("white");
        board[7][7] = new Rook("white");

        for (int col = 0; col < 8; col++) {
            board[6][col] = new Pawn("white");
        }
    }
/**
 * Returns the piece at a given position.
 */
public Piece getPiece(Position pos){
    return board[pos.getRow()][pos.getCol()];
}
/**
 * Moves a piece from one position to another.
 */
public Piece movePiece(Position from, Position to){
    Piece piece = board[from.getRow()][from.getCol()];
    board[from.getRow()][from.getCol()] = null;
    board[to.getRow()][to.getCol()] = piece;
    return piece;
}
/**
 * Displays the current state of the board in the console.
 */
 public void displayBoard() {
        System.out.println("  A  B  C  D  E  F  G  H");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    System.out.print(piece.getSymbol() + " ");
                } else {
                    System.out.print("## ");
                }
            }
            System.out.println();
        }
        
    }
/**
 * Resets the board to the starting configuration.
 */
    public void resetBoard() {
    board = new Piece[8][8];
    setupBoard();
    }
/**
 * Clears all pieces from the board.
 */
    public void clearBoard() {
        board = new Piece[8][8];
    }
/**
 * Places a piece at a specific position on the board.
 */
    public void setPiece(Position pos, Piece piece) {
        board[pos.getRow()][pos.getCol()] = piece;
    }
/**
 * Checks if piece isInsideBoard.
 */
    public boolean isInsideBoard(Position pos) {
    return pos.getRow() >= 0 && pos.getRow() < 8 &&
           pos.getCol() >= 0 && pos.getCol() < 8;
}

/**
 * Checks if path is clear.
 */
public boolean isPathClear(Position from, Position to) {
    int rowStep = Integer.compare(to.getRow(), from.getRow());
    int colStep = Integer.compare(to.getCol(), from.getCol());

    int row = from.getRow() + rowStep;
    int col = from.getCol() + colStep;

    while (row != to.getRow() || col != to.getCol()) {
        if (board[row][col] != null) {
            return false;
        }
        row += rowStep;
        col += colStep;
    }

    return true;
}

/**
 * Checks if move is valid.
 */
public boolean isValidMove(Position from, Position to, boolean whiteTurn) {
    if (!isInsideBoard(from) || !isInsideBoard(to)) {
        return false;
    }
    Piece piece = getPiece(from);
    if (piece == null) {
        return false;
    }
    if (whiteTurn && !piece.getColor().equals("white")) {
        return false;
    }
    if (!whiteTurn && !piece.getColor().equals("black")) {
        return false;
    }
    Piece target = getPiece(to);
    if (target != null && target.getColor().equals(piece.getColor())) {
        return false;
    }
    if (!piece.isValidMove(from, to, this)) {
        return false;
    }
    if (wouldLeaveKingInCheck(from, to, piece.getColor())) {
        return false;
    }
    return true;
}

/**
 * Detects king.
 */
public Position findKing(String color) {
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece piece = board[row][col];
            if (piece instanceof King && piece.getColor().equals(color)) {
                return new Position(row, col);
            }
        }
    }
    return null;
}

/**
 * Checks if king is in check.
 */
public boolean isKingInCheck(String color) {
    Position kingPos = findKing(color);
    if (kingPos == null) {
        return false;
    }

    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece piece = board[row][col];
            if (piece != null && !piece.getColor().equals(color)) {
                Position from = new Position(row, col);
                if (piece.isValidMove(from, kingPos, this)) {
                    return true;
                }
            }
        }
    }

    return false;
}

/**
 * Prevents illegal moves that would place the king in check.
 */
public boolean wouldLeaveKingInCheck(Position from, Position to, String color) {
    Piece movingPiece = getPiece(from);
    Piece capturedPiece = getPiece(to);

    board[to.getRow()][to.getCol()] = movingPiece;
    board[from.getRow()][from.getCol()] = null;

    boolean inCheck = isKingInCheck(color);

    board[from.getRow()][from.getCol()] = movingPiece;
    board[to.getRow()][to.getCol()] = capturedPiece;

    return inCheck;
}

/**
 * Checkmate!
 */
public boolean isCheckmate(String color) {
    if (!isKingInCheck(color)) {
        return false;
    }

    boolean whiteTurn = color.equals("white");

    for (int fromRow = 0; fromRow < 8; fromRow++) {
        for (int fromCol = 0; fromCol < 8; fromCol++) {
            Piece piece = board[fromRow][fromCol];

            if (piece == null || !piece.getColor().equals(color)) {
                continue;
            }

            Position from = new Position(fromRow, fromCol);

            for (int toRow = 0; toRow < 8; toRow++) {
                for (int toCol = 0; toCol < 8; toCol++) {
                    Position to = new Position(toRow, toCol);

                    if (isValidMove(from, to, whiteTurn)) {
                        return false;
                    }
                }
            }
        }
    }

    return true;
}

public boolean isValidMove(Position from, Position to) {
    if (!isInsideBoard(from) || !isInsideBoard(to)) {
        return false;
    }

    Piece piece = getPiece(from);
    if (piece == null) {
        return false;
    }

    Piece target = getPiece(to);
    if (target != null && target.getColor().equals(piece.getColor())) {
        return false;
    }

    if (!piece.isValidMove(from, to, this)) {
        return false;
    }

    if (wouldLeaveKingInCheck(from, to, piece.getColor())) {
        return false;
    }

    return true;
}


public List<Move> getAllLegalMoves(String color) {
    List<Move> legalMoves = new ArrayList<>();
    boolean whiteTurn = color.equals("white");

    for (int fromRow = 0; fromRow < 8; fromRow++) {
        for (int fromCol = 0; fromCol < 8; fromCol++) {
            Piece piece = board[fromRow][fromCol];

            if (piece == null || !piece.getColor().equals(color)) {
                continue;
            }
            Position from = new Position(fromRow, fromCol);

            for (int toRow = 0; toRow < 8; toRow++) {
                for (int toCol = 0; toCol < 8; toCol++) {
                    Position to = new Position(toRow, toCol);

                    if (isValidMove(from, to, whiteTurn)) {
                        legalMoves.add(new Move(from, to));
                    }
                }
            }
        }
    }

    return legalMoves;
}

}