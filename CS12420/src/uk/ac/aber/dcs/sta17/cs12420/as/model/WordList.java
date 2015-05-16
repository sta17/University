package uk.ac.aber.dcs.sta17.cs12420.as.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Contains List of all the words to pick from.
 * 
 * @author Sta17/Steven
 * @version 19.03.2014
 * 
 */
public class WordList implements WordListInterface {

	private List<String> wordlist;
	private String fileName;

	/**
	 * this is the constructor
	 */
	public WordList(String filename) {
		wordlist = new ArrayList<String>();
		this.fileName = filename;
	}
	
	public WordList() {
		wordlist = new ArrayList<String>();
		fileName = "piratewords.txt";
		
	}

	/*
	 * =========================================================================
	 * ===================== Add, remove, toString + Support
	 * =========================================================================
	 */

	/**
	 * Adds new word or phrase to wordlist, also check if word already exists as
	 * part of internal duplication fix.
	 * 
	 * @param phrase
	 *            - the phrase to be added to the wordlist.
	 * @throws FileNotFoundException - rethrowing the exception up the chain to caller.
	 */
	public void addPhrase(String phrase) throws FileNotFoundException{

		if (uniqueWord(phrase)) {
			wordlist.add(phrase);

					try {
						save(fileName);
					} catch (FileNotFoundException e) {
						throw e;
					} catch (RuntimeException e) {
						throw e;
					}
				
		} else {
			throw new IllegalArgumentException(
					"Error: Phrase already exist in list.");
		}
	}

	/**
	 * Enables a user to delete a word from the wordlist ArrayList. Searches
	 * thought the ArrayList to see if input matches ArrayList content. returns
	 * true if the word was removed and false if it was not.
	 * 
	 * @param phrase
	 *            - phrase is the word you want to have removed.
	 * @return - a boolean, indicating success(true) or failure (false) to
	 *         remove word.
	 */
	public Boolean removePhrase(String phrase) throws FileNotFoundException{
		for (String w : wordlist) {
			if (phrase.equals(w)) {
				wordlist.remove(w);

				if (wordlist.size() != 0) {
					try {
						save(fileName);
					} catch (IOException e) {
						throw e;
					}
				}
				return true;
			}
		}

		return false;
	}

	/**
	 * This is to prevent identical words from cluttering up the wordlist.
	 * 
	 * @param input
	 *            the word to be tested, to see if it already exist in wordlist
	 * @return unique - true if it does exist and false if not.
	 */
	private boolean uniqueWord(String input) {
		for (String word : wordlist) {
			if (input.equalsIgnoreCase(word)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Provides information on and words of the List.
	 * 
	 * @return temp - ArrayList size and all the words in it.
	 */
	public String toString() {
		Collections.sort(wordlist);// Sorts the list
		String temp = "";
		for (String w : wordlist) {
			temp = temp + w.toString() + "\n";
		}
		temp = "\nWordlist size is: " + wordlist.size() + "\n Words are:\n"
				+ "\n" + temp;
		return temp;
	}

	/*
	 * =========================================================================
	 * ===================== Get Methods
	 * =========================================================================
	 */

	/**
	 * To provide ease of use of class, this method was added so the size could
	 * be gotten without big fus
	 * 
	 * @return wordlist.size() the lists size, aka number of entries.
	 */
	public int getSize() {
		return wordlist.size();
	}

	/**
	 * This provides a basic find method, returning String of the entry number
	 * provided.
	 * 
	 * @param index
	 *            takes in the index number of the entry to be returned.
	 * @return return the word in that entry specified by param index.
	 */
	public String getPhrase(int index) {
		String temp = wordlist.get(index);
		return temp;

	}

	public String getFileName() {
		return fileName;
	}

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
	 * @throws FileNotFoundException - rethrowing the exception up the chain to caller.
	 * @throws RuntimeException
	 *             - rethrowing the exception up the chain to caller.
	 */
	public void save(String fileName) throws FileNotFoundException{
		try {
			PrintWriter outfile = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(fileName)));

			if (wordlist.size() == 0) {
				outfile.close();
				throw new IllegalArgumentException(
						"Has to be minimum of 1 entry for list to be saved.");
			}
			outfile.println(wordlist.size());
			for (String w : wordlist) {
				outfile.println(w);
			}
			outfile.close();
		}catch (IOException e){
			throw e;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * load mechanism, loads information from a file, that matches parameter
	 * filename. The method rethrows several exceptions up the chain, because
	 * GameModel is supposed to be no text.
	 * 
	 * @param fileName
	 *            - the name of the file from which the information is
	 *            retrieved.
	 * @throws FileNotFoundException
	 *             - rethrowing the exception up the chain
	 * @throws IllegalArgumentException
	 *             - is thrown if the file contains illegal lines or fileName does.
	 * @throws RuntimeException
	 *             - rethrowing the exception up the chain
	 */
	public void load(String fileName) throws FileNotFoundException {
		try {
			if (fileName == null || fileName.isEmpty() || fileName.trim().isEmpty()) {
				throw new IllegalArgumentException("Cannot load nothing");
			} else if (!new File(fileName).isFile() && !new File(fileName).exists() && !new File(fileName).isDirectory()) {
				throw new FileNotFoundException("The file does not exist.");
			}
			
			InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
			Scanner infile = new Scanner(is);
			
			//Scanner infile = new Scanner(new InputStreamReader(new FileInputStream(fileName)));
			
			int num = infile.nextInt();
			if (num == 0) {
				infile.close();
				throw new IllegalArgumentException(
						"File length cannot be 0, please check file.");
			}
			infile.nextLine();
			for (int i = 0; i < num; i++) {
				String w = infile.nextLine();
				if (w == null || w.isEmpty() || w.trim().isEmpty()) {
					infile.close();
					throw new IllegalArgumentException(fileName
							+ " contained a empty line.");
				} else if (uniqueWord(w) == false) {
					infile.close();
					throw new IllegalArgumentException(
							"Word already exist in list.");
				} else {
					wordlist.add(w);
				}
			}
			Collections.sort(wordlist);// Sorts the array list
			infile.close();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	
	/**
	 * This changes the list to the file spesified as a parameter, and follows
	 * up with saving and loading of said lists.
	 * 
	 * @param fileName - to be changed too
	 * @throws RuntimeException
	 *             this is mostly to catch the exceptions from save and throw
	 *             them one step up.
	 */
	public void changeList(String fileName) throws IOException {
		
		if (fileName.length() == 0 || fileName == null || fileName.isEmpty() || fileName.trim().isEmpty()) {
			throw new IllegalArgumentException("Cannot load nothing");
		} else if (!new File(fileName).isFile() && !new File(fileName).exists() && !new File(fileName).isDirectory()) {
			throw new FileNotFoundException("The File Does not exist");
		} else{
			if (wordlist.size() != 0) {
				try {
					save(this.fileName);
				} catch (RuntimeException | IOException e) {
					throw e;
				}
			}
			try {
				this.fileName = fileName;
				this.wordlist = new ArrayList<String>();
				load(fileName);
			} catch (RuntimeException | FileNotFoundException e) {
				throw e;
			}
		}
	}

}
