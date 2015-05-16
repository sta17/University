/**
 * Based on model view controller, so is model split into separate package for better handling.
 * This Class is also made with template form in mind, thus it does not have any print statement 
 * as it is up to the classes that uses it to do that.
 * 
 * This class here was designed with the idea in mind that for both for example a GUI version and 
 *  a text based version could use the same list, rather than having to close and open there own 
 *  individual ones, thus the arraylist had to be filtered out of the GameModel. This is the 
 *  interface for it, so that if needed it could be integrated into any other class.
 */
package uk.ac.aber.dcs.sta17.cs12420.as.model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class here was designed with the idea in mind that for both for example
 * a GUI version and a text based version could use the same list, rather than
 * having to close and open there own individual ones. And this interface is
 * designed to have all the functions demand that any list class are needed to
 * have for the GameModel to function, what you do outside of that is up to the
 * user.
 * 
 * Contains List of all the words to pick from
 * 
 * @author Sta17/Steven
 * @version 19.03.2014
 * 
 */
public interface WordListInterface {

	/**
	 * Adds new phrase to wordlist.
	 * 
	 * @param phrase
	 *            the word to be added to the wordlist.
	 * @throws Exception - the automatic saving causes exceptions to be thrown.
	 */
	public void addPhrase(String phrase) throws Exception;

	/**
	 * Enables a user to delete a phrase from the wordlist ArrayList. Searches
	 * thought the ArrayList to see if input matches ArrayList content. also
	 * returns true or false depending upon success of finding match.
	 * 
	 * @param phrase
	 *            - phrase is the word you want to have removed.
	 * @throws Exception - the automatic saving causes exceptions to be thrown.
	 */
	public Boolean removePhrase(String phrase) throws Exception;

	/**
	 * this method returns the size of the list in the class.
	 */
	public int getSize();

	/**
	 * this method returns a string of the entry spesified by index.
	 * 
	 * @param index
	 *            takes in the index number of the entry to be returned.
	 */
	public String getPhrase(int index);

	/*
	 * =========================================================================
	 * ===================== Save and Load
	 * =========================================================================
	 */

	/**
	 * Save mechanism, saves information in to a file, that matches parameter
	 * filename. The method rethrows several exceptions up the chain, because
	 * GameModel is supposed to be no text.
	 * 
	 * @param fileName
	 *            - the name of the file to which the information is stored.
	 * @throws IOException
	 */
	public void save(String fileName) throws IOException;

	/**
	 * load mechanism, loads information from a file, that matches parameter
	 * filename. The method rethrows several exceptions up the chain, because
	 * GameModel is supposed to be no text.
	 * 
	 * @param fileName
	 *            - the name of the file from which the information is
	 *            retrieved.
	 * @throws FileNotFoundException
	 *             to indicate potensial file is not found to force handeling.
	 */
	public void load(String fileName) throws FileNotFoundException;
}
