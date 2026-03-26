package chess.pieces;
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
}