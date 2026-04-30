package chess.pieces;

import chess.board.Board;
import chess.utils.Position;

/**
 * Represents a Knight chess piece.
 */
public class Knight extends Piece {
     /**
     * Creates a Knight with a specified color.
     */
    public Knight(String color) {
        super(color);
    }
/**
     * Returns the symbol for the knight.
     */
    @Override
    public String getSymbol() {
        return color.equals("white") ? "wN" : "bN";
    }

/**
     * Checks if knight move is valid.
     */
    @Override
public boolean isValidMove(Position from, Position to, Board board) {
    int rowDiff = Math.abs(from.getRow() - to.getRow());
    int colDiff = Math.abs(from.getCol() - to.getCol());

    return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
}
}