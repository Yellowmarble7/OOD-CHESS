package chess.pieces;
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
}