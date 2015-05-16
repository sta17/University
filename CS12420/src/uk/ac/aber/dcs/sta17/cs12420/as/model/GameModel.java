/**
 * Based on model view controller, so is model split into separate package for better handling.
 * This Class is also made with template form in mind, thus it does not have any print statement 
 * as it is up to the classes that uses it to do that.
 */
package uk.ac.aber.dcs.sta17.cs12420.as.model;

import java.util.Observable;
import java.util.Random;

/**
 * This is the basic GameModel interface which is to serve as the game basic
 * core.
 * 
 * @author Sta17/Steven
 * @version 19.03.2014
 * 
 */
public class GameModel extends Observable implements GameModelInterface {

	private String visible, hidden, usedletters, usedlettersDisplay;
	private int guessesleft, preguessesleft, charCount;
	private char[] charlist;
	private WordList wordlist;

	/**
	 * the constructor
	 * 
	 */
	public GameModel(WordListInterface wordlist) {
		this.wordlist = (WordList) wordlist;
		guessesleft = 10;
		preguessesleft = 0;
		usedletters = "";
		usedlettersDisplay = "";
		hidden = "";
		visible = "";
		generateHidden();
		if (this.wordlist.getSize() == 0) {
			visible = "List is Empty.";
		} else {
			charCount = (hidden.replaceAll("\\s+", "")).length();
			charlist = toCharArray(hidden.replaceAll("\\s+", ""));
			charlist = eliminateDuplicateCharArray(charlist);
			generateVisible();
		}
	}

	/*
	 * =========================================================================
	 * ===================== Private Methods
	 * =========================================================================
	 */

	/**
	 * this is responsible for generating random word from the wordlist in
	 * wordlist class.
	 * 
	 * @return temp the phrase picked from the list.
	 */
	private void generateHidden() {
		String temp = "";
		if (wordlist.getSize() > 1) {
			Random rand = new Random();
			int size = (wordlist.getSize() - 1);// minus 1 to correct size.
			int index = rand.nextInt(size); // it starts at 0 not 1.
			temp = wordlist.getPhrase(index);
		} else if (wordlist.getSize() == 1) {
			temp = wordlist.getPhrase(0);
		} else {
			temp = "List is Empty.";
		}
		hidden = temp;
	}

	/**
	 * uses the hidden word variable as basis to construct a hidden
	 * version(censored) version out of it.
	 * 
	 * @return temp is the progress updated string.
	 */
	private void generateVisible() {
		String temp = hidden;
		String star = "*";
		String ch = "";

		for (int i = 0; i < charCount; i++) {
			ch = "" + charlist[i];
			temp = temp.replace(ch, star);
		}

		visible = temp;
	}

	/**
	 * turns a string into a series of characters in and array, which are used
	 * for checking the letter have been used.
	 * 
	 * @param input
	 *            the hidden word in this case to add to the array
	 */
	private char[] toCharArray(String input) {
		input = input.replaceAll("\\s+", "");
		input = input.replaceAll(",", "");
		input = input.replaceAll("!", "");
		input = input.replaceAll("\\?", "");
		input = input.replaceAll("\\.", "");
		input = input.replaceAll("\\'", "");
		int size = input.length();
		char[] charlist = new char[size];
		for (int i = 0; i < size; i++) {
			charlist[i] = input.charAt(i);
		}
		charCount = size;
		return charlist;
	}

	/**
	 * first it sets up a loop to go thought all the characters in the array
	 * then it removes and corrects the letters so that the charlist don't
	 * contain the correct letter, because, don't want it guessed twice.
	 * 
	 * @param letter
	 *            the letter to be removed.
	 */
	private char[] updateCharArray(char letter) {
		int tempcharCount = charCount;
		char[] tempcharlist = charlist;
		for (int i = 0; i < tempcharCount; i++) {
			if (letter == charlist[i]) {
				for (int y = i; y < tempcharCount - 1; y++) {
					tempcharlist[y] = tempcharlist[y + 1];
				}
				tempcharCount--;
			}
		}
		charCount = tempcharCount;
		return tempcharlist;
	}

	/**
	 * simple method, it is designed to remove duplications in the char array.
	 * 
	 * @param tempcharlist
	 *            is the char array which duplications are to be removed.
	 * @return tempcharlist is the char array without any duplication in it.
	 */
	private char[] eliminateDuplicateCharArray(char[] tempcharlist) {
		int tempcharCount = tempcharlist.length;

		if (charCount > 2) {
			for (int i = 0; i < tempcharCount; i++) {// go thought entire array

				for (int y = i + 1; y < tempcharCount; y++) {// next in list is
																// to be
																// checked.

					while (charlist[i] == tempcharlist[y] && !(y == tempcharCount)) {// the follow up
															// check bit.
						for (int j = i; j < tempcharCount - 1; j++) {
							tempcharlist[j] = tempcharlist[j + 1];
						}
						tempcharCount--;
						y = i + 1;
					}
				}
			}
		}
		charCount = tempcharCount;
		return tempcharlist;
	}

	/*
	 * =========================================================================
	 * ===================== Diverse Methods
	 * =========================================================================
	 */

	/**
	 * resets all the game settings so that a new game is ready with a new word.
	 */
	public void newGame() {
		SetguessLeft(10);
		preguessesleft = 0;
		usedletters = "";
		usedlettersDisplay = "";
		hidden = "";
		visible = "";
		generateHidden();
		if (this.wordlist.getSize() == 0) {
			visible = "List is Empty.";
			throw new EmptyListException("The list of words and phrases is empty, no gameplay can be played.");
		} else {
			charCount = (hidden.replaceAll("\\s+", "")).length();
			charlist = toCharArray(hidden.replaceAll("\\s+", ""));
			charlist = eliminateDuplicateCharArray(charlist);
			generateVisible();
		}
	}

