package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.lang.management.ManagementFactory;
//import java.lang.management.ThreadMXBean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

public class ChoiceMenu {

	// ///////////////////////////////////////////////////instance variables

	private WordList list;
	private ChoiceListener listener;
	private ListHandelingPane listpane;

	private JPanel panel;
	private JFrame frame;
	private JButton textbutton, guibutton;

	/**
	 * constructor calls invokeLater() to build window
	 */
	public ChoiceMenu(WordList list) {
		this.list = list;

		listener = new ChoiceListener();
		panel = new JPanel();
		listpane = new ListHandelingPane(list);
		frame = new JFrame();
		textbutton = new JButton("Start Text, Requires Command Line");
		guibutton = new JButton("Start GUI");

		buildChoice();
	}

	/**
	 * actually builds window with panels in right place
	 */
	public void buildChoice() {

		frame.setTitle("Choice Menu");
		panel.setLayout(new GridLayout(2, 2, 3, 3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textbutton.addActionListener(listener);
		panel.add(textbutton);
		guibutton.addActionListener(listener);
		panel.add(guibutton);

		// Get the screen size
		GraphicsConfiguration gc = frame.getGraphicsConfiguration();
		Rectangle bounds = gc.getBounds();

		// Create and pack the Elements
		frame.add(panel, BorderLayout.NORTH);
		frame.add(listpane, BorderLayout.SOUTH);
		frame.pack();
		// Set the Location and Activate
		Dimension size = frame.getPreferredSize();
		frame.setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
				(int) ((bounds.height / 10) - (size.getHeight() / 2)));

		frame.setVisible(true);
	}

	/*
	 * =========================================================
	 * ==================== Controller bit.
	 * =========================================================
	 */

	private void startTxt() {
		TextVer txt = new TextVer(list);
		txt.runTheMenu();
	}

	private void startGUI() {
		@SuppressWarnings("unused")
		GuiVer gui = new GuiVer(list);
	}

	/*
	 * =========================================================
	 * ==================== ActionListener comes here.
	 * =========================================================
	 */

	private class ChoiceListener implements ActionListener {

		@Override
		/**
		 * This method is called when a button is presed because 'this' is listening
		 * you could set up a separate object to do that
		 */
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Start Text, Requires Command Line")) {
				if (list.getSize() != 0) {
					startTxt();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"ERROR: The List of words and phrases are empty, please add some before attempting to play.",
									"List Error", 1);
				}
			} else if (actionCommand.equals("Start GUI")) {
				if (list.getSize() != 0) {
					startGUI();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"ERROR: The List of words and phrases are empty, please add some before attempting to play.",
									"List Error", 1);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"ERROR: Unexpected ActionCommand!", "ERROR", 1);
			}
		}
	}

}
