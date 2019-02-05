package project2;

import javax.swing.*;

import project2.threeD.ConnectFourPanel3D;

/**
 * This class contains the main method that runs the Connect Four game as well
 * as a separate method that asks if the user would like to play in 2D or 3D
 * 
 * @see game3D
 * @author AJ
 *
 */
public class ConnectFour {

	/** boolean value that keeps track of whether new game is 3D or not */
	public Boolean is3D = false;

	/**
	 * Main method that creates and displays the frame for the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectFour newGame = new ConnectFour();
		newGame.game3D(); // asks user if they would like to play in 3D or 2D

		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem gameItem;

		JFrame frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		gameItem = new JMenuItem("New game");

		fileMenu.add(gameItem);
		fileMenu.add(quitItem);
		menus = new JMenuBar();
		frame.setJMenuBar(menus);
		menus.add(fileMenu);

		if (!newGame.is3D) { // if the new game IS NOT 3D
			ConnectFourPanel panel = new ConnectFourPanel(quitItem, gameItem);
			frame.setTitle("Connect Four 2D");
			frame.getContentPane().add(panel);
		}
		if (newGame.is3D) { // if the new game IS 3D
			ConnectFourPanel3D panel = new ConnectFourPanel3D(quitItem, gameItem);
			frame.setTitle("Connect Four 3D");
			frame.getContentPane().add(panel);
		}

		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	/**
	 * This private method is invoked in the beginning of the main and asks the user
	 * if they would like to play in 2D or 3D
	 */
	private void game3D() {
		String[] options = new String[] { "2D", "3D" };
		int response = JOptionPane.showOptionDialog(null, "Please select type of game", "Connect Four",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (response == 1) // if 3D is selected
			is3D = true;
		else // if 2D is selected (or no input is given)
			is3D = false;
	}
}