	/**
	 * This just prints the basic hangman instructions and returns it as a
	 * string, the method used return, so the text can be transfered to medium
	 * desired.
	 * 
	 * @return the instructions
	 */
	public String instructions() {
		String temp = "";
		temp += "-------------Instructions:-------------\n"
				+ "You are to input letters and see if it\n"
				+ "matches the hidden word. you can also \n"
				+ "input words if you wish, only remember.\n"
				+ "1 wrong letter and you lose 1 guess, \n"
				+ "1 wrong word and you lose 5 guesses, \n"
				+ "you start with 10 guesses\n"
				+ "---------------------------------------";
		return temp;
	}

	@Override
	/**
	 * this method sees if the letter is in the words
	 * if it is it updates what the user can see (visible)
	 * if not it removes a guess
	 *
	 * @param letter the letter to try
	 * @return whether the letter is correct
	 * @throws GameOverException to indicate that the game is actually over, when attempting to play when should not.
	 * @throws EmptyListException to indicate that the game contains no words or phrases to which to pick from.
	 */
	public boolean tryThis(char letter){

		//checks if there are any words in the list
		if(wordlist.getSize()== 0){
			throw new EmptyListException("The list of words and phrases is empty, no gameplay can be played.");
		// check if the game is over or not, its not supposed to function then.
		} else if (guessesleft <= 0 || hidden.equals(visible) || charlist.length <= 0) {
			throw new GameOverException("Game Over is already over.");
		} 
		
		String letterlow = "" + letter;
		letterlow = letterlow.toLowerCase();
		String hiddenlow = hidden.toLowerCase();
		
		// the check if matching bit. since it is correct,
		if (hiddenlow.contains(letterlow)) {
			// only the internal list is updated, this add letter to the
			// usedletters, don't want the person to fail, on correct letters
			// even if they are not displayed in usedletters section, because
			// they are displayed in the word section.
			usedletters = usedletters + letter;

			updateCharArray(letter);
			generateVisible();
			return true;
		} else {
			// basically it is false, and both the display and internal
			// character list are updated.
			usedletters = usedletters + letter;
			usedlettersDisplay = usedlettersDisplay + letter;
			usedlettersDisplay = usedlettersDisplay.toUpperCase();

			if ((guessesleft - 1) <= 0) {
				SetguessLeft(0);
			} else {
				SetguessLeft(1);
			}
		}// else simply wrong info.
		return false;
	}

	@Override
	/**
	 * this method sees if the word guess is correct
	 * if it is it updates what the user can see (visible)
	 * if not it removes 5 guesses
	 *
	 * @param guess the word to guess
	 * @return whether there is winner
	 * @throws GameOverException to indicate that the game is actually over, when attempting to play when should not.
	 * @throws EmptyListException to indicate that the game contains no words or phrases to which to pick from.
	 */
	public boolean tryWord(String guess){

		if(wordlist.getSize()== 0){
			throw new EmptyListException("The list of words and phrases is empty, no gameplay can be played.");
		// check if the game is over or not, its not supposed to function then.
		}else if(guessesleft == 0 || hidden.equals(visible) || charlist.length <= 0) {
			throw new GameOverException("Game Over is already over.");

			// the checking if matching bit, is it correct word.
		} else if (guess.equalsIgnoreCase(hidden)) {
			visible = hidden;
			// since the entire thing was guessed right, set the entire thing to
			// correct.
			return true;

			// if it is not matching, then false and thus incorrect word.
		} else {
			if ((guessesleft - 5) <= 0) {
				SetguessLeft(0);
			} else {
				SetguessLeft(5);
			}
		}// else simply wrong.
		return false;
	}

	/*
	 * =========================================================================
	 * ===================== Set and Get Methods
	 * =========================================================================
	 */

	@Override
	/**
	 * this method returns what the user is allowed to see
	 */
	public String getVisible() {
		return visible;
	}

	@Override
	/**
	 * this method returns the words to guess
	 */
	public String getHidden() {
		return hidden;
	}

	@Override
	/**
	 * this method tells how many guesses are left
	 */
	public int guessLeft() {
		return guessesleft;
	}

	/**
	 * This method returns the value of before the guessesleft where set using
	 * the SetguessLeft() method
	 * 
	 * @return preguessesleft - the value of preGuessesleft
	 */
	public int preSetGuessLeft() {
		return preguessesleft;
	}

	/**
	 * this method sets how many guesses there are left.
	 * 
	 * @param the
	 *            guesses to be set.
	 */
	private void SetguessLeft(int value) {

		if (guessesleft != value) {
			if (value == 0) {
				preguessesleft = guessesleft;
				guessesleft = 0;
			} else if (value == 10) {
				guessesleft = 10;
			} else {
				preguessesleft = guessesleft;
				guessesleft = guessesleft - value;
			}

			// mark as value changed
			setChanged();
			// trigger notification
			notifyObservers(value);
		}
	}

	@Override
	/**
	 * this method returns the letters that have already been guessed
	 * 
	 * @return all the letters that have been tried.
	 */
	public String getLetters() {
		return usedletters;
	}

	/**
	 * this method returns the letters that have already been guessed for
	 * Display, which is all letters minus correct ones for easiness.
	 * 
	 * @return all the incorrect letters.
	 */
	public String getLettersDisplay() {
		return usedlettersDisplay;
	}
}
