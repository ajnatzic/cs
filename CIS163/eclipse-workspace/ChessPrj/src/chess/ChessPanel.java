package chess;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial") // stops eclipse from giving a warning

/**
 * This class handles all of the front end functionality for the chess board
 * 
 * @author AJ, Adan, Trevor
 *
 */
public class ChessPanel extends JPanel {

	/** boolean that keeps track of whether a computer is playing or not */
	private boolean computer;
	
	/** 2D array of JButtons that represents the board */
	private JButton[][] board;

	/** ChessModel instantiated as 'model' */
	private ChessModel model;

	/** all of the images used in the GUI are instantiated */
	private ImageIcon black_king;
	private ImageIcon black_queen;
	private ImageIcon black_pawn;
	private ImageIcon black_bishop;
	private ImageIcon black_knight;
	private ImageIcon black_rook;
	
	private ImageIcon white_king;
	private ImageIcon white_queen;
	private ImageIcon white_pawn;
	private ImageIcon white_bishop;
	private ImageIcon white_knight;
	private ImageIcon white_rook;

	/** an Icon that will hold the old chesspiece when the move is executed */
	private Icon tempIcon;

	/** the size of the board */
	private final int SIZE = 8;

	/**
	 * keeps track of the amount of clicks the user performs (see action
	 * performed)
	 */
	private int clicks = 0;

	/** keeps track of the previous row from the previous move */
	int prevRow = 0;

	/** keeps track of the previous column from the previous move */
	int prevCol = 0;

	/** keeps track of the previous color of the board from the previous move */
	Color prevColor = null;

	/** instantiates Move as 'm' */
	Move m;
	
	JMenuItem quit, settings, newGame, switchGameMode;
	
	ButtonListener listener;
	
	/** all of the buttons used in settings panel */
	JButton 
	theme, 
	icons;
	
	JFrame settingsFrame;
	
