package chess;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class ChessGUI {	
	
	public static void main(String[] args) {
		boolean computer = false;
		
		// asks the player if they want a 1 or 2 player game
				String[] options = new String[] { "1 player", "2 players" };
				int response = JOptionPane.showOptionDialog(null, "Please select number of players", "Chess",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (response == 1) // if 2 player selected
					computer = false;
				else // if 1 player selected (or if no input is given)
					computer = true;
				
				
		JFrame frame = new JFrame("Chess Game");
		
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quit;
		JMenuItem settings;
		JMenuItem newGame;
		JMenuItem switchGameMode;
		
		
		fileMenu = new JMenu("File");
		quit = new JMenuItem("Quit");
		settings = new JMenuItem("Settings");
		newGame = new JMenuItem("New Game");
		switchGameMode = new JMenuItem("Switch Game Mode");
		
		fileMenu.add(switchGameMode);
		fileMenu.add(newGame);
		fileMenu.add(settings);
		fileMenu.add(quit);
		
		menus = new JMenuBar();
		frame.setJMenuBar(menus);
		menus.add(fileMenu);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel(computer, quit, settings, newGame, switchGameMode);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}
