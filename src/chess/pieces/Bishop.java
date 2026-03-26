package chess.pieces;

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
}