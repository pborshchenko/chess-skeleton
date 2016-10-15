package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {

    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    public boolean validateMovement(Position from, Position to, IBoard board) {
        if (to.getRow() == (from.getRow() + 1) && to.getColumn() == from.getColumn()) {
            return true;
        } else if (to.getRow() == (from.getRow() + 1) && to.getColumn() == (from.getColumn() - 1)) {
            return true;
        } else if (to.getRow() == from.getRow() && to.getColumn() == from.getColumn() - 1) {
            return true;
        } else if (to.getRow() == (from.getRow() - 1) && to.getColumn() == (from.getColumn() - 1)) {
            return true;
        } else if (to.getRow() == (from.getRow() - 1) && to.getColumn() == from.getColumn()) {
            return true;
        } else if (to.getRow() == (from.getRow() - 1) && to.getColumn() == (from.getColumn() + 1)) {
            return true;
        } else if (to.getRow() == from.getRow() && to.getColumn() == (from.getColumn() + 1)) {
            return true;
        } else if (to.getRow() == (from.getRow() + 1) && to.getColumn() == (from.getColumn() + 1)) {
            return true;
        }

        return false;
    }

}
