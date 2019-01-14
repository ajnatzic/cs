package project2.threeD;

import java.awt.Point;


/**
 * This class handles all of the functions of Connect Four in 3D
 * 
 * @author AJ Natzic
 *
 */
public class ConnectFourGame3D {

	/** the 3D array of the game board */
	private int[][][] board;
	
	/** the size of the 3D array board */
	private int size;
	
	/** this integer keeps track of which player's turn it is */
	private int player;
	
	/** amount of players playing */
	private int playerCount;
	
	/** final integer that represents player 0 (The user in single-player games) */
	private static final int USER = 0;
	
	/** final integer that represents player 1 (The computer in single-player games) */
	private static final int COMPUTER = 1;

	/**
	 * Constructor for ConnectFourGame
	 * 
	 * @param pSize
	 *            Size of the board
	 */
	public ConnectFourGame3D(int pSize) {
		size = pSize;
		board = new int[pSize][pSize][pSize];
		this.playerCount = 2;
		this.player = 0;
		reset();
	}

	/**
	 * Resets the game board so that all spaces are blank
	 */
	public void reset() {
		setCurrentPlayer(USER); // or player 0
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				for (int k = 0; k < size; k++){
				board[i][j][k] = -1;
			}
	}

	/**
	 * Method that checks to see which row to put the checker into after a column is
	 * selected in the panel
	 * 
	 * @param pCol
	 *            What column was selected
	 * @return row What row to put checker in
	 * @return -1 No available row
	 */
	public int selectCol(int pCol) {
		for (int row = size - 1; row >= 0; row--) {
			for (int col2 = size - 1; col2 >= 0; col2--) {
				if (board[row][pCol][col2] == -1) {
					board[row][pCol][col2] = player;
					return row;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Method that checks to see which column is open horizontally in the 3D array
	 * (the other column)
	 * 
	 * @param pCol
	 * @return
	 */
	public int selectCol2(int pCol) {
		for (int row = size - 1; row >= 0; row--) {
			for (int col2 = size - 1; col2 >= 0; col2--) {
				if (board[row][pCol][col2] == -1) {
					board[row][pCol][col2] = player;
					return col2;
				}
			}
		}
		return -1;
	}

	/**
	 * Method that returns the next player
	 * 
	 * @return 0 Player 1
	 * @return 1 Player 2
	 */
	public int nextPlayer() {
		player = (player + 1) % playerCount;
		return player;

	}

	/**
	 * Returns the current player
	 * 
	 * @return 0 Player 1
	 * @return 1 Player 2
	 */
	public int getCurrentPlayer() {
		return player;
	}

	/**
	 * Sets the current player (useful for resetting board as well as "simulating" a
	 * players turn @see ComputerMove())
	 * 
	 * @param pPlayer
	 *            what player should be changed to
	 */
	public void setCurrentPlayer(int pPlayer) {
		player = pPlayer;
	}

	/**
	 * A class that returns true if a player has won, or false if no one has won yet
	 * 
	 * @return boolean
	 */
	public boolean isWinner(int player) {
		if (checkVerticalWin(player)) {
			return true;
		}
		if (checkHorizontalWin(player)) {
			return true;
		}
		if (checkDiagonal1Win(player)) {
			return true;
		}
		if (checkDiagonal2Win(player)) {
			return true;
		}
		return false;
	}

	/** 
	 * A private method that checks if the player (or computer) has won vertically
	 * 
	 * @param currPlayer
	 *            The player being checked
	 * @return true If player wins vertically
	 */
	private boolean checkVerticalWin(int currPlayer) {
		for (int row = 0; row < size - 3; row++) {
			for (int col = 0; col < size; col++) {
				for (int col2 = 0; col2 < size - 3; col2++)	{
						if (board[row][col][col2] == currPlayer && 
							board[row + 1][col][col2] == currPlayer && 
							board[row + 2][col][col2] == currPlayer && 
							board[row + 3][col][col2] == currPlayer) {
							return true;

					}
				}
			}
		}
		return false;
	}

	/**
	 * A private method that checks if the player (or computer) has won Horizontally
	 * 
	 * @param currPlayer
	 *            The player being checked
	 * @return true If player wins horizontally
	 */
	private boolean checkHorizontalWin(int currPlayer) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size - 3; col++) {
				for (int col2 = 0; col2 < size - 3; col2++)	{
						if (board[row][col][col2] == currPlayer &&
							board[row][col + 1][col2] == currPlayer && 
							board[row][col + 2][col2] == currPlayer && 
							board[row][col + 3][col2] == currPlayer) {
					return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * A private method that checks if the player (or computer) has won Diagonally
	 * (descending) from left to right
	 * 
	 * @param currPlayer
	 *            The player being checked
	 * @return true If player wins Diagonally (descending)
	 */
	private boolean checkDiagonal1Win(int currPlayer)	{	
		for (int row = 0; row < size - 3; row++) {
			for (int col = 0; col < size - 3; col++) {
				for (int col2 = 0; col2 < size - 3; col2++)	{
							if (board[row][col][col2] == currPlayer &&
								board[row + 1][col + 1][col2] == currPlayer && 
								board[row + 2][col + 2][col2] == currPlayer && 
								board[row + 3][col + 3][col2] == currPlayer) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * A private method that checks if the player (or computer) has won Diagonally
	 * (ascending) from left to right
	 * 
	 * @param currPlayer
	 *            The player being checked
	 * @return true If player wins diagonally (ascending)
	 */
	private boolean checkDiagonal2Win(int currPlayer)	{	
		for (int row = 3; row < size; row++) {
			for (int col = 0; col < size - 3; col++) {
				for (int col2 = 0; col2 < size - 3; col2++)	{
							if (board[row][col][col2] == currPlayer &&
								board[row - 1][col + 1][col2] == currPlayer && 
								board[row - 2][col + 2][col2] == currPlayer && 
								board[row - 3][col + 3][col2] == currPlayer) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * A method that "undoes" the previous turn. Meaning that the inputed column and
	 * row block will be set blank
	 * 
	 * @param pRow
	 *            The row being changed to blank
	 * @param pCol
	 *            The column being changed to blank
	 */
	private void undo(int pRow, int pCol, int pCol2) {
		if (pRow != -1) {
			board[pRow][pCol][pCol2] = -1;
		}
	}

	/**
	 * Method that executes the computers move. First the computer checks if it can
	 * move to a spot that can win, if it cannot then it checks to see if it can
	 * "block" the user. If it can do neither of these, then it moves to a random
	 * spot.
	 * 
	 * @return result A Point on the x,y axis for the computers turn
	 */
	public Point computerMove() { 
		// check to see if computer can move to a spot that will win
		for (int col = 0; col < size; col++) {
			int row = selectCol(col);
			int col2 = selectCol2(col);
			if (isWinner(COMPUTER)) {
				Point result = new Point(row, col);
				return result;
			} else {
				undo(row, col, col2);
			}
		}

		// if computer cannot win, it will attempt to "block" the player
		// by simulating all possible player moves
		setCurrentPlayer(USER);
		for (int col = 0; col < size; col++) {
			int row = selectCol(col);
			int col2 = selectCol2(col);
			if (isWinner(USER)) {
				undo(row, col, col2);
				setCurrentPlayer(COMPUTER);
				row = selectCol(col);
				Point result = new Point(row, col);
				return result;

			} else {
				undo(row, col, col2);
			}

		}
		setCurrentPlayer(COMPUTER);

		// if computer cannot win or block player, pick a random Column
		int randomCol = (int) Math.floor(Math.random() * size);
		int randomRow = selectCol(randomCol);
		int randomCol2 = selectCol2(randomCol);
		while (randomRow == -1 || randomCol2 == -1) { // prevents "Out of Range" exception
			randomCol = (int) Math.floor(Math.random() * size);
			randomRow = selectCol(randomCol);
			randomCol2 = selectCol2(randomCol);
		}
		Point result = new Point(randomRow, randomCol2);
		return result;
	}
}