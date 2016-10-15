package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

public interface IPiece {

    char getIdentifier();

    Player getOwner();

    boolean validateMovement(Position from, Position to, IBoard board);


}
