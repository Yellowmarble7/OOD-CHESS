package chess.game;

import java.util.Scanner;
import chess.board.Board;
import chess.utils.Position;

public class ChessGame {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;

        while (true) {
            board.displayBoard();
            System.out.println((whiteTurn ? "White" : "Black") + " to move");
            System.out.print("Enter move (example: E2 E4): ");

            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                System.out.println("Invalid format. Use: E2 E4");
                continue;
            }

            try {
                Position from = new Position(parts[0]);
                Position to = new Position(parts[1]);

                var piece = board.getPiece(from);
                if (piece == null) {
                    System.out.println("No piece at that square.");
                    continue;
                }

                if (whiteTurn && !piece.getColor().equals("white")) {
                System.out.println("You must move a WHITE piece.");
                continue;
                }

                if (!whiteTurn && !piece.getColor().equals("black")) {
                    System.out.println("You must move a BLACK piece.");
                    continue;
                }
            }

                board.movePiece(from, to);
                whiteTurn = !whiteTurn;
            } catch (Exception e) {
                System.out.println("Invalid move input.");
            }
        }
    }
}