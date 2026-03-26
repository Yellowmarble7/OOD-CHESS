package chess.utils;

public class Position{
    private int row;
    private int col;

    //note to self: position 8 = row 0, 7 = row 1, etc.

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position(String pos){
        char letter = pos.charAt(0);
        char number = pos.charAt(1);

        this.col = letter - 'A';
        this.row = 8 - (number - '0'); //because of ASCII
        letter = Character.toUpperCase(letter);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}