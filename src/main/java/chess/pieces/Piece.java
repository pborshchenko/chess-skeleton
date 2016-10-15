package chess.pieces;

import chess.IBoard;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece implements IPiece {

    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    @Override
    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();

    //rook/queen
    protected boolean validateLineMovement(Position from, Position to, IBoard board) {
        if ((from.getColumn() == to.getColumn()) && (from.getRow() != to.getRow())) {
            if (to.getRow() < from.getRow()) {
                for (int newRow = (from.getRow() - 1); newRow > to.getRow(); newRow--) {
                    if (!board.isEmpty(new Position(to.getColumn(), newRow))) {
                        return false;
                    }
                }
            } else {
                for (int newRow = (from.getRow() + 1); newRow < to.getRow(); newRow++) {
                    if (!board.isEmpty(new Position(to.getColumn(), newRow))) {
                        return false;
                    }
                }
            }
        } else if ((from.getRow() == to.getRow()) && (from.getColumn() != to.getColumn())) {
            if (to.getColumn() < from.getColumn()) {
                for (int newColumn = (from.getColumn() - 1); newColumn > to.getColumn(); newColumn--) {
                    if (!board.isEmpty(new Position((char) newColumn, to.getRow()))) {
                        return false;
                    }
                }
            } else {
                for (int newColumn = (from.getColumn() + 1); newColumn < to.getColumn(); newColumn++) {
                    if (!board.isEmpty(new Position((char) newColumn, to.getRow()))) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    //bishop/queen
    protected boolean validateDiagonalMovement(Position from, Position to, IBoard board) {
        if (to.getRow() < from.getRow() && to.getColumn() < from.getColumn()) {
            if ((to.getRow() - from.getRow()) == (to.getColumn() - from.getColumn())) {
                for (int newRow = (from.getRow() - 1); newRow > to.getRow(); newRow--) {
                    char newColumn = (char) (from.getColumn() - (from.getRow() - newRow));
                    if (!board.isEmpty(new Position(newColumn, newRow))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (to.getRow() < from.getRow() && to.getColumn() > from.getColumn()) {
            if ((to.getRow() - from.getRow()) == (from.getColumn() - to.getColumn())) {
                for (int newRow = (from.getRow() - 1); newRow > to.getRow(); newRow--) {
                    char newColumn = (char) (from.getColumn() + (from.getRow() - newRow));
                    if (!board.isEmpty(new Position(newColumn, newRow))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (to.getRow() > from.getRow() && to.getColumn() < from.getColumn()) {
            if ((from.getRow() - to.getRow()) == (to.getColumn() - from.getColumn())) {
                for (int newRow = (from.getRow() + 1); newRow < to.getRow(); newRow++) {
                    char newColumn = (char) (from.getColumn() - (newRow - from.getRow()));
                    if (!board.isEmpty(new Position(newColumn, newRow))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (to.getRow() > from.getRow() && to.getColumn() > from.getColumn()) {
            if ((from.getRow() - to.getRow()) == (from.getColumn() - to.getColumn())) {
                for (int newRow = (from.getRow() + 1); newRow < to.getRow(); newRow++) {
                    char newColumn = (char) (from.getColumn() + (newRow - from.getRow()));
                    if (!board.isEmpty(new Position(newColumn, newRow))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(getIdentifyingCharacter());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (owner != piece.owner) return false;
        if (getIdentifyingCharacter() != piece.getIdentifyingCharacter()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return owner.hashCode();
    }
}
