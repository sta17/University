package withmenu;
import java.util. *;
import java.io. *;
/**
 * Contains the arraylist of all the words, of both version of english and its pirate version.
 * Contains the translator as well as a 3 word/phrace generator.
 * 
 * @author Sta17
 * @version 30.11.2013
 */
public class Dictonary{
    // instance variables - replace the example below with your own
    private ArrayList<Word> wordlist;

    /**
     * Constructor for objects of class Dictonary
     */
    public Dictonary()
    {
        // initialise instance variables
        wordlist = new ArrayList<Word>();
    }

    /*===============================================================================================
     *                      Add, remove, toString + Support
     * ==============================================================================================
     */
    
    /**
     * Adds new word to Dictonary, also check if word already exists and if input is empty or whitespace.
     * @param english english is the english version of the word.
     * @param pirate pirate is the pirate version of the word.
     */
    public void addWord(String english, String pirate) {
        if(pirate.isEmpty() || pirate.trim().isEmpty()|| english.isEmpty() || english.trim().isEmpty()){
            System.out.println("Please enter proper English and Pirate equvalent phrace.");
        }
        else if(uniqueWord(english) == false || uniqueWord(pirate) == false){
            System.out.println("Please enter unique English and Pirate equvalent phrace.");
        }
        else{
            Word tempWord = new Word(pirate, english);
            wordlist.add(tempWord);
            System.out.println("Phrace added.");
        }
    }

    /**
     * This is to prevent identical words from cluttering up the dictonary.
     * @param phrace the phrace to be tested, to see if it already exist in dictonary
     * @return unique - true if it does exist and false if not.
     */
    private boolean uniqueWord(String phrace){
        for (Word w:wordlist) {
            if (phrace.equalsIgnoreCase(w.getPirate()) || phrace.equalsIgnoreCase(w.getEnglish()))  {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Enables a user to delete a word from the dictonary arraylist.
     * Searches thought the arraylist to see if input matches arraylist content.
     * 
     * @param word word is the word you want to have removed.
     */
    public void removeWord(String word) {
        for (Word w:wordlist) {
            if (word.equals(w.getEnglish()) || word.equals(w.getPirate()))  {     
                wordlist.remove(w);
                System.out.println("Name successfully removed.");
                return;
            }
        }
        System.out.println("word not on list");
    }
    
    /**
     * Provides information on and words of the Arraylist.
     * @return temp - arraylist size and all the words in it.
     */
    public String toString() {
        Collections.sort(wordlist);// Sorts the array list
        String temp="";
        for (Word w: wordlist) {
            temp=temp+w.toString()+"\n";
        }
        temp =  "Dictonary size is: " +wordlist.size()+"\n Words are:\n"+"\n"+temp;
        return temp;
    }

    /*===============================================================================================
     *                      Diverse Methods
     * ==============================================================================================
     */
    
    /**
     * Generates a 3 word/phrace sentance once called, does not print
     * @return temp - the generated 3 word/phrace, sentance
     */
    public String phrGen(){//Shortened down from phraseGenerator
        String temp = "";
        Random rand = new Random();
        for(int i=0;i<4;i++){//only want 3 phraces/words
            int index = rand.nextInt((wordlist.size() - 1));// minus 1 to correct size.
            Word w = wordlist.get(index);
            temp += " "+(w.getPirate());
        }
        return temp = "Random Generated Phrace:\n"+temp;
    }

    /**
     * Translate to Pirate speak.  thought dictonary, and send back pirate phraces.
     * @return phrace - the translated phrace, from english to pirate
     */
    public String TranslatetoPir(String phrace){
        for (Word w:wordlist) {
            if (phrace.contains(w.getEnglish()))  {
                phrace = phrace.replace(w.getEnglish(),w.getPirate());
            }
        }
        return phrace;
    }

    /**
     * Translate to the english language. Search thought dictonary, and send back english phraces
     * @return phrace -  the translated phrace, from phrace to english
     */
    public String TranslatetoEng(String phrace){
        for (Word w:wordlist) {
            if (phrace.contains(w.getPirate()))  {
                phrace = phrace.replace(w.getPirate(),w.getEnglish());
            }
        }
        return phrace;
    }

    /*===============================================================================================
     *                      Save and Load 
     * ==============================================================================================
     */
    
    /**
     * Save mechanism, saves information in to a file, that matches parameter filename.
     * @param fileName - the name of the file to which the information is stored.
     */
    public void save(String fileName){
        try{
            PrintWriter outfile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            outfile.println(wordlist.size());
            for (Word w:wordlist) {
                outfile.println(w.getPirate());
                outfile.println(w.getEnglish());
            }
            System.out.println(fileName+" successfully saved.");
            outfile.close();
        }
        catch (IOException e) {
            System.out.println("cant find file: "+fileName);
        }
        catch(Exception e) {
            System.out.println("Error during saving of "+fileName);
        }
    }

    /**
     * load mechanism, loads information from a file, that matches parameter filename.
     * @param fileName - the name of the file from which the information is retrieved.
     */
    public void load(String fileName){
        try{ 
            Scanner infile =new Scanner(new InputStreamReader(new FileInputStream(fileName)));
            int num=infile.nextInt();infile.nextLine();
            for (int i=0;i<num;i++) {
                String p=infile.nextLine();
                String e=infile.nextLine();

                Word w=new Word(p,e);
                wordlist.add(w);
            }
            Collections.sort(wordlist);// Sorts the array list
            System.out.println(fileName+" successfully loaded.");
            infile.close();
        }
        catch(IOException e) {
            System.out.println("cant find file: "+fileName);
        }
        catch (InputMismatchException e) {
             System.out.println("cannot add - weird input");
        }
        catch(Exception e) {
            System.out.println("Error during loading of "+fileName);
        }
    }
}