package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    public boolean validateMovement(Position from, Position to, IBoard board) {
        return validateLineMovement(from, to, board);
    }


}
