package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {

    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public boolean validateMovement(Position from, Position to, IBoard board) {
        //If moving in wrong direction
        if ((getOwner() == Player.Black && to.getRow() >= from.getRow()) ||
                (getOwner() == Player.White && to.getRow() <= from.getRow())) {
            return false;
        //If only moving one place side ways
        } else if (to.getColumn() != from.getColumn()) {
            if ((to.getColumn() > from.getColumn() && to.getColumn() == (from.getColumn() + 1)) ||
                    (to.getColumn() < from.getColumn() && to.getColumn() == (from.getColumn() - 1))) {
                if ((to.getRow() == (from.getRow() + 1) && getOwner() == Player.White) ||
                        (to.getRow() == (from.getRow() - 1) && getOwner() == Player.Black)) {
                    if (board.isEmpty(to)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        //If moved two or more places
        } else if ((getOwner() == Player.Black && to.getRow() < (from.getRow() - 1)) ||
                (getOwner() == Player.White && to.getRow() > (from.getRow() + 1))) {
            //If moved two places
            if ((getOwner() == Player.Black && to.getRow() == (from.getRow() - 2)) ||
                    (getOwner() == Player.White && to.getRow() == (from.getRow() + 2))) {
                if ((getOwner() == Player.Black && from.getRow() != 7) ||
                        (getOwner() == Player.White && from.getRow() != 2)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

}
