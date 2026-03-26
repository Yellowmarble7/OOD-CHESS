package chess.pieces;

import java.util.List;
import chess.utils.Position;

public abstract class Piece {
    protected String color;
    protected Position position;

    public Piece(String color, Position position) {
        this.color = color;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public abstract List<Position> getValidMoves();

    public String getSymbol() {
        return "";
    }
}