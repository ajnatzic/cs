package chess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPawn {

	ChessPiece[][] board;
	Pawn white_pawn;
	Pawn black_pawn;
	Move move;
	
	public TestPawn()
	{
		board = new ChessPiece[8][8];
		black_pawn = new Pawn(Player.BLACK);
		white_pawn = new Pawn(Player.WHITE);
	}
	@Test
	public void testMovement()
	{
		// Sets pawn in starting position
		board[1][0] = black_pawn;
		// Checks movement by one square
		move = new Move(1,0,2,0);
		assertTrue("Should be true", black_pawn.isValidMove(move, board));
		// Checks movement by two squares
		move = new Move(1,0,3,0);
		assertTrue("Should be true", black_pawn.isValidMove(move, board));
		// Sets pawn in non-starting position
		board[3][4] = black_pawn;
		// Checks movement by one square
		move = new Move(3,4,4,4);
		assertTrue("Should be true", black_pawn.isValidMove(move, board));
		// Checks movement by two squares
		move = new Move(3,4,5,4);
		assertFalse("Should be false", black_pawn.isValidMove(move, board));
		// Checks a random movement
		move = new Move(3,4,0,0);
		assertFalse("Should be false", black_pawn.isValidMove(move, board));
		
		
		// Sets pawn in starting position
		board[6][0] = white_pawn;
		// Checks movement by one square
		move = new Move(6,0,5,0);
		assertTrue("Should be true", white_pawn.isValidMove(move, board));
		// Checks movement by two squares
		move = new Move(6,0,4,0);
		assertTrue("Should be true", white_pawn.isValidMove(move, board));
		// Sets pawn in non-starting position
		board[5][5] = white_pawn;
		// Checks movement by one square
		move = new Move(5,5,4,5);
		assertTrue("Should be true", white_pawn.isValidMove(move, board));
		// Checks movement by two squares
		move = new Move(5,5,3,5);
		assertFalse("Should be false", white_pawn.isValidMove(move, board));
		// Checks a random movement
		move = new Move(5,5,0,0);
		assertFalse("Should be false", black_pawn.isValidMove(move, board));
	}
	@Test
	public void testCapture()
	{
		// Checks the black pawn's capture mechanism
		board[4][4] = black_pawn;
		board[5][5] = white_pawn;
		move = new Move(4,4,5,5);
		assertTrue("Should be true", black_pawn.isValidMove(move, board));
		
		// Checks the white pawn's capture mechanism
		board[4][4] = white_pawn;
		board[3][3] = black_pawn;
		move = new Move(4,4,3,3);
		assertTrue("Should be true", white_pawn.isValidMove(move, board));
		
	}
}