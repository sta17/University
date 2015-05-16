package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

@SuppressWarnings("serial")
public class ListHandelingPane extends JPanel {

	private JButton addButton, removeButton, changeButton;
	private WordList list;
	private ListHandelingListener listener;

	/**
	 * constructor calls invokeLater() to build window
	 */
	public ListHandelingPane(WordList list) {
		this.list = list;
		listener = new ListHandelingListener();

		addButton = new JButton("Add word");
		removeButton = new JButton("Remove word");
		changeButton = new JButton("Change list");

		buildChoice();
	}

	/**
	 * actually builds window with panels in right place
	 */
	public void buildChoice() {

		addButton.addActionListener(listener);
		add(addButton);
		removeButton.addActionListener(listener);
		add(removeButton);
		changeButton.addActionListener(listener);
		add(changeButton);

	}

	/**
	 * This method checks the input to see if it is legal, before an attempt to
	 * add it is made.
	 * 
	 * @param add
	 *            the input String to be checked.
	 */
	private void addWord(String add) {
		try {
			if (add == null || add.isEmpty() || add.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Please enter proper word.", "Input", 1);
			} else {
				list.addPhrase(add);
				JOptionPane.showMessageDialog(null,
						"Word Added Successfully.", "Input", 1);
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Invalid entry : " + add
					+ " already exists in list.", "Input", 1);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Entry : " + add
					+ " added to list, but file not found has caused it not be saved.", "File Error", 1);
		}
	}

	/**
	 * This method checks the input to see if it is legal, before an attempt to
	 * remove it is made.
	 * 
	 * @param remove
	 *            the input String to be checked.
	 */
	private void removeWord(String remove) {
		if (remove == null || remove.isEmpty() || remove.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter proper word.",
					"Input", 1);
		} else
			try {
				if (list.removePhrase(remove)) {
					JOptionPane.showMessageDialog(null, "Word Removed successfully.",
							"Input", 1);

				} else {
					JOptionPane.showMessageDialog(null, "Invalid entry : " + remove
							+ " was not found.", "Input", 1);
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Entry : " + remove
						+ " removed from list, but file not found has caused it not be saved.", "File Error", 1);
			}
	}

	/**
	 * this methods changes the list from what is current one to the spesified
	 * one.
	 * 
	 * @param txtfile
	 *            the file to change too.
	 */
	private void changeList(String txtfile) {
			try{
					list.changeList(txtfile);
					JOptionPane.showMessageDialog(null, "File Successfully Switched.", "File Switch", 1);
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(null,
						"Please enter proper filename path.", "Input", 1);
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "File: " + txtfile
						+ " not found.", "File error", 1);
			} catch (NoSuchElementException e1) {
				// simple prevention
				JOptionPane.showMessageDialog(null, "Error: empty file",
						"File error", 1);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
						"Error: error during saving of old file", "File error",
						1);
			} catch (RuntimeException e1) {
				JOptionPane.showMessageDialog(null, "Error: " + e1,
						"File error", 1);
			}
	}

	/*
	 * =========================================================
	 * ==================== ActionListener comes here.
	 * =========================================================
	 */

	private class ListHandelingListener implements ActionListener {

		@Override
		/**
		 * This method is called when a button is presed because 'this' is listening
		 * you could set up a separate object to do that
		 */
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Add word")) {
				String str = JOptionPane.showInputDialog(null, "Enter word : ",
						"Input", 1);
				if(str != null){
				addWord(str);
				}
			} else if (actionCommand.equals("Remove word")) {
				String str = JOptionPane.showInputDialog(null, "Enter word : ",
						"Input", 1);
				if(str != null){
				removeWord(str);
				}
			} else if (actionCommand.equals("Change list")) {

				String txtfile = JOptionPane
						.showInputDialog(
								null,
								"piratewords.txt is the system default, press enter to use it.\nOtherwise please Enter full path of file,"
										+ " including name and .txt: ",
								"filename", 1);
				if(txtfile != null){
				changeList(txtfile);
				}
			} else {
				// System.out.println("ERROR: Unexpected ActionCommand!");
				JOptionPane.showMessageDialog(null,
						"ERROR: Unexpected ActionCommand!", "ERROR", 1);
			}
		}

	}

}