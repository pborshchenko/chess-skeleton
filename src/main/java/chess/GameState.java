package chess;

import chess.pieces.*;

import java.util.List;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer;

    private IBoard board;

    /**
     * Create the game state.
     */
    public GameState() {
        board = new Board();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void start() {
        setCurrentPlayer(Player.White);
        board.initializePieces();
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public IPiece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return board.getPieceAt(position);
    }

    public List<Movement> getPossibleMovements() {
        return board.getPossibleMovements(getCurrentPlayer());
    }

    public boolean move(Position from, Position to) {
        Player currentPlayer = getCurrentPlayer();
        boolean result = board.move(from, to, currentPlayer);
        if (result) {
            if (currentPlayer == Player.Black) {
                setCurrentPlayer(Player.White);
            } else {
                setCurrentPlayer(Player.Black);
            }
        }
        return result;
    }

    public boolean validateCheck() {
        return board.validateCheck(getCurrentPlayer());
    }

    boolean validateMate() {
        return board.validateMate(getCurrentPlayer());
    }

}
