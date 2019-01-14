package project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Instantiates one CDTpanel 3 times, this main panel will be called in the
 * MyTimerPanel class to build the GUI
 * 
 * @see MyTimerPanel()
 * @see CDTpanel()
 * @author AJ
 *
 */
@SuppressWarnings("serial")
public class mainPanel extends JPanel {

	private CDTpanel p1;
	private CDTpanel p2;
	private CDTpanel p3;

	private JButton masterStart;
	private JButton masterStop;

	/**
	 * Constructor for mainPanel
	 */
	public mainPanel() {

		p1 = new CDTpanel();
		p2 = new CDTpanel();
		p3 = new CDTpanel();
		masterStart = new JButton("Start all timers");
		masterStop = new JButton("Stop all timers");

		add(p1);
		add(p2);
		add(p3);
		add(masterStart);
		add(masterStop);

		ButtonListener btnListen = new ButtonListener();
		masterStart.addActionListener(btnListen);
		masterStop.addActionListener(btnListen);

	}

	/**
	 * ButtonListener class
	 * 
	 * @author AJ
	 *
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Checks to see if masterStart or masterStop button was pressed
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == masterStart) {
				p1.javaTimer.start();
				p2.javaTimer.start();
				p3.javaTimer.start();
			} else if (arg0.getSource() == masterStop) {
				p1.javaTimer.stop();
				p2.javaTimer.stop();
				p3.javaTimer.stop();

			}

		}
	}
}
