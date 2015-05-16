package uk.ac.aber.dcs.sta17.cs12420.as.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

/**
 * 
 * 
 * @author Steven
 * 
 */
public class WordListTest {
	private WordList list;
	
	private String finefile = "JUnitLists/finelist.txt";
	private String error1file = "JUnitLists/errorlist1.txt";
	private String error2file = "JUnitLists/errorlist2.txt";
	private String error3file = "JUnitLists/errorlist3.txt";
	private String savingfile = "JUnitLists/SavingTest.txt";

	/*
	 * ----------------------------------------------------------------------
	 * Testing Loading of files are done in the FileExistanceTest, because the
	 * files have been set up to both pickup files and check for expected errors
	 * ----------------------------------------------------------------------
	 * 
	 * ----------------------------------------------------------------------
	 * There are 2 methods in WordList, the get(int index) and Size that are not
	 * tested, they are basic methods, used as shells to provide easier
	 * Usability of the Wordlist class and are tested thought other methods.
	 * ----------------------------------------------------------------------
	 * 
	 * ----------------------------------------------------------------------
	 * The private UniqueWord method is an integral part of other methods as the
	 * tester for duplication of words, its ability is tested above when testing
	 * for duplication and not duplication.
	 * ----------------------------------------------------------------------
	 */

	@Before
	public void setupData() throws FileNotFoundException {
		list = new WordList(finefile);
		list.removePhrase("simple");

	}

	// ------------------------
	// Testing addWord here
	// ------------------------

	@Test
	// also tests get method for success
	public void testAddWordandGetAgain() throws FileNotFoundException {

		// Exercise the implementation class
		String simpleWord = "simple";
		list.addPhrase(simpleWord);

		String testWord = list.getPhrase(0);

		// Check that the actual outputs from imp class
		// are same as expected outputs
		assertEquals("These are the same words", simpleWord, testWord);

	}
	
	@Test(expected = IllegalArgumentException.class)
	// also tests get method for success
	public void testAddWordandGetDuplicationError() throws FileNotFoundException {

		// Exercise the implementation class
		String simpleWord = "simple";
		list.addPhrase(simpleWord);
		list.addPhrase(simpleWord);

	}

	// ------------------------
	// Testing Get method here
	// ------------------------

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetAgainAndFail() {

		// Exercise the implementation class
		list.getPhrase(0);

	}

	// ------------------------
	// Testing removeWord here
	// ------------------------

	@Test//also mean successful removal
	public void testRemoveReturnTrueUponExistingWord() throws FileNotFoundException {
		
		// Exercise the implementation class
		String simpleWord = "simple";
		try {
			list.addPhrase(simpleWord);
		} catch (FileNotFoundException e) {}

		assertTrue("Remove does work and find stuff",
				list.removePhrase(simpleWord));
	}

	@Test//also test for none-existent word
	public void testRemoveReturnFalseUponNoneExistingWord() throws FileNotFoundException {

		assertFalse("Remove does work and find stuff",
				list.removePhrase("simple"));
	}

	// ------------------------
	// Testing Saving here
	// ------------------------
	
	@Test
	public void TestSavingSuccessfully() {

		// Exercise the implementation class
				String simpleWord = "simple";
				try {
					list.addPhrase(simpleWord);
				} catch (FileNotFoundException e1) {}
		try {
			list.save(savingfile);
		} catch (IOException e) {}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestSavingEmptyList() {

		// Exercise the implementation class
		try {
			list.save(error3file);
		} catch (IOException e) {}
	}
	
	// ------------------------
	// Testing Loading here
	// ------------------------

	@Test
	public void TestSuccessfullLoad() {
		Boolean testResult;

		try {
			list.load(finefile);
			testResult = true;
		} catch (FileNotFoundException e) {
			System.out.println("finelist.txt Basically the File is gone.");
			testResult = false;
		}

		assertTrue("finelist.txt does not exist!", testResult);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestWhiteSpaceDetectionInLoad() {
		// act
		try {
			list.load(error1file);
		} catch (FileNotFoundException e) {
			System.out.println("errorlist1.txt Basically the File is gone.");
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestDuplicationErrorDetectionInLoad() {

		try {
			list.load(error2file);
		} catch (FileNotFoundException e) {
			System.out.println("errorlist2.txt Basically the File is gone.");
		}
	}

	@Test
	public void TestLoadingEmptyList() {
		Boolean testResult = false;

		try {
			list.load(savingfile);
			testResult = true;
		} catch (FileNotFoundException e) {
			System.out.println("SavingTest.txt Basically the File is gone.");
		}
		assertTrue("SavingTest.txt does not exist!", testResult);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestAttemptingToLoadWithEmptyFileName() {

		try {
			list.load("");
		} catch (FileNotFoundException e) {
			System.out.println("errorlist2.txt Basically the File is gone.");
		}
	}
	
	@Test(expected = FileNotFoundException.class)
	public void TestAttemptingToLoadNoneExsistantFile() throws FileNotFoundException {

		list.load("trala");
	}
	
	// ---------------------------
	// Testing Changing List here
	// ---------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void TestAttemptingtoChangetoNothing() throws IOException {

		try {
			list.changeList("");
		} catch (FileNotFoundException e) {
			System.out.println("errorlist2.txt Basically the File is gone.");
		}
	}
	
	@Test(expected = FileNotFoundException.class)
	public void TestAttemptingtoNoneExsistantFile() throws FileNotFoundException {

		try {
			list.changeList("trala");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
		}
	}
	
}
