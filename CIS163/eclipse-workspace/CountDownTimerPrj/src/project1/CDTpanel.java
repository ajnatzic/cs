package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * An extensive GUI class that creates a fully working JPanel for one timer
 * (including buttons and modifiers). This class is invoked 3 times in the
 * mainPanel class to create 3 working timers with buttons.
 * 
 * @see mainPanel()
 * @author AJ
 *
 */
@SuppressWarnings("serial")
public class CDTpanel extends JPanel {

	/** A list of buttons that add functionality to GUI */
	private JButton startBtn;
	private JButton stopBtn;
	private JButton incBtn;
	private JButton decBtn;
	private JButton addBtn;
	private JButton subBtn;
	private JButton customTime;
	private JButton reset;

	/** This JLabel holds the current time of CountDownTimer object */
	private JLabel currTime;

	/** This instantiates CountDownTimer */
	private CountDownTimer countdowntimer;

	/**
	 * This gives a final CountDownTimer with value "0:00:00" used later in the
	 * class to denote that the timer has reached 0
	 */
	private static final CountDownTimer OUT_OF_TIME = new CountDownTimer("0:00:00");

	/** A timer listener used in the constructor */
	private TimerListener timer;

	/** The timer object itself */
	public Timer javaTimer;

	/**
	 * The main method that constructs the GUI for the CountDownTimer class. This
	 * method calls mainPanel which creates 3 separate timer JPanels.
	 * 
	 * @see mainPanel()
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame frame = new JFrame("Timers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel panel = new mainPanel();
		frame.getContentPane().add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Constructor for the CDTpanel class. This class builds the JPanel for ONE
	 * timer.
	 */
	public CDTpanel() {

		reset = new JButton("Reset");
		countdowntimer = new CountDownTimer(1, 0);
		currTime = new JLabel(countdowntimer.toString());
		timer = new TimerListener();
		javaTimer = new Timer(1000, timer);

		ButtonListener buttonListen = new ButtonListener();

		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		incBtn = new JButton("Add 1 second");
		decBtn = new JButton("Minus 1 second");
		addBtn = new JButton("Add seconds...");
		subBtn = new JButton("Subtract seconds...");
		customTime = new JButton("Use custom time");

		startBtn.addActionListener(buttonListen);
		stopBtn.addActionListener(buttonListen);
		incBtn.addActionListener(buttonListen);
		decBtn.addActionListener(buttonListen);
		addBtn.addActionListener(buttonListen);
		subBtn.addActionListener(buttonListen);
		customTime.addActionListener(buttonListen);
		reset.addActionListener(buttonListen);

		setLayout(new GridLayout(12, 1));
		add(reset);
		add(currTime);
		add(startBtn);
		add(stopBtn);
		add(incBtn);
		add(decBtn);
		add(addBtn);
		add(subBtn);
		add(customTime);
	}

	/**
	 * A buttonListener for all of the buttons in the GUI. The functionality of each
	 * button is described in inner comments.
	 * 
	 * @author AJ
	 *
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getSource() == startBtn) {// start button
				javaTimer.start();
			}

			if (arg0.getSource() == stopBtn) {// stop button
				javaTimer.stop();
			}

			if (arg0.getSource() == incBtn) { // increment one second
				countdowntimer.inc();
				currTime.setText(countdowntimer.toString());
			}

			if (arg0.getSource() == decBtn) { // decrement one second
				countdowntimer.dec();
				currTime.setText(countdowntimer.toString());
			}

			if (arg0.getSource() == addBtn) { // adds user inputed seconds
				javaTimer.stop();
				String seconds = JOptionPane.showInputDialog("Please enter amount of seconds to be added: ");
				countdowntimer.add(Integer.parseInt(seconds));
				currTime.setText(countdowntimer.toString());
				javaTimer.start();
			}

			if (arg0.getSource() == subBtn) { // subtracts user inputed seconds
				javaTimer.stop();
				String seconds = JOptionPane.showInputDialog("Please enter amount of seconds to be subtracted: ");
				countdowntimer.sub(Integer.parseInt(seconds));
				currTime.setText(countdowntimer.toString());
				javaTimer.start();
			}

			if (arg0.getSource() == customTime) { // sets timer to user inputed time
				javaTimer.stop();
				String time = JOptionPane.showInputDialog("Please enter desired time: ");
				countdowntimer = new CountDownTimer(time);
				currTime.setText(countdowntimer.toString());
			}
			if (arg0.getSource() == reset) { // stops timer and resets to 0:01:00
				javaTimer.stop();
				countdowntimer = new CountDownTimer(0, 1, 0);
				currTime.setText(countdowntimer.toString());
			}

		}

	}

	/**
	 * A TimerListener class that does something every time a second passes
	 * 
	 * @author AJ
	 *
	 */
	private class TimerListener implements ActionListener {

		/**
		 * Decrements the current time by a second for every second that passes as long
		 * as countdowntimer does not equal 0
		 */
		public void actionPerformed(ActionEvent e) {

			if (countdowntimer.compareTo(OUT_OF_TIME) == 0) { // if timer equals 0 then stop the timer and display "Out
																// of Time!" string
				javaTimer.stop();
				currTime.setText(countdowntimer.toString() + " Out of time!");
			} else { // otherwise, keep counting down
				countdowntimer.dec();
				currTime.setText(countdowntimer.toString());
			}

		}

	}

}
