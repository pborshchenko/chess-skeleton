package chess;

import chess.pieces.Bishop;
import chess.pieces.IPiece;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Board implements IBoard {

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, IPiece> positionToPieceMap;

    private List<IPiece> killed;

    public Board() {
        positionToPieceMap = new HashMap<>();
        killed = new ArrayList<>();
    }

    @Override
    public void initializePieces() {
        positionToPieceMap.clear();
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    @Override
    public List<Movement> getPossibleMovements(Player owner) {
        return positionToPieceMap.entrySet().stream()
                .filter(e -> e.getValue().getOwner() == owner)
                .flatMap(e -> {
                    IPiece piece = e.getValue();
                    Position from = e.getKey();
                    return getPossibleMovementsForPiece(piece, from).stream();
                })
                .collect(toList());
    }

    private List<Movement> getPossibleMovementsForPiece(IPiece piece, Position from) {
        List<Movement> values = new ArrayList<>();
        for (int i = Position.MIN_ROW; i <= Position.MAX_ROW; i++) {
            for (char j = Position.MIN_COLUMN; j <= Position.MAX_COLUMN; j++) {
                Position to = new Position(j, i);
                if (piece.validateMovement(from, to, this) &&
                        isEmptyForPlayer(to, piece.getOwner())) {
                    values.add(new Movement(piece, from, to));
                }
            }
        }
        return values;
    }


    @Override
    public boolean move(Position from, Position to, Player currentPlayer) {
        IPiece fromPiece = getPieceAt(from);
        if (fromPiece == null ||
                fromPiece.getOwner() != currentPlayer) {
            return false;
        }

        if (fromPiece.validateMovement(from, to, this) &&
                (isEmpty(to) || isEmptyForPlayer(to, fromPiece.getOwner()))) {
                return move(from, to, fromPiece);
        }

        return false;
    }

    private boolean move(Position from, Position to, IPiece piece) {
        IPiece occupied = getPieceAt(to);

        positionToPieceMap.remove(from);
        positionToPieceMap.put(to, piece);

        if (!validateCheck(piece.getOwner())) {
            if (occupied != null) {
                killed.add(occupied);
            }
            return true;
        } else {
            positionToPieceMap.remove(to);
            if (occupied != null) {
                positionToPieceMap.put(to, occupied);
            }
            positionToPieceMap.put(from, piece);
            return false;
        }
    }

    @Override
    public boolean isEmpty(Position position) {
        return getPieceAt(position) == null;
    }

    private boolean isEmptyForPlayer(Position position, Player owner) {
        IPiece occupied = getPieceAt(position);
        if (occupied != null) {
            return owner != occupied.getOwner();
        }
        return true;
    }

    @Override
    public boolean validateCheck(Player opponent) {
        Position opponentKingPosition = positionToPieceMap.entrySet().stream()
                .filter(e -> e.getValue().getOwner() == opponent)
                .filter(e -> Character.toLowerCase(e.getValue().getIdentifier()) == 'k')
                .map(e -> e.getKey())
                .findFirst().get();
        return positionToPieceMap.entrySet().stream()
                .filter(e -> e.getValue().getOwner() != opponent)
                .anyMatch(e -> {
                    IPiece piece = e.getValue();
                    Position from = e.getKey();
                    return piece.validateMovement(from, opponentKingPosition, this);
                });
    }

    @Override
    public boolean validateMate(Player opponent) {
        if (validateCheck(opponent)) {
            //validate possible new position for opponents king
            return !positionToPieceMap.entrySet().stream()
                    .filter(e -> e.getValue().getOwner() == opponent)
                    .filter(e -> Character.toLowerCase(e.getValue().getIdentifier()) == 'k')
                    .flatMap(e -> {
                        IPiece piece = e.getValue();
                        Position from = e.getKey();
                        return getPossibleMovementsForPiece(piece, from).stream();
                    })
                    .map(m -> m.getTo())
                    .filter(to -> positionToPieceMap.entrySet().stream()
                            .filter(e -> e.getValue().getOwner() != opponent)
                            .noneMatch(e -> {
                                IPiece piece = e.getValue();
                                Position from = e.getKey();
                                return piece.validateMovement(from, to, this);
                            }))
                    .findAny()
                    .isPresent();
        }

        return false;
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public IPiece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(IPiece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

}
