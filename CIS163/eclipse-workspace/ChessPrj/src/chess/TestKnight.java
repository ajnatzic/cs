package chess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestKnight {

	ChessPiece[][] board;
	Knight knight;
	Pawn pawn;
	Move move;
	
	public TestKnight()
	{
		board = new ChessPiece[8][8];
		knight = new Knight(Player.WHITE);
		pawn = new Pawn(Player.BLACK);
	}
	@Test
	public void testDownRight()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 6, 5);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 4, 5);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testDownLeft()
	{
		board[3][4] = knight;
		// Checks a correct movement
		move = new Move(3,4, 5, 3);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(3,4, 2, 7);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testUpRight()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 2, 5);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 0, 0);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testUpLeft()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 2, 3);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 7, 7);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testRightUp()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 3, 6);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 0, 0);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testRightDown()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 5, 6);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 0, 0);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testLeftUp()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 3, 2);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 0, 0);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testLeftDown()
	{
		board[4][4] = knight;
		// Checks a correct movement
		move = new Move(4,4, 5, 2);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Checks an incorrect movement
		move = new Move(4,4, 4, 3);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	@Test
	public void testCapture()
	{
		board[4][4] = knight;
		// Test capture going up and left
		board[2][3] = pawn;
		move = new Move(4,4,2,3);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going up and right
		board[2][5] = pawn;
		move = new Move(4,4,2,5);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going right and up
		board[3][6] = pawn;
		move = new Move(4,4,3,6);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going right and down
		board[5][6] = pawn;
		move = new Move(4,4,5,6);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going down and right
		board[6][5] = pawn;
		move = new Move(4,4,6,5);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going down and left
		board[6][3] = pawn;
		move = new Move(4,4,6,3);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going left and down
		board[5][2] = pawn;
		move = new Move(4,4,5,2);
		assertTrue("Should be true", knight.isValidMove(move, board));
		// Test capture going left and down
		board[3][2] = pawn;
		move = new Move(4,4,3,2);
		assertTrue("Should be true", knight.isValidMove(move, board));
		
	}
	@Test
	public void testThis()
	{
		board[7][1] = knight;
		move = new Move(7,1,0,4);
		assertFalse("Should be false", knight.isValidMove(move, board));
	}
	

}
