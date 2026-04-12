package chess.pieces;
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
}