	/** 
	 * A constructor for ChessPanel that sets up the board, as well as imports all
	 * of the chess piece images
	 */
	public ChessPanel(boolean computerChoice, JMenuItem quit, JMenuItem settings, JMenuItem newGame, JMenuItem switchGameMode) {
		
		
		computer = computerChoice;
				
		model = new ChessModel(computer);

		board = new JButton[SIZE][SIZE];
		listener = new ButtonListener();
		
		this.quit = quit;
		this.settings = settings;
		this.newGame = newGame;
		this.switchGameMode = switchGameMode;
		
		this.quit.addActionListener(listener);
		this.settings.addActionListener(listener);
		newGame.addActionListener(listener);
		switchGameMode.addActionListener(listener);
		
		
		
		
		setLayout(new GridLayout(8, 8, 1, 1));
		black_king = new ImageIcon("Black_King.png");
		black_queen = new ImageIcon("Black_Queen.png");
		black_pawn = new ImageIcon("Black_Pawn.png");
		black_bishop = new ImageIcon("Black_Bishop.png");
		black_knight = new ImageIcon("Black_Knight.png");
		black_rook = new ImageIcon("Black_Rook.png");
		white_king = new ImageIcon("White_King.png");
		white_queen = new ImageIcon("White_Queen.png");
		white_pawn = new ImageIcon("White_Pawn.png");
		white_bishop = new ImageIcon("White_Bishop.png");
		white_knight = new ImageIcon("White_Knight.png");
		white_rook = new ImageIcon("White_Rook.png");

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				// checks if it is on an even row AND an even column.
				// also checks if it is on an odd column AND an odd row.
				if ((row % 2 == 0 && col % 2 == 0) || (col % 2 != 0 && row % 2 != 0)) {
					board[row][col] = new JButton("");
					board[row][col].setBackground(Color.WHITE);
					add(board[row][col]);
					// otherwise, just set checker to light gray
				} else {
					board[row][col] = new JButton("");
					board[row][col].setBackground(Color.LIGHT_GRAY);
					add(board[row][col]);
				}
				board[row][col].addActionListener(listener);
			}
		}
		
		// sets up a new board
		displayBoard();
	}

	/**
	 * This method sets the chess piece icons in the correct position
	 */
	private void displayBoard() {
		board[0][0].setIcon(black_rook);
		board[0][1].setIcon(black_knight);
		board[0][2].setIcon(black_bishop);
		board[0][3].setIcon(black_queen);
		board[0][4].setIcon(black_king);
		board[0][5].setIcon(black_bishop);
		board[0][6].setIcon(black_knight);
		board[0][7].setIcon(black_rook);
		for (int col = 0; col < SIZE; col++)
			board[1][col].setIcon(black_pawn);

		board[7][0].setIcon(white_rook);
		board[7][1].setIcon(white_knight);
		board[7][2].setIcon(white_bishop);
		board[7][3].setIcon(white_queen);
		board[7][4].setIcon(white_king);
		board[7][5].setIcon(white_bishop);
		board[7][6].setIcon(white_knight);
		board[7][7].setIcon(white_rook);
		for (int col = 0; col < SIZE; col++)
			board[6][col].setIcon(white_pawn);

		for (int row = 2; row < 6; row++)
			for (int col = 0; col < SIZE; col++)
				board[row][col].setIcon(null);
	}
	
	/**
	 * dynamically switches the game mode based on whether a computer is currently playing or not
	 */
	public void switchGameMode() {
		if (computer == true) {
			int response = JOptionPane.showConfirmDialog(null,
					"Switch to multi-player? This will restart the current game, are you sure?", "Warning",
					JOptionPane.WARNING_MESSAGE);
			if (response == JOptionPane.OK_OPTION) {
				computer = false;
				setDefaultBoard();
			}
		} else {
			int response = JOptionPane.showConfirmDialog(null,
					"Switch to single-player? This will restart the current game, are you sure?", "Warning",
					JOptionPane.WARNING_MESSAGE);
			if (response == JOptionPane.OK_OPTION) {
				computer = true;
				setDefaultBoard();

			}
		}

	}
	
	/**
	 * sets a default board (useful for resetting the board)
	 */
	public void setDefaultBoard() {
//		model = new ChessModel(computer);
		model.setDefaultBoard();
		setDefaultBoardColors();
		displayBoard();
	}

	/**
	 * sets the checkers on board to default colors
	 */
	private void setDefaultBoardColors() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				// checks if it is on an even row AND an even column.
				// also checks if it is on an odd column AND an odd row.
				if ((row % 2 == 0 && col % 2 == 0) || (col % 2 != 0 && row % 2 != 0)) {
					board[row][col].setBackground(Color.WHITE);
					// otherwise, just set checker to light gray
				} else {
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	/**
	 * sets up and shows the settings panel, which allows user to modify multiple
	 * settings
	 */
	private void showSettingsPanel() { // FIXME not being used
		theme = new JButton("Themes");
		icons = new JButton("Icon packs");
		
		
		settingsFrame = new JFrame("Settings");
		JPanel settings = new JPanel();
		
		GridBagConstraints layout = new GridBagConstraints();	// TODO
		
		
		settingsFrame.getContentPane().add(settings);
		settingsFrame.setVisible(true);
		settingsFrame.setResizable(false);
		settingsFrame.setSize(800, 600);
		
	}
	
	/**
	 * highlights all possible moves on the board from pRow and pCol
	 * 
	 * @param pRow	from row
	 * @param pCol	from col
	 */
	private void highlightMoves(int pRow, int pCol) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (model.isValidMove(new Move(pRow, pCol, row, col))) {
					board[row][col].setBackground(Color.YELLOW);
				}

			}
		}

	}
	

	/**
	 * A button listener that listens for the user's actions, as well as executes
	 * the computer move
	 * 
	 * @author AJ Natzic
	 *
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if(source == quit)
				System.exit(1);
			if(source == newGame)
				setDefaultBoard();

			if(source == settings)
				showSettingsPanel();
		
			if(source == switchGameMode)	{	// TODO scrap all this, use JMenuitmes instead
				switchGameMode();
			}
				
			for (int row = 0; row < SIZE; row++)
				for (int col = 0; col < SIZE; col++)
					if (source == board[row][col]) {
						// checks if the user has performed an invalid move
						if (clicks == 1 && !model.isValidMove(new Move(prevRow, prevCol, row, col))) {

							JOptionPane.showMessageDialog(null, "Cant move there!");
							clicks = 0;
							setDefaultBoardColors();
						}
						// checks if the user has performed a valid move
						if (clicks == 1 && model.isValidMove(new Move(prevRow, prevCol, row, col))) {
							board[row][col].setIcon(tempIcon);
							board[prevRow][prevCol].setIcon(null);
							setDefaultBoardColors();

							clicks++;
							m = new Move(prevRow, prevCol, row, col);
							model.move(m);
							System.out.println("User move: " + m);
						}
						// checks if this is the user's first click (the user has not made a move yet)
						if (clicks == 0 && model.pieceAt(row, col) != null) {
							tempIcon = board[row][col].getIcon();
							prevColor = board[row][col].getBackground();
							board[row][col].setBackground(Color.BLUE);
							highlightMoves(row, col);
							prevRow = row;
							prevCol = col;
							clicks++;
						}
						// actions that take place after user move
						if (clicks == 2) {
							clicks = 0;

							if (computer) {
								Move compMove = model.computerMove();
								System.out.println("Computer Move: " + compMove);

								tempIcon = board[compMove.fromRow][compMove.fromColumn].getIcon();
								prevColor = board[compMove.fromRow][compMove.fromColumn].getBackground();
								prevRow = compMove.fromRow;
								prevCol = compMove.fromColumn;

								board[compMove.toRow][compMove.toColumn].setIcon(tempIcon);
								board[prevRow][prevCol].setIcon(null);
								board[prevRow][prevCol].setBackground(prevColor);

								model.move(compMove);
							}
							/** checks if someone won */
							if (model.isComplete(Player.BLACK))
								JOptionPane.showMessageDialog(null, "Checkmate\n" + "White player wins" );
							if (model.isComplete(Player.WHITE))
								JOptionPane.showMessageDialog(null, "Checkmate\n" + "Black Player wins");
							
							/** checks if a king is in check */
							if (model.inCheck(Player.BLACK))
								JOptionPane.showMessageDialog(null, "Black king is in check!");
							if (model.inCheck(Player.WHITE))
								JOptionPane.showMessageDialog(null, "White king is in check!");
						}
					}

		}
	}

}