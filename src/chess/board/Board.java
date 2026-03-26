package chess.board;

private Piece[][] board;

public Board(){
    board = new Piece[8][8];
    setupBoard();
}

public Piece getPiece(Position pos){
    return board[pos.getRow()][pos.getCol()];
}

public Piece movePiece(Position from, Position to){
    Piece piece = board[from.getRow()][from.getCol()];
    board[from.getRow()][from.getCol()] = null;
    board[to.getRow()][to.getCol()] = piece;
    return piece;
}