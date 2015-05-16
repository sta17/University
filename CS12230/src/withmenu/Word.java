package withmenu;
/**
 * This just basically contains the details for the english and the pirate version of the phrace.
 * 
 * @author Sta17
 * @version 30.11.2013
 */
public class Word implements Comparable <Word> {
    private String pirate;
    private String english;

    /**
     * Constructor, used to create a new instance of the class Word.
     * 
     * @param  pirate this is the pirate version of the phrace or word
     * @param  english this is the english version of the phrace or word
     */
    public Word(String pirate, String english){
        this.pirate = pirate;
        this.english = english;
    }

    //=====================toString=============================

    /**
     * Used for listing the english and its translation version.
     * @return returns - the english and pirate version to the caller.
     */
    public String toString(){
        return "English version: "+english+"\n Pirate version: "+pirate;
    }

    //=====================Diverse=============================

    /**
     * Overrides the compareTo method.
     * used for sorting object a to b.
     * @param w this is the word it wants to compare against.
     */
    public int compareTo(Word w){
        return (this.english).compareTo(w.english);
    }
    
    //=====================get Methods=============================
    
    /**
     * gets the pirate string and returns it to caller.
     * @return pirate - the string containing pirate
     */
    public String getPirate(){
        return pirate;
    }

    /**
     * gets the english string and returns it to caller.
     * @return english - the string containing english
     */
    public String getEnglish(){
        return english;
    }
}
