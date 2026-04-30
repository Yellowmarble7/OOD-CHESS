package chess.pieces;
import chess.board.Board;
import chess.utils.Position;
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

/**
 * isValidMove for Pawn.
 */
@Override
public boolean isValidMove(Position from, Position to, Board board) {
    int direction = color.equals("white") ? -1 : 1;
    int startRow = color.equals("white") ? 6 : 1;

    int rowDiff = to.getRow() - from.getRow();
    int colDiff = Math.abs(to.getCol() - from.getCol());

    // move forward 1
    if (colDiff == 0 && rowDiff == direction && board.getPiece(to) == null) {
        return true;
    }

    // move forward 2 from starting row
    if (colDiff == 0 && from.getRow() == startRow && rowDiff == 2 * direction) {
        Position middle = new Position(from.getRow() + direction, from.getCol());
        return board.getPiece(middle) == null && board.getPiece(to) == null;
    }

    // diagonal capture
    if (colDiff == 1 && rowDiff == direction && board.getPiece(to) != null) {
        return !board.getPiece(to).getColor().equals(color);
    }

    return false;
}
}