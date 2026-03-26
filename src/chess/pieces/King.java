package chess.pieces;

public class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "wK" : "bK";
    }
}