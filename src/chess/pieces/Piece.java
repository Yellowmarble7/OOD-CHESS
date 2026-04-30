package chess.pieces;

import java.util.List;
import chess.utils.Position;
import chess.board.Board;

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
 * Force subclass to define its own movement rules.
 */
    public abstract boolean isValidMove(Position from, Position to, Board board);

/**
 * Displays color for the piece.
 */
    public String getDisplaySymbol() {
        String name = getClass().getSimpleName();

        if (name.equals("Pawn")) {
            return color.equals("white") ? "\u2659" : "\u265F";
        } else if (name.equals("Rook")) {
            return color.equals("white") ? "\u2656" : "\u265C";
        } else if (name.equals("Knight")) {
            return color.equals("white") ? "\u2658" : "\u265E";
        } else if (name.equals("Bishop")) {
            return color.equals("white") ? "\u2657" : "\u265D";
        } else if (name.equals("Queen")) {
            return color.equals("white") ? "\u2655" : "\u265B";
        } else if (name.equals("King")) {
            return color.equals("white") ? "\u2654" : "\u265A";
        }

    return getSymbol();}

}