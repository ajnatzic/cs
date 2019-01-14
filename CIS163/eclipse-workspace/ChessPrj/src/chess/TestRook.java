package chess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestRook
{
	ChessPiece[][] board;
	Rook rook;
	Pawn pawn;
	Move move;
	
	public TestRook()
	{
		board = new ChessPiece[8][8];
		rook = new Rook(Player.WHITE);
		pawn = new Pawn(Player.BLACK);
	}
	@Test
	public void testVerticallyDown()
	{
		board[1][0] = rook;
		move = new Move(1,0,6,0);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		move = new Move(1,0,4,0);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		// checks to see if the move is invalid due to another peace
		board[3][0] = pawn;
		move = new Move(1,0,6,0);
		assertFalse("Should be false", rook.isValidMove(move, board));
	}
	@Test
	public void testVerticallyUp()
	{
		board[6][0] = rook;
		move = new Move(6,0,1,0);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		move = new Move(6,0,5,0);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		// checks to see if the move is invalid due to another peace
		board[3][0] = pawn;
		move = new Move(6,0,1,0);
		assertFalse("Should be false", rook.isValidMove(move, board));
	}
	@Test
	public void testHorizontallyRight()
	{
		board[4][4] = rook;
		move = new Move(4,4,4,6);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		move = new Move(4,4,4,7);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		// checks to see if the move is invalid due to another peace
		board[4][5] = pawn;
		move = new Move(4,4,4,6);
		assertFalse("Should be false", rook.isValidMove(move, board));
	}
	@Test
	public void testHorizontallyLeft()
	{
		board[4][4] = rook;
		move = new Move(4,4,4,0);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		move = new Move(4,4,4,3);
		assertTrue("Should return true", rook.isValidMove(move, board));
		
		// checks to see if the move is invalid due to another peace
		board[4][3] = pawn;
		move = new Move(4,4,4,1);
		assertFalse("Should be false", rook.isValidMove(move, board));
	}
	@Test
	public void testCapture()
	{
		board[0][0] = rook;
		board[2][0] = pawn;
		move = new Move(0,0,2,0);
		
		assertTrue("Should be true", rook.isValidMove(move, board));
	}
	@Test
	public void testDiagonal()
	{
		board[4][4] = rook;
		
		// Checks the north-east direction
		move = new Move(4,4,2,6);
		assertFalse("Should be false", rook.isValidMove(move, board));
		// Checks the South-east direction
		move = new Move(4,4,6,6);
		assertFalse("Should be false", rook.isValidMove(move, board));
		// Checks the south-west direction
		move = new Move(4,4,6,2);
		assertFalse("Should be false", rook.isValidMove(move, board));
		// Checks the north-west direction
		move = new Move(4,4,2,2);
		assertFalse("Should be false", rook.isValidMove(move, board));
	}
}
