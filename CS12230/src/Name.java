import java.util. *;
import java.io. *;
/**
 * A class with an ArrayList of Pirate Names for the Generator method NameGen(), 
 * the rest is support functions basically.
 * 
 * @author Sta17
 * @version 30.11.2013
 */
public class Name{
    // instance variables
    private ArrayList<String> names;

    /**
     * Constructor, used to create a new instance of the class Name.
     */
    public Name(){
        names = new ArrayList<String>();
    }

    /*===============================================================================================
     *                      Add, remove, toString + Support
     * ==============================================================================================
     */
    
    /**
     * Adds a new name to Piratename arraylist, also check if string already exists and if input is empty.
     * @param name name first is the name.
     */
    public void addPirateName(String name) {
        if(name.isEmpty() || name.trim().isEmpty()){    
            System.out.println("Please enter a proper name.");
        }
        else if(UniqueName(name) == false){
            System.out.println("Please enter unique name.");
        }
        else{
            String tempname = new String(name);
            names.add(tempname);
            System.out.println("Name added.");
        }
    }

    /**
     * This is to prevent identical names from cluttering up the name list.
     * @param name the name to be tested, to see if it already exist in namelist.
     * @return unique - true if it does exist and false if not.
     */
    private boolean UniqueName(String name){
        for (String n: names) {
            if (name.equals(n))  {
                return false;
            }
        }
        return true;
    }

    /**
     * Enables a user to delete a name from the Piratename arraylist.
     * Searches thought the arraylist.
     * 
     * @param name name is the name you want to have removed.
     */
    public void removeName(String name) {
        for (String n:names) {
            if (name.equals(n))  {     
                names.remove(n);
                System.out.println("Word successfully removed.");
                return;
            }
        }
        System.out.println("Name not on list");
    }

    /**
     * toString, prints the total count of possible pirate names
     * pluss a list of names
     * @return temp the combination of text, arraylist size, names.
     */
    public String toString() {
        Collections.sort(names);// Sorts the array list
        String temp="Number of names: "+names.size()+"\n Pirate names are:\n";
        for (String n: names) {
            temp+=n+"\n";
        }
        System.out.println("M for Menu.\n");
        return temp;
    }

    /*===============================================================================================
     *                      Diverse Methods
     * ==============================================================================================
     */
    
    /**
     * The Name Generator for Name, uses random to generate 1 values, based on the arraylist.
     * This value is then compered to the arraylist and a name is picked from it.
     * @return n - n is the name generated by random and index comparison.
     */
    public String NameGen(){//Shortened down from NameGenerator
        Random rand = new Random();
        int index = rand.nextInt((names.size() - 1));//Starts at 1 higher than arraylists 0, so -1
        return names.get(index);
    }

    /*===============================================================================================
     *                      Save and Load 
     * ==============================================================================================
     */
    
    /**
     * Save mechanics for the name arraylist.
     * Prints arraylist size, then name, 
     * then repeats this for all names.
     */
    public void save(String fileName){
        try{
            PrintWriter outfile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            outfile.println(names.size());
            for (String n:names) {
                outfile.println(n);
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
     * Load mechanics, reads in from file, arraylist size,
     * and uses that as a run number for a loop.
     * Read names in a loop, 
     * depending on number of times read.
     */
    public void load(String fileName){
        try{
            Scanner infile =new Scanner(new InputStreamReader(new FileInputStream(fileName)));
            int num=infile.nextInt();infile.nextLine();
            for (int i=0;i<num;i++) {
                String l=infile.nextLine();
                String n=new String(l);
                names.add(n);
            }
            Collections.sort(names);// Sorts the array list
            System.out.println(fileName+" names successfully loaded.");
            infile.close();
        }
        catch(IOException e) {
            System.out.println("cant find file: "+fileName);
        }
        catch (InputMismatchException e) {
             System.out.println("cannot add - weird input");
        }
        catch(Exception e) {
            System.out.println("cant find file: "+fileName);
        }
    }
}