import java.util. *;
import cs122graphics.*;
/**
 * This the Class that handles the menu, sets up the  save, load for individual files and ArrayLists.
 * 
 * @author Sta17
 * @version 30.11.2013
 */
public class MainContent
{
    // instance variables - replace the example below with your own
    private Scanner input;
    private Dictonary wordlist;
    private Name firstnames;
    private Name lastnames;

    /**
     * Constructor, creates new instance of class MainContent, declaring Arraylists and scanner.
     */
    public MainContent(){
        input=new Scanner(System.in);
        wordlist=new Dictonary();
        firstnames = new Name();
        lastnames = new Name();
    }

    /*===============================================================================================
     *                      Diverse Methods
     * ==============================================================================================
     */
    
    /**
     * This is an overview of the menu for options in the method runTheMenu() right below. Which tells the user what to write in to do what. Its a print method.
     * The line is splitt into several to provide clearity and overview.
     */
    public void printMenu() {
        System.out.println("Menu input is case insensitive. \n"+
            "What would you like to do: \n " +
            "1 - add phrace \n "+
            "2 - add a pirate name \n "+
            "3 - translate to Pirate \n "+
            "4 - translate to English \n "+
            "5 - random phrace generation \n "+
            "6 - random pirate name generator \n "+
            "7 - print Pirate Name List \n "+
            "8 - print Pirate Dictonary \n "+
            "9 - remove a word \n"+
            "10 - remove a name \n "+
            "M - print menu \n "+
            "Q - quit \n");
    }
    
