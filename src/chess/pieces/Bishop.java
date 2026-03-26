package chess.pieces;

public class Bishop extends Piece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "wB" : "bB";
    }
}