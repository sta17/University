package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.ac.aber.dcs.sta17.cs12420.as.model.EmptyListException;
import uk.ac.aber.dcs.sta17.cs12420.as.model.GameModel;
import uk.ac.aber.dcs.sta17.cs12420.as.model.GameOverException;
import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

@SuppressWarnings("serial")
public class InteractionPane extends JPanel {

	private JButton letter, word, newGame, instructions;
	private JLabel usedStatic, currentWordStatic, usedDisplay,
			currentWordDisplay, triesStatic, triesDisplay;
	private JTextField letterField, wordField;

	private InteractionListener listener;
	private GameModel game;
	private WordList list;

	public InteractionPane(GameModel game, WordList list) {
		this.game = game;
		this.list = list;
		listener = new InteractionListener(this);

		BuildPane();
	}

	private void BuildPane() {
		setLayout(new GridLayout(3, 4, 3, 3));
		// first line

		letter = new JButton("Try Letter");
		letter.addActionListener(listener);
		add(letter);

		letterField = new JTextField("");
		add(letterField);

		usedStatic = new JLabel("UsedLetters: ");
		add(usedStatic);

		usedDisplay = new JLabel("" + game.getLettersDisplay());
		add(usedDisplay);
		// second line
		word = new JButton("Try Phrase");
		word.addActionListener(listener);
		add(word);

		wordField = new JTextField("");
		add(wordField);

		currentWordStatic = new JLabel("Progress:");
		add(currentWordStatic);

		currentWordDisplay = new JLabel("" + game.getVisible());
		add(currentWordDisplay);

		newGame = new JButton("New Phrase");
		newGame.addActionListener(listener);
		add(newGame);

		instructions = new JButton("Get Instructions.");
		instructions.addActionListener(listener);
		add(instructions);

		triesStatic = new JLabel("Guesses left:");
		add(triesStatic);

		triesDisplay = new JLabel("" + game.guessLeft());
		add(triesDisplay);
	}

	public String getLetterField() {
		return letterField.getText();
	}

	public String getWordField() {
		return wordField.getText();
	}

	/**
	 * This checks user input, to see if it is legal.
	 * 
	 * @param wordtry
	 *            to check and try in GameModel class tryWord method
	 */
	private void tryWord(String wordtry) {
		String response = "";

		if (list.getSize() == 0){
			response = "The list of words and phrases is empty, no gameplay can be played.";
		// checks if the game is actually lost, before proceeding.
		} else if (game.guessLeft() == 0) {
			response = "Game Over, you lost the game.\n Enter N for new phrase.\n";

			// checks if they have won.
		} else if (game.getHidden().equals(game.getVisible())) {
			response = "Game is Over, you won the game.\n Enter N for new phrase.\n";

			// check if input actually contains anything to match.
		} else if (wordtry == null || wordtry.isEmpty()
				|| wordtry.trim().isEmpty()) {
			response = "Invalid entry : No actuall input.";
		} else {
			try {
				if (game.tryWord(wordtry)) {
					currentWordDisplay.setText(game.getVisible());
					wordField.setText("");
					if (game.getHidden().equals(game.getVisible())) {
						response = "Congratulations you have guesses the Phrase.";
					}
				} else {// this is false or wrong letter bit
					response = "Phrase was incorrect.";
				}
				triesDisplay.setText("" + game.guessLeft());
				wordField.setText("");
				if (game.guessLeft() <= 0) {
					// Incase it is now game over, after minus 5, so
					// have to check
					// again.
					response = "Game Over, you have lost.";
				}
			} catch (GameOverException e) {
				response = "Game is Over, try new Phrase.";
			} catch (EmptyListException e) {
				response = "The list of words and phrases is empty, no gameplay can be played.";
			}
		}
		if (response.length() != 0) {
			JOptionPane.showMessageDialog(null, response, "Input", 1);
		}
	}

	/**
	 * This checks user input, to see if it is legal.
	 * 
	 * @param letter
	 *            the letter that is to be tried
	 */
	private void tryThis(String input) {
		String response = "";

		if (list.getSize() == 0){
			response = "The list of words and phrases is empty, no gameplay can be played.";
		// checks if the game is actually lost, before proceeding.
		} else if (game.guessLeft() == 0) {
			response = "Game Over, you lost the game.\n Press Button for new phrase.\n";

			// checks if they have won.
		} else if (game.getHidden().equals(game.getVisible())) {
			response = "Game is Over, you won the game.\n Press Button for new phrase.\n";

			// check if input actually contains anything to match.
		} else if (input == null || input.isEmpty() || input.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Invalid entry : No actuall input.", "Input", 1);

			// basic check for if they have actually only submitted 1 letter.
		} else if (input.length() == 1) {
			String letter = input.substring(0, 1);

			// checks if the letter has already been used
			if (game.getLetters().toLowerCase().contains(letter.toLowerCase())) {
				response = "Invalid entry : Letter already Used.";
				// the other bits
			} else {

				char temp = letter.charAt(0);

				try {
					if (game.tryThis(temp) == true) {
						currentWordDisplay.setText(game.getVisible());
						usedDisplay.setText(game.getLettersDisplay());
						letterField.setText("");
						if (game.getHidden().equals(game.getVisible())) {
							response = "Congratulations you have guesses the Phrase.";
						}
					} else {// this is false or wrong letter bit
						triesDisplay.setText("" + game.guessLeft());
						usedDisplay.setText(game.getLettersDisplay());
						letterField.setText("");
					}
					if (game.guessLeft() <= 0) {
						// Incase it is now game over, after minus 1, so
						// have to check
						// again.
						response = "Game Over, you have lost.";
					}
				} catch (GameOverException e) {
					response = "Game is Over, try new Phrase.";
				} catch (EmptyListException e) {
					response = "The list of words and phrases is empty, no gameplay can be played.";
				}
			}
		} else {
			response = "Invalid entry : 1 letter only in letter field.";
		}
		if (response.length() != 0) {
			JOptionPane.showMessageDialog(null, response, "Input", 1);
		}
	}

	private class InteractionListener implements ActionListener {
		private InteractionPane pane;

		public InteractionListener(InteractionPane pane) {
			this.pane = pane;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Try Letter")) {
				String input = (pane.getLetterField());
				tryThis(input);

			} else if (actionCommand.equals("Try Phrase")) {
				String input = (pane.getWordField());
				tryWord(input);

			} else if (actionCommand.equals("New Phrase")) {
				try {
					game.newGame();
					currentWordDisplay.setText(game.getVisible());
					triesDisplay.setText("" + game.guessLeft());
					usedDisplay.setText(game.getLettersDisplay());
					JOptionPane.showMessageDialog(null, "New phrase ready.",
							"New Game", 1);
				} catch (EmptyListException e1) {
					currentWordDisplay.setText("List is Empty.");
					triesDisplay.setText("Zero");
					usedDisplay.setText("");
					JOptionPane
							.showMessageDialog(
									null,
									"The list of words and phrases is empty, no gameplay can be played.",
									"New Game", 1);
				}

			} else if (actionCommand.equals("Get Instructions.")) {
				JOptionPane.showMessageDialog(null, game.instructions()
						+ "-----", "Instructions", 1);

			} else {
				// System.out.println("ERROR: Unexpected ActionCommand!");
				JOptionPane.showMessageDialog(null,
						"ERROR: Unexpected ActionCommand!", "ERROR", 1);
			}

		}

		private void tryThis(String letter) {
			pane.tryThis(letter);
		}

		private void tryWord(String word) {
			pane.tryWord(word);
		}

	}

}
