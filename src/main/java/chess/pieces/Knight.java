package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {

    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    public boolean validateMovement(Position from, Position to, IBoard board) {
        if (to.getRow() == (from.getRow() - 2) && to.getColumn() == (from.getColumn() - 1)) {
            return true;
        } else if (to.getRow() == (from.getRow() - 2) && to.getColumn() == (from.getColumn() + 1)) {
            return true;
        } else if (to.getRow() == (from.getRow() + 2) && to.getColumn() == (from.getColumn() - 1)) {
            return true;
        } else if (to.getRow() == (from.getRow() + 2) && to.getColumn() == (from.getColumn() + 1)) {
            return true;
        } else if (to.getRow() == (from.getRow() - 1) && to.getColumn() == (from.getColumn() - 2)) {
            return true;
        } else if (to.getRow() == (from.getRow() - 1) && to.getColumn() == (from.getColumn() + 2)) {
            return true;
        } else if (to.getRow() == (from.getRow() + 1) && to.getColumn() == (from.getColumn() - 2)) {
            return true;
        } else if (to.getRow() == (from.getRow() + 1) && to.getColumn() == (from.getColumn() + 2)) {
            return true;
        }

        return false;
    }

}
