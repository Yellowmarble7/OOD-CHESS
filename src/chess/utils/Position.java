package chess.utils;

public class Position{
    private int row;
    private int col;

    //note to self: position 8 = row 0, 7 = row 1, etc.
    
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}