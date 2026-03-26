package chess.utils;

/**
 * Represents a position on the chess board using row and column indices.
 */
public class Position{
    private int row;
    private int col;

    //note to self: position 8 = row 0, 7 = row 1, etc.

/**
 * Creates a position using row and column.
 */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
/**
 * Converts a chess coordinate (e.g., "E2") into row and column indices.
 */
    public Position(String pos){
        char letter = pos.charAt(0);
        char number = pos.charAt(1);

        letter = Character.toUpperCase(letter);
        this.col = letter - 'A';
        this.row = 8 - (number - '0'); //because of ASCII
    }

/**
 * Returns the row index.
 */
    public int getRow() {
        return row;
    }
/**
 * Returns the column index.
 */
    public int getCol() {
        return col;
    }
}