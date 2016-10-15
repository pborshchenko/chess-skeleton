package chess;

import chess.pieces.IPiece;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Basic unit tests for the Board class
 */
public class BoardTest {

    private IBoard board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testValidateNotCheck() {
        // Start the game
        board.initializePieces();

        board.move(new Position("f2"), new Position("f3"), Player.White);
        board.move(new Position("e7"), new Position("e5"), Player.Black);
        board.move(new Position("g2"), new Position("g4"), Player.White);

        assertTrue("Should not be a Check", !board.validateCheck(Player.Black));
    }

    @Test
    public void testValidateNotMate() {
        // Start the game
        board.initializePieces();

        board.move(new Position("f2"), new Position("f3"), Player.White);
        board.move(new Position("e7"), new Position("e5"), Player.Black);
        board.move(new Position("g2"), new Position("g4"), Player.White);

        assertTrue("Should not be a Mate", !board.validateMate(Player.Black));
    }

    @Test
    public void testValidateCheck() {
        // Start the game
        board.initializePieces();

        board.move(new Position("f2"), new Position("f3"), Player.White);
        board.move(new Position("e7"), new Position("e5"), Player.Black);
        board.move(new Position("g2"), new Position("g4"), Player.White);
        board.move(new Position("d8"), new Position("h4"), Player.Black);

        assertTrue("Should be a Check", board.validateCheck(Player.White));
    }

    @Test
    public void testValidateMate() {
        // Start the game
        board.initializePieces();

        board.move(new Position("f2"), new Position("f3"), Player.White);
        board.move(new Position("e7"), new Position("e5"), Player.Black);
        board.move(new Position("g2"), new Position("g4"), Player.White);
        board.move(new Position("d8"), new Position("h4"), Player.Black);

        assertTrue("Should be a Mate", board.validateMate(Player.White));
    }

    @Test
    public void testMove() {
        // Start the game
        board.initializePieces();

        board.move(new Position("f2"), new Position("f3"), Player.White);

        IPiece whitePawn= board.getPieceAt(new Position("f3"));
        assertTrue("A rook should be at a1", whitePawn instanceof Pawn);
        assertEquals("The rook at a1 should be owned by White", Player.White, whitePawn.getOwner());
    }

    @Test
    public void testGetPossibleMovements() {
        // Start the game
        board.initializePieces();

        List<Movement> expected = new ArrayList<>();
        expected.add(new Movement(new Knight(Player.White), new Position("b1"), new Position("a3")));
        expected.add(new Movement(new Knight(Player.White), new Position("b1"), new Position("c3")));
        expected.add(new Movement(new Knight(Player.White), new Position("g1"), new Position("f3")));
        expected.add(new Movement(new Knight(Player.White), new Position("g1"), new Position("h3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("a2"), new Position("a3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("a2"), new Position("a4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("b2"), new Position("b3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("b2"), new Position("b4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("c2"), new Position("c3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("c2"), new Position("c4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("d2"), new Position("d3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("d2"), new Position("d4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("e2"), new Position("e3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("e2"), new Position("e4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("f2"), new Position("f3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("f2"), new Position("f4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("g2"), new Position("g3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("g2"), new Position("g4")));
        expected.add(new Movement(new Pawn(Player.White), new Position("h2"), new Position("h3")));
        expected.add(new Movement(new Pawn(Player.White), new Position("h2"), new Position("h4")));

        List<Movement> movements =  board.getPossibleMovements(Player.White);

        assertEquals(movements, expected);
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", board.getPieceAt(new Position(String.valueOf(col) + row)));
            }
        }
    }

    @Test
    public void testInitialPositions() {
        // Start the game
        board.initializePieces();

        // Spot check a few pieces
        IPiece whiteRook = board.getPieceAt(new Position("a1"));
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());

        IPiece blackQueen = board.getPieceAt(new Position("d8"));
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }
}
