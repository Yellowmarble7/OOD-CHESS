package chess.game;

import java.util.Scanner;
import chess.board.Board;
import chess.utils.Position;

public class ChessGame {
    private Board board;
    private Scanner scanner;

    public ChessGame() {
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println(board);
            System.out.print("Enter your move (e.g., e2 e4): ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            processMove(input);
        }
    }

    private void processMove(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid move format.");
            return;
        }
        Position from = Position.fromString(parts[0]);
        Position to = Position.fromString(parts[1]);
        if (from == null || to == null) {
            System.out.println("Invalid position.");
            return;
        }
        board.movePiece(from, to);
    }
}