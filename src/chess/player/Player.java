package chess.player;
/**
 * Represents a player in the chess game.
 */

public class Player {
    /**
     * The color of the player (white or black).
     */
    private String color;

    /**
     * Creates a player with a given color.
     */
    public Player(String color) {
        this.color = color;
    }

    /**
     * Returns the player's color.
     */
    public String getColor() {
        return color;
    }
}