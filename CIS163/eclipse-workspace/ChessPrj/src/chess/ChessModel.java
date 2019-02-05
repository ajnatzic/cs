package chess;

/**
 * A class that runs the back end functionality of the chess board
 * 
 * @author AJ, Adan, Trevor
 *
 */
public class ChessModel implements IChessModel {

	/** boolean that keeps track of whether a computer is playing or not */
	private boolean computer;
	
	/** the back end board that utilizes IChessPiece */
	private IChessPiece[][] board;

	/** the size of the board */
	private final int SIZE = 8;

	/** an int value that holds the row of the king */
	private int kingRow = 0;

	/** an int value that holds the column of the king */
	private int kingCol = 0;

	/** Move instantiated as 'm' */
	Move m;
	
	Move undoMove;

	/** keeps track of which player is currently moving */
	private Player player;

	/** user will always play a white piece */
	private final Player USER;

	/** computer will always be black piece */
	private final Player COMPUTER;

	/**
	 * Constructor that initializes the variables as well as sets up the board
	 */
	public ChessModel(boolean computerChoice) {

		computer = computerChoice;
		if (computer == true) {
			USER = Player.WHITE; // user will always be white piece
			COMPUTER = Player.BLACK; // computer will always be black piece
		} else {
			USER = null;
			COMPUTER = null;
		}

		board = new ChessPiece[SIZE][SIZE];
		player = Player.BLACK;
		setDefaultBoard();

	}

	/**
	 * resets the board to default
	 */
	public void setDefaultBoard() {

		// Sets each button to a default null
		for (int row = 0; row < SIZE; row++)
			for (int col = 0; col < SIZE; col++)
				board[row][col] = null;

		// Initialize the piece objects to their respective location
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		for (int col = 0; col < SIZE; col++)
			board[1][col] = new Pawn(Player.BLACK);
		// Initialize the piece objects to their respective location
		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		for (int col = 0; col < SIZE; col++)
			board[6][col] = new Pawn(Player.WHITE);

	}

