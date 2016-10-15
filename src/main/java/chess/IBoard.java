package chess;

import chess.pieces.IPiece;

import java.util.List;

public interface IBoard {

    void initializePieces();

    IPiece getPieceAt(Position position);

    boolean isEmpty(Position position);

    List<Movement> getPossibleMovements(Player owner);

    boolean move(Position from, Position to, Player currentPlayer);

    boolean validateCheck(Player owner);

    boolean validateMate(Player owner);

}
