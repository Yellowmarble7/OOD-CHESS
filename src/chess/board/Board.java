package chess.board;

import chess.pieces.*;
import chess.utils.Position;

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
    public void resetBoard() {
    board = new Piece[8][8];
    setupBoard();
    }

    public void clearBoard() {
        board = new Piece[8][8];
    }

    public void setPiece(Position pos, Piece piece) {
        board[pos.getRow()][pos.getCol()] = piece;
    }
}