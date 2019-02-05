package chess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestKing {

	ChessPiece[][] board;
	King king;
	Pawn pawn;
	Move move;
	
	public TestKing()
	{
		board = new ChessPiece[8][8];
		king = new King(Player.WHITE);
		pawn = new Pawn(Player.BLACK);
	}
	@Test
	public void testForward()
	{
		board[0][0] = king;
		move = new Move(0,0,1,0);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(0,0,2,0);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[1][0] = pawn;
		move = new Move(0,0,1,0);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testBackwards()
	{
		board[2][0] = king;
		move = new Move(2, 0, 1, 0);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(2,0,0,0);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[1][0] = pawn;
		move = new Move(2,0,1,0);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testRight()
	{
		board[2][2] = king;
		move = new Move(2, 2, 2, 3);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(2,2,2,4);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[2][3] = pawn;
		move = new Move(2,2,2,3);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testLeft()
	{
		board[2][2] = king;
		move = new Move(2, 2, 2, 1);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(2,2,2,0);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[2][1] = pawn;
		move = new Move(2,2,2,1);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testDiagonalNW()
	{
		board[4][4] = king;
		move = new Move(4, 4, 3, 3);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(4,4,2,2);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[3][3] = pawn;
		move = new Move(4,4,3,3);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testDiagonalNE()
	{
		board[4][4] = king;
		move = new Move(4, 4, 3, 5);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(4,4,2,6);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[3][5] = pawn;
		move = new Move(4,4,3,5);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testDiagonalSE()
	{
		board[4][4] = king;
		move = new Move(4, 4, 5, 5);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(4,4,6,6);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[5][5] = pawn;
		move = new Move(4,4,5,5);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
	@Test
	public void testDiagonalSW()
	{
		board[4][4] = king;
		move = new Move(4, 4, 5, 3);
		assertTrue("Should be true", king.isValidMove(move, board));
		// Checks to see if player is attempting to move more than one space
		move = new Move(4,4,6,2);
		assertFalse("Should be false", king.isValidMove(move, board));
		// Checks for capture
		board[5][3] = pawn;
		move = new Move(4,4,5,3);
		assertTrue("Should be true", king.isValidMove(move, board));
	}
}
