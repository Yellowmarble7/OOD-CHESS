package chess.pieces;
/**
 * Represents a Pawn piece.
 */
public class Pawn extends Piece {
    public Pawn(String color) {
        super(color);
    }
/**
 * Returns the symbol for the pawn.
 */
    @Override
    public String getSymbol() {
        return color.equals("white") ? "wp" : "bp";
    }
}