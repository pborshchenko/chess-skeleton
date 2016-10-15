package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece {

    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    public boolean validateMovement(Position from, Position to, IBoard board) {
        if (from.getRow() == to.getRow() ^ from.getColumn() == to.getColumn()) {
            return validateLineMovement(from, to, board);
        } else if (from.getRow() != to.getRow() && from.getColumn() != to.getColumn()) {
            return validateDiagonalMovement(from, to, board);
        }
        return false;
    }

}
