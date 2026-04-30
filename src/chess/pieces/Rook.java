package chess.pieces;
import chess.board.Board;
import chess.utils.Position;
/**
 * Represents a Rook chess piece.
 */
public class Rook extends Piece {
     /**
     * Creates a Rook with a specified color.
     */
    public Rook(String color) {
        super(color);
    }
/**
     * Returns the symbol for the rook.
     */
    @Override
    public String getSymbol() {
        return color.equals("white") ? "wR" : "bR";
    }
/**
     * isValidMove for Rook.
     */
    @Override
public boolean isValidMove(Position from, Position to, Board board) {
    boolean straight = from.getRow() == to.getRow() || from.getCol() == to.getCol();
    return straight && board.isPathClear(from, to);
}
}