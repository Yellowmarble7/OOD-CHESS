package chess.pieces;

public class Rook extends Piece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "wR" : "bR";
    }
}