	/**
	 * checks to see if someone won
	 */
	@Override
	public boolean isComplete(Player p) {	// FIXME
		boolean checkmate = false;
		if(inCheck(p))	{
		for (int row = 0; row < SIZE; row++)
			for (int col = 0; col < SIZE; col++)
				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
						&& board[row][col].player() == p) {
					kingRow = row;
					kingCol = col;
				}
		
		

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (board[row][col] == null) {
					
					m = new Move(kingRow, kingCol, row, col);
					undoMove = new Move(row, col, kingRow, kingCol);

					if (board[kingRow][kingCol].isValidMove(m, board)) {
						move(m);
						if (inCheck(p)) {
							checkmate = true;
							move(undoMove);
						} else {
							checkmate = false;
							move(undoMove);
//							break;
						}
					}
					// if(board[kingRow][kingCol].isValidMove(m, board) && !inCheck(p)) {
					// checkmate = false;
					// break;
					// }
				}

			}
		}
		}
		return checkmate;
	}

	/**
	 * checks to see if a move is valid
	 */
	@Override
	public boolean isValidMove(Move move) {
		// Checks to see if there is a piece at the selected location
		if (board[move.fromRow][move.fromColumn] != null) {
			// Checks to make sure the piece is making a valid move
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
				return true;
		}
		return false;
	}

	/**
	 * executes a move in the back end of the chess board
	 */
	@Override
	public void move(Move move) {
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}
	
	/**
	 * executes an undo move
	 */
	public void undoMove(Move move) {
		Move undoMove = new Move(move.toRow, move.toColumn, move.fromRow, move.fromColumn);
		move(undoMove);
	}

	/**
	 * returns true if a given player is in check, otherwise returns false
	 */
	@Override
	public boolean inCheck(Player p) {

		for (int row = 0; row < SIZE; row++)
			for (int col = 0; col < SIZE; col++)
				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
						&& board[row][col].player() == p) {
					kingRow = row;
					kingCol = col;
				}

		for (int row = 0; row < SIZE; row++)
			for (int col = 0; col < SIZE; col++)
				if (board[row][col] != null) // && board[row][col].player() == nextPlayer())
				{
					m = new Move(row, col, kingRow, kingCol);
					if (board[row][col].isValidMove(m, board))
						return true;
				}

		return false;
	}

	/**
	 * returns the current player
	 */
	@Override
	public Player currentPlayer() {
		return player;
	}

	/**
	 * returns what will be the next player
	 * 
	 * @return
	 */
	public Player nextPlayer() {
		if (currentPlayer() == Player.BLACK)
			return Player.WHITE;
		if (currentPlayer() == Player.WHITE)
			return Player.BLACK;
		return null;
	}

	/**
	 * switches to the next player
	 */
	public void switchPlayer() {
		player = player.next();
	}

	/**
	 * returns a piece at a specific place on the board
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/**
	 * returns the number of rows
	 * 
	 * @return
	 */
	public int numRows() {
		return SIZE;
	}

	/**
	 * returns the number of columns
	 * 
	 * @return
	 */
	public int numColumns() {
		return SIZE;
	}

	/**
	 * Completes the computer move by following the rubric
	 * 
	 * @return
	 */
	public Move computerMove() { // Step 11: Full functionality
		// a. Check to see if you are in check.
		// if(currentPlayer() == Player.BLACK) { //the computer will play as a BLACK
		// piece
		if (inCheck(Player.BLACK)) {
			System.out.println("COMPUTER IS IN CHECK");
			for (int row = 0; row < SIZE; row++)
				for (int col = 0; col < SIZE; col++)
					if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
							&& board[row][col].player() == COMPUTER) {
						kingRow = row;
						kingCol = col;
					}

			// i. If so, get out of check by moving the king or placing a piece to block the
			// check
			// if(kingRow - 1 >= 0) {
			// if(board[kingRow - 1][kingCol - 1] == null) {
			// Move m = new Move(kingRow,kingCol,kingRow - 1,kingCol - 1);
			// return m;
			// }
			// }
			// }

		}
		// b. Attempt to put opponent into check (or checkmate).
		// i. Attempt to put opponent into check without losing your piece
		// ii. Perhaps you have won the game.
		// c. Determine if any of your pieces are in danger, or you can take their
		// piece.
		// i. Take their piece OR.
		// ii. Attempt to protect your piece.
		// d. Move a piece (pawns first) forward toward opponent king
		int numPawns = 0;
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {

				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("Pawn")
						&& board[row][col].player() == COMPUTER) {
					numPawns++;

					if (col > 0 && col < 7 && row < 7) {
						m = new Move(row, col, row + 1, col + 1);
						if (board[row][col].isValidMove(m, board) && board[row + 1][col + 1].player() == USER)
							return new Move(row, col, row + 1, col + 1);

						m = new Move(row, col, row + 1, col - 1);
						if (board[row][col].isValidMove(m, board) && board[row + 1][col - 1].player() == USER)
							return new Move(row, col, row + 1, col - 1);
					}
					if (col == 0) {
						m = new Move(row, col, row + 1, col + 1);
						if (board[row][col].isValidMove(m, board) && board[row + 1][col + 1].player() == USER)
							return new Move(row, col, row + 1, col + 1);
					}
					if (col == 7) {
						m = new Move(row, col, row + 1, col - 1);
						if (board[row][col].isValidMove(m, board) && board[row + 1][col - 1].player() == USER)
							return new Move(row, col, row + 1, col - 1);
					}

				}
				// if (board[row][col] != null && pieceAt(row,
				// col).type().equalsIgnoreCase("Pawn") && board[row][col].player() ==
				// player.BLACK
				// && (board[row + 1][col + 1].player() == USER || board[row + 1][col -
				// 1].player() == USER)) {
				//
				// }
			}
		}

		System.out.println("Number of computer pawns = " + numPawns);

		int chosenPawn = 0;
		if (numPawns > 0) {
			chosenPawn = (int) Math.floor(Math.random() * numPawns) + 1;
			numPawns = 0;
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("Pawn")) {
						numPawns++;
						if (numPawns == chosenPawn)
							if (row == 1)
								return new Move(row, col, row + 2, col);
							else
								return new Move(row, col, row + 1, col);
					}
				}
			}
		}

		return null;
	}
}
