package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import uk.ac.aber.dcs.sta17.cs12420.as.model.EmptyListException;
import uk.ac.aber.dcs.sta17.cs12420.as.model.GameModel;
import uk.ac.aber.dcs.sta17.cs12420.as.model.GameOverException;
import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

/**
 * This the Class that handles the menu, sets up the save, load for individual
 * files and ArrayLists in the txt version. to simplify and manage the content
 * so has the menu and its individual actions been split of, into private
 * methods.
 * 
 * @author Sta17
 * @version 30.11.2013
 */
public class TextVer {

	private Scanner input;
	private GameModel game;
	private WordList list;

	/**
	 * Constructor, creates new instance of class MainContent, declaring
	 * Arraylists and scanner.
	 */
	public TextVer(WordList wordlist) {
		list = wordlist;
		input = new Scanner(System.in);
		game = new GameModel(wordlist);
	}

	/*
	 * ==========================================================================
	 * ===================== View bit.
	 * =========================================================================
	 */

	/**
	 * This is an overview of the menu for options in the method runTheMenu()
	 * right below. Which tells the user what to write in to do what. Its a
	 * print method. The line is splitt into several to provide clearity and
	 * overview.
	 */
	private void printMenu() {
		System.out.println("Menu input is case insensitive. \n"
				+ "What would you like to do: \n " + "L - Try a letter \n "
				+ "W - try a word \n " + "N - Starts a new game \n "
				+ "1 - Print Pirate wordlist \n "
				+ "2 - Print instructions \n " + "3 - add phrase \n "
				+ "4 - Remove a phrase \n "
				+ "5 - Change the list used to pick phrases another one. \n "
				+ "M - print menu \n " + "Q - quit \n");
	}

