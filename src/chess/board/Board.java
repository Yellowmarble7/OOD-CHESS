package chess.board;

private Piece[][] board;

public Board(){
    board = new Piece[8][8];
    setupBoard();
}