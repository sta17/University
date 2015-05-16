package uk.ac.aber.dcs.sta17.cs12420.as.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.sta17.cs12420.as.model.GameModel;
import uk.ac.aber.dcs.sta17.cs12420.as.model.GameOverException;
import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

/**
 * 
 * 
 * @author Steven
 * 
 */
public class GameModelTest {
	private WordList list;
	private GameModel game;
	private char[] charlist;
	private int charCount = 0;
	
	private String finefile = "JUnitLists/finelist.txt";
	

	@Before
	public void setupData() {
		list = new WordList(finefile);

		try {
			list.load(finefile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		game = new GameModel(list);

	}

	/**
	 * had some issues with toString so made a method to compare the content of
	 * array as a string.
	 * 
	 * @param charlist
	 *            used in test method.
	 * @param charCount
	 *            used in the test method.
	 * @return the sum of the character list as a string
	 */
	private String charlistSummary(char[] charlist, int charCount) {
		String temp = "";
		for (int i = 0; i < charCount; i++) {
			String minitemp = "" + charlist[i];
			temp += minitemp;
		}
		return temp;
	}

	/**
	 * Simple method to try letters multiple time, so that guesses goes down.
	 * 
	 * @param input
	 *            the characters to take input from
	 */
	@SuppressWarnings("unused")
	private void RunTenTests(String input) {
		for (int i = 0; i < 10; i++) {

			// Set Fixture Data

			String lettergetter = input.substring(0, 1);
			char letter = input.charAt(0);

			// Exercise the implementation class

			game.tryThis(letter);

			int b = input.length();

			input = input.substring(1, b);
		}
	}
	
	// ---------------------------
	// Testing TryThis Method here
	// ---------------------------

	@Test
	public void TestTryThisAcceptsTrueOnRight() {
		// Set Fixture Data

		String String = game.getHidden();
		char letter = String.charAt(0);

		// Exercise the implementation class

		// Check that the method returns true when expected.
		assertTrue("The letters do not match at all", game.tryThis(letter));
	}

	@Test
	public void TestTryThisReturnFalseOnWrong() {
		// Set Fixture Data

		String String = "z"; // Z is picked because it is not in finelist.txt
		char letter = String.charAt(0);

		// Exercise the implementation class

		assertFalse("The letters do not match at all", game.tryThis(letter));
	}

	@Test
	public void TestTryThisCorrectCharRemove() {
		// Set Fixture Data

		String string = game.getHidden();
		string = string.substring(0, 1);
		char letter = string.charAt(0);

		// Exercise the implementation class

		game.tryThis(letter);

		String CharlistOutput = charlistSummary(charlist, charCount);

		// Check that the method returns false when expected.
		assertFalse(
				"Character has not been removed from character check list.",
				CharlistOutput.contains(string));
	}

	@Test
	public void TestTryThisGuessLeftProperUpdate() {

		// Set Fixture Data
		String string = "zabcdfjkmop456789";
		@SuppressWarnings("unused")
		String lettergetter = string.substring(0, 1);
		@SuppressWarnings("unused")
		char letter = string.charAt(0);

		RunTenTests(string);

		// assert that the intended value of guesses left is correct.
		assertEquals("The Number of guesses left is not properly updating", 0,
				game.guessLeft());
	}

	@Test(expected = GameOverException.class)
	public void TestTryThisGuessLeftProperUpdateAndGetGameOverException() {

		// Set Fixture Data
		String string = "zabcdfjkmop456789";
		@SuppressWarnings("unused")
		String lettergetter = string.substring(0, 1);
		char letter = string.charAt(0);

		RunTenTests(string);

		lettergetter = string.substring(0, 1);

		// Check that the method updates the guessleft to correct sum.
		// and will now that it is 0 throw a GameOverException, because, no game
		// when at 0 guessleft.
		game.tryThis(letter);
	}

	@Test
	public void TestTryThisAndGetLetters() {

		// Set Fixture Data
		String string = "zabcdfjkmop456789";

		RunTenTests(string);

		// Check that the method updates the guessleft to correct sum.
		// and will now that it is 0 throw a GameOverException, because, no game
		// when at 0 guessleft.

		assertEquals(
				"The Used Characters are not properly added to the used list.",
				"zabcdfjkmo", game.getLetters());
	}
	
	@Test
	public void TestTryThisAndGetLettersDisplay() {

		// Set Fixture Data
		String string = "zabcdfjkmop456789";

		RunTenTests(string);

		// Check that the method updates the guessleft to correct sum.
		// and will now that it is 0 throw a GameOverException, because, no game
		// when at 0 guessleft.

		assertEquals(
				"The Used Characters are not properly added to the used list.",
				"ZABCDFJKMO", game.getLettersDisplay());
	}

	// ---------------------------
	// Testing TryWord Method here
	// ---------------------------

	@Test
	public void TestTryWordTrueInput() {

		// Check that the method returns true when expected.
		assertTrue("The letters do not match at all",
				game.tryWord(game.getHidden()));
	}

	@Test
	public void TestTryWordDisplayOutputCorrect() {

		// Exercise the implementation class
		game.tryWord(game.getHidden());

		assertEquals("The Words do not match.", game.getHidden(),
				game.getVisible());
	}

	@Test
	public void TestTryWordFalseInput() {

		// Check that the method returns true when expected.
		assertFalse("The letters do not match at all", game.tryWord(game.getHidden()+"string"));
	}

	@Test(expected = GameOverException.class)
	public void TestTryWordGuessLeftProperUpdateAndGetGameOverException() {

		// Set Fixture Data
		String string = "zabcdfjkmop456789";
		String lettergetter = string.substring(0, 1);

		for (int i = 0; i < 2; i++) {

			// Exercise the implementation class

			lettergetter = string.substring(0, 1);

			game.tryWord(lettergetter);

			string = string.substring(1, string.length());
		}

		lettergetter = string.substring(0, 1);

		// Check that the method updates the guessleft to correct sum.
		// and will now that it is 0 throw a GameOverException, because, no game
		// when at 0 guessleft.
		game.tryWord(lettergetter);
	}

	@Test
	public void TestTryWordGuessLeftProperUpdate() {

		// Set Fixture Data
		String string = "zabcdfjkmop456789";
		String lettergetter = string.substring(0, 1);

		for (int i = 0; i < 2; i++) {

			// Exercise the implementation class

			lettergetter = string.substring(0, 1);

			game.tryWord(lettergetter);
		}

		assertEquals("The tryWord dont update the guesses left properly", 0,
				game.guessLeft());
	}
}
