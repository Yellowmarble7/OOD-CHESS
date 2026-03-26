package chess.pieces;
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
}