    /**
     * runTheMenu() method runs from the main and allows entry of data. Data is handeled differently depending on option.
     */
    public void runTheMenu(){
        //menu input
        String response;
        printMenu();
        do {
            response=input.nextLine();
            //The add a phrace to the name dictonary
            if (response.equals("1")){
                System.out.println("Enter English version: ");
                String english=input.nextLine();
                System.out.println("Enter Pirate version: ");
                String pirate=input.nextLine();
                wordlist.addWord(english,pirate);
                System.out.println("M for menu.");
            }

            //add name to pirate list
            else if (response.equals("2")) {                
                System.out.println("Do you wish to enter a firstname or lastname?(firstname/lastname): ");
                String answer=input.nextLine();
                if(answer.equalsIgnoreCase("firstname")){
                    System.out.println("Enter a pirate firstname: ");
                    String first=input.nextLine();
                    firstnames.addPirateName(first);
                    System.out.println("M for menu.");
                }
                else if(answer.equalsIgnoreCase("lastname")){
                    System.out.println("Enter a pirate lastname : ");
                    String last=input.nextLine();
                    lastnames.addPirateName(last);
                    System.out.println("M for menu.");
                }
                else{
                    System.out.println("Please enter firstname or lastname for name adding.");
                    System.out.println("M for menu.");
                }
            }

            //The Mystery phrace
            else if (response.equalsIgnoreCase("The Mystery, Flag and Pirates")) {
                Image();
                System.out.println("M for Menu.\n");
            }

            //Translate to Pirate
            else if (response.equals("3")) {//split lines for clearness
                System.out.println("Instructions:\n Enter up to 35 lines for translation."+
                    "\n Single line with Capital 'Q' if you wish to translate it."+
                    "\n Enter English text to translate: \n");
                String piratetran = "";
                int counter = 0;
                while(counter<35){//limited to 35 due to visability
                    String in = input.nextLine();
                    counter++;
                    if(in.equals("Q")){
                        break;
                    }
                    else{
                        piratetran+=" \n"+in;
                    }
                }
                System.out.println("\n Pirate text:\n"+wordlist.TranslatetoPir(piratetran)+"\n Translation done. \n M for Menu.\n");
            }

            //Translate to English
            else if (response.equals("4")) {//split lines for clearness
                System.out.println("Instructions:\n Enter up to 35 lines for translation."+
                    "\n Single line with Capital 'Q' if wish to quit."+
                    "\n Enter English text to translate: \n");
                String englishtran = "";
                int counter = 0;
                while(counter<35){//limited to 35 due to visability
                    String in = input.nextLine();
                    counter++;
                    if(in.equals("Q")){
                        break;
                    }
                    else{
                        englishtran+=" \n"+in;
                    }
                }
                System.out.println("\n English text:\n"+wordlist.TranslatetoEng(englishtran)+"\n Translation done. \n M for Menu.\n");
            }

            //generate random phrace
            else if (response.equals("5")) {
                System.out.println(wordlist.phrGen()+".");
                System.out.println("M for Menu.\n");
            }

            //generate pirate name
            else if (response.equals("6")) {
                System.out.println("Your Pirate name is: " + firstnames.NameGen() + " " + lastnames.NameGen());
                System.out.println("M for Menu.\n");
            }

            //print the pirate list
            else if (response.equals("7")) {
                System.out.println("Do you wish to see list of firstname or lastname?(firstname/lastname): ");
                String answer=input.nextLine();
                if(answer.equalsIgnoreCase("firstname")){
                    System.out.println(firstnames.toString());
                    System.out.println("M for menu.");
                }
                else if(answer.equalsIgnoreCase("lastname")){
                    System.out.println(lastnames.toString());
                    System.out.println("M for menu.");
                }
                else{
                    System.out.println("Please enter firstname or lastname to see their lists.");
                    System.out.println("M for menu.");
                }
            }

            //print the dictonary out
            else if (response.equals("8")) {
                System.out.println(wordlist.toString());
                System.out.println("M for Menu.\n");
            }

            //deletes a word/phrace in the arraylists
            else if(response.equals("9")){
                System.out.println("Enter word, pirate or english: ");
                String remove=input.nextLine();
                wordlist.removeWord(remove);
                System.out.println("M for menu.");
            }

            //delete a an entry in the arraylists
            else if(response.equals("10")){
                System.out.println("Do you wish to remove a firstname or lastname?(firstname/lastname): ");
                String answer=input.nextLine();
                if(answer.equalsIgnoreCase("firstname")){
                    System.out.println("Enter a pirate firstname: ");
                    String remove=input.nextLine();
                    firstnames.removeName(remove);
                    System.out.println("M for menu.");
                }
                else if(answer.equalsIgnoreCase("lastname")){
                    System.out.println("Enter a pirate lastname : ");
                    String remove=input.nextLine();
                    lastnames.removeName(remove);
                    System.out.println("M for menu.");
                }
                else{
                    System.out.println("Please enter a firstname or lastname to navigate to the remove option of either.");
                    System.out.println("M for menu.");
                }
            }

            //prints menu
            else if (response.equalsIgnoreCase("M")) {//prints menu
                printMenu();
            }
        } while (! ( (response.equalsIgnoreCase("Q"))));//quits
    }

    /**
     * Brings up images. Code by Lynda.
     * Provided for Assignment.
     */
    public void Image() {
        System.out.println("hit return to move on");
        Scanner in=new Scanner(System.in);   //to slow down
        TheSimpleFrame frame=new TheSimpleFrame();
        in.nextLine();
        
        frame.display("flag.png");
        frame.setVisible(true);
        in.nextLine();
        
        frame.display("pirate.png");
    }   

    /*===============================================================================================
     *                      Save and Load 
     * ==============================================================================================
     */
    
    /**
     * save() method runs from the main and writes back to file
     * calls save(filename) in dictonary and nameGenerator
     */
    public void save(){
        System.out.println("Starting Saving.");
        wordlist.save("pirate.txt");
        firstnames.save("Firstnames.txt");
        lastnames.save("Lastnames.txt");
    }
    
    /**
     * initialise() method runs from the main and reads from pirate.txt and piratename.txt
     * calls load method in dictonary class and nameGenerator class.
     */
    public void initialise(){
        System.out.println("Statring Loading.");
        wordlist.load("pirate.txt");
        firstnames.load("Firstnames.txt");
        lastnames.load("Lastnames.txt");
    }
}