	/**
	 * runTheMenu() method runs from the main and allows entry of data. Data is
	 * handled differently depending on option.
	 */
	public void runTheMenu() {
		// menu input
		String response;
		printMenu();
		System.out.println("Welcome to hangman, enter 2 for instructions.");

		do {// this is the standard print content after each attempt.
			System.out.println("" + "You have guesses letters: \n"
					+ game.getLettersDisplay() + "\n" + "You have "
					+ game.guessLeft() + " guesses left \n"
					+ "The phrase you have to guess is: " + game.getVisible()
					+ "\n" + "\n" + "input is case insensitive.\n"
					+ "Type L to try a letter. Type W to try a word.\n"
					+ "Type M to see full menu. Type Q to quit.\n");

			response = input.nextLine();

			// Try a word
			if (response.equalsIgnoreCase("W")) {
				System.out.println("Enter a pirate phrase to try: ");
				String wordtry = input.nextLine();
				tryWord(wordtry);
				System.out.println("M for menu.");
			}

			// try a single letter
			else if (response.equalsIgnoreCase("L")) {
				System.out.println("Enter a letter to try: ");
				String letter = input.nextLine();
				tryThis(letter);
				System.out.println("M for menu.");
			}

			if (response.equalsIgnoreCase("N")) {
				try {
					game.newGame();
					System.out.println("New Game Started");
				} catch (EmptyListException e) {
					System.out
							.println("The list of words and phrases is empty, no gameplay can be played.");
				}
			}

			// print the pirate list
			else if (response.equals("1")) {
				System.out.println(list.toString());
				System.out.println("M for menu.");
			}

			// print the instructions
			else if (response.equals("2")) {
				System.out.println(game.instructions());
				System.out.println("M for menu.");
			}

			// Add a word to the wordlist
			else if (response.equalsIgnoreCase("3")) {
				System.out.println("Enter a pirate phrase: ");
				String add = input.nextLine();
				addWord(add);
				System.out.println("M for menu.");
			}

			// deletes a word/phrace in the arraylists
			else if (response.equals("4")) {
				System.out.println("Enter a pirate phrase to remove: ");
				String remove = input.nextLine();
				removeWord(remove);
				System.out.println("M for menu.");
			}

			// change the list used to another one.
			else if (response.equals("5")) {
				System.out
						.println("Enter the txt file with full path name to change to: ");
				String pathname = input.nextLine();
				changeList(pathname);
				System.out.println("M for menu.");
			}

			// prints menu
			else if (response.equalsIgnoreCase("M")) {// prints menu
				printMenu();
			}
		} while (!((response.equalsIgnoreCase("Q"))));// quits
		try {
			list.save(list.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * =========================================================
	 * ==================== Controller bit.
	 * =========================================================
	 */

	/**
	 * This checks user input, to see if it is legal.
	 * 
	 * @param wordtry
	 *            to check and try in GameModel class tryWord method
	 */
	private void tryWord(String wordtry) {
		if (game.guessLeft() == 0) {
			System.out
					.println("Game Over, you lost the game.\n Enter N for new phrase.\n");
		} else if (game.getHidden().equals(game.getVisible())) {
			System.out
					.println("Game Over, you won the game.\n Enter N for new phrase.\n");
			// check if input actually contains anything to match.
		} else if (wordtry == null || wordtry.isEmpty()
				|| wordtry.trim().isEmpty()) {
			System.out.println("No actuall input.");
		} else {

			try {
				if (game.tryWord(wordtry)) {
					if (game.guessLeft() <= 0) {
						// Incase it is now game over, after minus 1, so
						// have to check
						// again.
						System.out.println("Game Over, you have lost.");
					} else if (game.getHidden().equals(game.getVisible())) {
						System.out
								.println("Congratulations you have guesses the word.");
					}
				}
			} catch (GameOverException e) {
				System.out.println("Game is over.\n");
			} catch (EmptyListException e) {
				System.out
						.println("The list of words and phrases is empty, no gameplay can be played.");
			}
		}
	}

	/**
	 * This checks user input, to see if it is legal.
	 * 
	 * @param letter
	 *            the letter that is to be tried
	 */
	private void tryThis(String guess) {
		if (guess == null || guess.isEmpty() || guess.trim().isEmpty()) {
			System.out.println("No actuall input.");
		} else if (game.guessLeft() == 0) {
			System.out
					.println("Game Over, you lost the game.\n Enter N for new phrase.\n");
		} else if (game.getHidden().equals(game.getVisible())) {
			System.out
					.println("Game Over, you won the game.\n Enter N for new phrase.\n");
			// check if input actually contains anything to match.
		} else if (guess == null || guess.isEmpty() || guess.trim().isEmpty()) {
			System.out.println("No actuall input.");

			// checks if the letter has already been used
		} else {
			String letter = guess.substring(0, 1);

			if (game.getLetters().toLowerCase().contains(letter.toLowerCase())) {
				System.out.println("Letter already tried.");
				// the other bits
			} else {

				char temp = letter.charAt(0);

				try {
					if (game.tryThis(temp) == true) {
						if (game.guessLeft() <= 0) {
							// Incase it is now game over, after minus 1, so
							// have to check
							// again.
							throw new GameOverException("Game Over");
						} else if (game.getHidden().equalsIgnoreCase(
								game.getVisible())) {
							System.out
									.println("Congratulations you have guesses the word.");
						}
					}
				} catch (GameOverException e) {
					System.out.println("Game Over, you have lost the game.\n");
				} catch (EmptyListException e) {
					System.out
							.println("The list of words and phrases is empty, no gameplay can be played.");
				}
			}
		}
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
				System.out.println("Please enter proper word.");
			} else {
				list.addPhrase(add);
				System.out.println("Word Successfully added.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Word already exist in list.");
		} catch (FileNotFoundException e) {
			System.out.println("Word added but not saved, due to File not found error.");
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
			System.out.println("Please enter proper phrase.");
		} else
			try {
				if (list.removePhrase(remove)) {
					System.out.println("Phrase Successfully removed.");
				} else {
					System.out.println("Input: " + remove + " was not found.");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Word removed but not saved, due to File not found error.");
			}
	}

	/*
	 * ==========================================================
	 * ===================== Save and Load
	 * ==========================================================
	 */

	/**
	 * save() method runs from the main and writes back to piratewords.txt calls
	 * save(filename) in WordDatabase class
	 * 
	 * Save mechanism, saves information in to a file, that matches parameter
	 * fileName.
	 */
	public void save() {
		System.out.println("Starting Saving.");
		try {
			list.save(list.getFileName());
			System.out.println("Saving Success.");
		} catch (IOException e) {
			System.out.println("Saving Failed: ");
		} catch (RuntimeException e) {
			System.out.println("Saving Failed: Unexpected error, " + e);
		}
	}

	/**
	 * 
	 * load() method runs from the main and reads from piratewords.txt calls
	 * load method in WordDatabase class.
	 * 
	 * Load mechanism, loads information from a file, that matches parameter
	 * filename.
	 */
	public void load() {
		System.out.println("Statring Loading.");
		try {
			list.load(list.getFileName());
			System.out.println("Loading Success.");
		} catch (FileNotFoundException e) {
			System.out.println("Loading Failed: file " + list.getFileName()
					+ " not found.");
		} catch (IllegalArgumentException e) {
			System.out.println("Loading Failed: " + e);
		} catch (RuntimeException e) {
			System.out.println("Loading Failed: Unexpected error, " + e);
		}
	}

	public void changeList(String fileName) {
		try {
			if (fileName == null || fileName.isEmpty()
					|| fileName.trim().isEmpty()) {
				System.out.println("Please enter proper filename path.");
			} else if (!new File(fileName).isFile()
					&& !new File(fileName).exists()
					&& !new File(fileName).isDirectory()) {
				throw new FileNotFoundException("The file does not exist.");
			} else {
				list.changeList(fileName);
				System.out.println("File Successfully Switched.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File: " + fileName + " not found.");
		} catch (IOException e) {
			System.out.println(e);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}

}