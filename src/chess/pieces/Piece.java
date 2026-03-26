package chess.pieces;

import java.util.List;
import chess.utils.Position;

/**
 * Abstract base class representing a chess piece.
 * Stores the piece color and defines common behavior.
 */
public abstract class Piece {
    protected String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

/**
 * Returns the string representation of the piece.
 */
    public abstract String getSymbol();

    public List<Position> possibleMoves(){
        return List.of();
    }
}