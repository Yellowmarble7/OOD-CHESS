package chess.board;

import chess.pieces.*;
import chess.util.Position;

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

public void displayBoard(){
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece piece = board[row][col];
            if (piece != null) {
                System.out.print(piece.getSymbol() + " ");
            } else {
                System.out.print(". ");
            }
        }
        System.out.println();
    }
}