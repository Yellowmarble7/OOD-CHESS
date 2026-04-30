package chess.pieces;
import chess.board.Board;
import chess.utils.Position;

/**
 * Represents a Bishop chess piece.
 */
public class Bishop extends Piece {
    /**
     * Creates a Bishop with a specified color.
     */
    public Bishop(String color) {
        super(color);
    }

    /**
     * Returns the symbol for the bishop.
     */
    @Override
    public String getSymbol() {
        return color.equals("white") ? "wB" : "bB";
    }

/**
 * isValidMove for Bishop.
 */
    @Override
public boolean isValidMove(Position from, Position to, Board board) {
    int rowDiff = Math.abs(from.getRow() - to.getRow());
    int colDiff = Math.abs(from.getCol() - to.getCol());

    return rowDiff == colDiff && board.isPathClear(from, to);
}
}