package chess.pieces;

import java.util.List;
import chess.utils.Position;

public abstract class Piece {
    protected String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract String getSymbol();

    public List<Position> possibleMoves(){
        return List.of();
    }
}