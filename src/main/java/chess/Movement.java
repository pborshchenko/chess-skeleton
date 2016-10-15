package chess;

import chess.pieces.IPiece;

public class Movement {

    private final IPiece piece;
    private final Position from;
    private final Position to;

    public Movement(IPiece piece, Position from, Position to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public IPiece getMovingPiece() {
        return piece;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movement movement = (Movement) o;

        if (!from.equals(movement.from)) return false;
        if (!piece.equals(movement.piece)) return false;
        if (!to.equals(movement.to)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = piece.hashCode();
        result = 31 * result + from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Movement for Piece " + piece + " [from=" + from + ", to=" + to
                + "]";
    }

}