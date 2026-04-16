package chess.pieces;

import java.util.List;
import chess.utils.Position;

/**
 * Abstract base class representing a chess piece.
 * Stores the piece color and defines common behavior.
 */
public abstract class Piece {
    protected String color;
/**
 * Creates a piece with a given color.
 */
    public Piece(String color) {
        this.color = color;
    }
/**
 * Gets the color of the piece.
 */
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

/**
 * Displays color for the piece.
 */
    public String getDisplaySymbol() {
        String name = getClass().getSimpleName();

        if (name.equals("Pawn")) {
            return color.equals("white") ? "♙" : "♟";
        } else if (name.equals("Rook")) {
            return color.equals("white") ? "♖" : "♜";
        } else if (name.equals("Knight")) {
            return color.equals("white") ? "♘" : "♞";
        } else if (name.equals("Bishop")) {
            return color.equals("white") ? "♗" : "♝";
        } else if (name.equals("Queen")) {
            return color.equals("white") ? "♕" : "♛";
        } else if (name.equals("King")) {
            return color.equals("white") ? "♔" : "♚";
        }

    return getSymbol();}

}