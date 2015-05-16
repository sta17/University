/**
 * 
 */
package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

/**
 * Main, The startup location.
 * 
 * @author Sta17
 * @version 6.11.2013
 */
public class PirateApp {

	/**
	 * Sets up everything, loads and saves, before exit.
	 */
	public static void main(String args[]) {
		
		String fileName = "piratewords.txt";
		WordList list = new WordList();
		try {
			list.load(fileName);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File: piratewords.txt not found.", "File removed", 1);
		}catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null,"Loading failed, see error: "+e, "Loading error", 1);
		}
		
		if (args!=null && args.length>0) {// simply checks if the array actually contains
							// anything at all
				if (args[0].equals("-TXT")) {// is there txt, caseinsensitive

					TextVer app = new TextVer(list);
					app.runTheMenu();

				} else if (args[0].equals("-GUI")) {// is there gui,
															// case insensitive
					@SuppressWarnings("unused")
					GuiVer gui = new GuiVer(list,true);
				}
		}
		
		else {// Normal default version starts up here.
			@SuppressWarnings("unused")
			ChoiceMenu app = new ChoiceMenu(list);
		}
	}
}