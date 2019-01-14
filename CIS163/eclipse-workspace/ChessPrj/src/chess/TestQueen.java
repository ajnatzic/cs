package chess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestQueen {

	ChessPiece[][] board;
	Queen queen;
	Pawn pawn;
	Move move;
	
	public TestQueen()
	{
		board = new ChessPiece[8][8];
		queen = new Queen(Player.BLACK);
		pawn = new Pawn(Player.WHITE);
	}
	@Test
	public void testVertialUp()
	{
		board[4][4] = queen;
		move = new Move(4,4,0,4);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[1][4] = pawn;
		move = new Move(4,4,0,4);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testVertialDown()
	{
		board[4][4] = queen;
		move = new Move(4,4,7,4);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[5][4] = pawn;
		move = new Move(4,4,7,4);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testHorizontalRight()
	{
		board[4][4] = queen;
		move = new Move(4,4,4,7);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[4][5] = pawn;
		move = new Move(4,4,4,7);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testHorizontalLeft()
	{
		board[4][4] = queen;
		move = new Move(4,4,4,0);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[4][2] = pawn;
		move = new Move(4,4,4,0);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testDiagonalNE()
	{
		board[4][4] = queen;
		move = new Move(4,4,1,7);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[3][5] = pawn;
		move = new Move(4,4,1,7);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testDiagonalSE()
	{
		board[4][4] = queen;
		move = new Move(4,4,7,7);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[6][6] = pawn;
		move = new Move(4,4,7,7);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testDiagonalSW()
	{
		board[4][4] = queen;
		move = new Move(4,4,0,0);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[2][2] = pawn;
		move = new Move(4,4,0,0);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testDiagonalNW()
	{
		board[4][4] = queen;
		move = new Move(4,4,7,1);
		assertTrue("Should be true", queen.isValidMove(move, board));
		
		// Checks to see if a piece is in the way
		board[5][3] = pawn;
		move = new Move(4,4,7,1);
		assertFalse("Should be false", queen.isValidMove(move, board));
	}
	@Test
	public void testCapture()
	{
		board[4][4] = queen;
		// Check's capture in the NW direction
		board[2][2] = pawn;
		move = new Move(4,4,2,2);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the NE direction
		board[2][6] = pawn;
		move = new Move(4,4,2,6);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the SE direction
		board[6][6] = pawn;
		move = new Move(4,4,6,6);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the SW direction
		board[6][2] = pawn;
		move = new Move(4,4,6,2);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the S direction
		board[5][4] = pawn;
		move = new Move(4,4,5,4);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the N direction
		board[3][4] = pawn;
		move = new Move(4,4,3,4);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the E direction
		board[4][6] = pawn;
		move = new Move(4,4,4,6);
		assertTrue("Should be true", queen.isValidMove(move, board));
		// Check's capture in the NW direction
		board[4][2] = pawn;
		move = new Move(4,4,4,2);
		assertTrue("Should be true", queen.isValidMove(move, board));
	}
	
}
