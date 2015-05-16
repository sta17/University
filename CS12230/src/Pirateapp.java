
/**
 * Main, The startup location.
 * 
 * @author Sta17
 * @version 6.11.2013
 */
public class Pirateapp{
    /**
     * Sets up everything, loads and saves, before exit.
     */
    public static void main(String args[]){
        MainContent app=new MainContent();
        app.initialise();
        app.runTheMenu();
        app.save();
    }
}