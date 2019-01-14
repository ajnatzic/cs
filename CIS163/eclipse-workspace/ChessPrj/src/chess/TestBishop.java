package chess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBishop {

	ChessPiece[][] board;
	Bishop bishop;
	Pawn pawn;
	Move move;
	
	public TestBishop()
	{
		board = new ChessPiece[8][8];
		bishop = new Bishop(Player.BLACK);
		pawn = new Pawn(Player.WHITE);
	}
	
	@Test
	public void testDiagonalNE()
	{
		board[7][0] = bishop;
		move = new Move(7,0,4,3);
		assertTrue("should be true", bishop.isValidMove(move, board));
		
		move = new Move(7,0,0,7);
		assertTrue("should be true", bishop.isValidMove(move, board));
		// Checks to make sure the move is invalid
		board[2][5] = pawn;
		move = new Move(7,0,0,7);
		assertFalse("Should be false", bishop.isValidMove(move, board));
	}
	@Test
	public void testDiagonalSE()
	{
		board[0][0] = bishop;
		move = new Move(0,0,3,3);
		assertTrue("should be true", bishop.isValidMove(move, board));
		
		move = new Move(0,0,7,7);
		assertTrue("should be true", bishop.isValidMove(move, board));
		// Checks to make sure the move is invalid
		board[3][3] = pawn;
		move = new Move(0,0,7,7);
		assertFalse("Should be false", bishop.isValidMove(move, board));
	}
	@Test
	public void testDiagonalSW()
	{
		board[0][7] = bishop;
		move = new Move(0,7,2,5);
		assertTrue("should be true", bishop.isValidMove(move, board));
		
		move = new Move(0,7,7,0);
		assertTrue("should be true", bishop.isValidMove(move, board));
		// Checks to make sure the move is invalid
		board[3][4] = pawn;
		move = new Move(0,7,5,2);
		assertFalse("Should be false", bishop.isValidMove(move, board));
	}
	@Test
	public void testDiagonalNW()
	{
		board[7][7] = bishop;
		move = new Move(7,7,5,5);
		assertTrue("should be true", bishop.isValidMove(move, board));
		
		move = new Move(7,7,2,2);
		assertTrue("should be true", bishop.isValidMove(move, board));
		// Checks to make sure the move is invalid
		board[2][2] = pawn;
		move = new Move(7,7,0,0);
		assertFalse("Should be false", bishop.isValidMove(move, board));
		move = new Move(7,7,1,1);
		assertFalse("Should be false", bishop.isValidMove(move, board));
	}
	@Test 
	public void testStraight()
	{
		board[7][7] = bishop;
		move = new Move(7,7,0,7);
		assertFalse("should be false", bishop.isValidMove(move, board));
		
		board[0][0] = bishop;
		move = new Move(0,0,0,7);
		assertFalse("should be false", bishop.isValidMove(move, board));
	}
	@Test
	public void testCapture()
	{
		board[0][0] = bishop;
		board[3][3] = pawn;
		move = new Move(0,0,3,3);
		assertTrue("Should be true", bishop.isValidMove(move, board));
	}

}
