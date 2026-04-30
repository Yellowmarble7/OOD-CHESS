package chess.pieces;
import chess.board.Board;
import chess.utils.Position;
/**
 * Represents a King chess piece.
 */
public class King extends Piece {
 /**
 * Creates a King with a specified color.
 */
    public King(String color) {
        super(color);
    }
/**
 * Returns the symbol for the king.
 */
    @Override
    public String getSymbol() {
        return color.equals("white") ? "wK" : "bK";
    }

/**
 * isValidMove for King.
 */
    @Override
public boolean isValidMove(Position from, Position to, Board board) {
    int rowDiff = Math.abs(from.getRow() - to.getRow());
    int colDiff = Math.abs(from.getCol() - to.getCol());

    return rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);
}
}