package chess.pieces;
import chess.board.Board;
import chess.utils.Position;
/**
 * Represents a Queen chess piece.
 */
public class Queen extends Piece {
     /**
     * Creates a Queen with a specified color.
     */
    public Queen(String color) {
        super(color);
    }
/**
     * Returns the symbol for the queen.
     */
    @Override
    public String getSymbol() {
        return color.equals("white") ? "wQ" : "bQ";
    }

/**
     * isValidMove for Queen.
     */
    @Override
public boolean isValidMove(Position from, Position to, Board board) {
    int rowDiff = Math.abs(from.getRow() - to.getRow());
    int colDiff = Math.abs(from.getCol() - to.getCol());

    boolean diagonal = rowDiff == colDiff;
    boolean straight = from.getRow() == to.getRow() || from.getCol() == to.getCol();

    return (diagonal || straight) && board.isPathClear(from, to);
}
}