package project2;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
/**
 * This class creates the visual GUI panel for the 2D Connect Four Game
 * 
 * @author AJ Natzic
 *
 */
public class ConnectFourPanel extends JPanel {

	/** the size of the board (10 is a 10x10 array) */
	private int size = 10;

	/** the JLabels for the 2D array board (represents the "checkers") */
	private JLabel[][] matrix;

	/**
	 * selection buttons for columns, determines which column the checker is
	 * "dropped" in
	 */
	private JButton[] selection;

	/**
	 * boolean that states whether game has a computer or not (1 or 2 player, 1
	 * player is default)
	 */
	private Boolean computer = true;

	/** menu item that resets the game */
	private JMenuItem gameItem;

	/** menu item that quits the game */
	private JMenuItem quitItem;

	/** image for a blank spot on the board */
	private ImageIcon iconBlank;

	/** image for the user player (or player 0) */
	private ImageIcon iconPlayerUser;

	/** image for the computer player (or player 1) */
	private ImageIcon iconPlayerComputer;

	/** integer that represents the user (player 0) */
	private static final int USER = 0;

	/** integer that represents the computer (player 1) */
	private static final int COMPUTER = 1;

	/** the instance of the 2D game */
	private ConnectFourGame game;

	/**
	 * Constructor for ConnectFourPanel
	 * 
	 * @param pquitItem
	 * @param pgameItem
	 */
	public ConnectFourPanel(JMenuItem pquitItem, JMenuItem pgameItem) {
		// asks the player if they want a 1 or 2 player game
		String[] options = new String[] { "1 player", "2 players" };
		int response = JOptionPane.showOptionDialog(null, "Please select number of players", "Connect Four 2D",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (response == 1) // if 2 player selected
			computer = false;
		else // if 1 player selected (or if no input is given)
			computer = true;

		// asks the player what board size they want
		String userBoardSize = JOptionPane.showInputDialog(null, "Please enter board size between 3 and 30:", size);
		setUserBoardSize(userBoardSize); // sets board size if input is valid; if not valid default size is used (10)

		game = new ConnectFourGame(size);

		gameItem = pgameItem;
		quitItem = pquitItem;

		setLayout(new GridLayout(size + 1, size)); // room for top row

		iconBlank = new ImageIcon("blank.png");
		iconPlayerUser = new ImageIcon("blue.png");
		iconPlayerComputer = new ImageIcon("red.png");

		ButtonListener listener = new ButtonListener();
		quitItem.addActionListener(listener);
		gameItem.addActionListener(listener);

		selection = new JButton[size];
		for (int col = 0; col < size; col++) {
			selection[col] = new JButton("Select");
			selection[col].addActionListener(listener);
			add(selection[col]);
		}

		matrix = new JLabel[size][size];

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				matrix[row][col] = new JLabel("", iconBlank, SwingConstants.CENTER);
				add(matrix[row][col]);
			}
		}
	}

	/**
	 * This method checks the string inputed by the user to see if it is a valid
	 * board size between 3 and 30 and sets it to variable "size"
	 * 
	 * @param userBoardSize
	 *            The board size inputed
	 */
	private void setUserBoardSize(String userBoardSize) {
		try {
			size = Integer.parseInt(userBoardSize);
			game = new ConnectFourGame(size);
			if (size < 3 || size > 30) // tests to see if user inputed value between 3 and 30
				throw new Exception();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid input given for board size", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			size = 10; // set board size to default if invalid input given
		}
	}

	// *****************************************************************
	// Represents a listener for button push (action) events.
	// *****************************************************************
	private class ButtonListener implements ActionListener {
		// --------------------------------------------------------------
		// Updates the counter and label when the button is pushed.
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {

			JComponent comp = (JComponent) event.getSource();

			for (int col = 0; col < size; col++) {
				if (selection[col] == comp) {

					int row = game.selectCol(col);
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Column is full!");
						continue;
					} else
						matrix[row][col].setIcon((game.getCurrentPlayer() == 0) ? iconPlayerUser : iconPlayerComputer);

					if (computer == true) { // if game is one player, computer move will execute
						if (!game.isWinner(USER)) {
							game.nextPlayer();
							Point computerMove = game.computerMove();
							matrix[computerMove.x][computerMove.y].setIcon(iconPlayerComputer);
						}
					}

					if (game.isWinner(game.getCurrentPlayer())) { // checks if someone wins
						if (computer == true) { // if game has computer, display computer message
							if (game.getCurrentPlayer() == COMPUTER)
								JOptionPane.showMessageDialog(null, "Computer Wins!");
							if (game.getCurrentPlayer() == USER)
								JOptionPane.showMessageDialog(null, "You win!");

						}

						else if (computer == false) { // if game does not have computer, display player messages
							if (game.getCurrentPlayer() == 0)
								JOptionPane.showMessageDialog(null, "Player 1 Wins!");
							if (game.getCurrentPlayer() == 1)
								JOptionPane.showMessageDialog(null, "Player 2 Wins!");
						}
						for (int button = 0; button < size; button++) { // disables selection buttons after win
							selection[button].setEnabled(false);
						}
					}

					game.nextPlayer();

				}

			}

			if (comp == gameItem) {
				game.reset();

				for (int button = 0; button < size; button++) { // re-enables all selection buttons
					selection[button].setEnabled(true);
				}

				for (int row = 0; row < size; row++) // sets all checkers to blank
					for (int col = 0; col < size; col++)
						matrix[row][col].setIcon(iconBlank);
			}

			if (comp == quitItem)
				System.exit(1);
		}

	}

}
