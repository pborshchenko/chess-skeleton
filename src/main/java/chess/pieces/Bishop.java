package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {

    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    public boolean validateMovement(Position from, Position to, IBoard board) {
        return validateDiagonalMovement(from, to, board);
    }

}
