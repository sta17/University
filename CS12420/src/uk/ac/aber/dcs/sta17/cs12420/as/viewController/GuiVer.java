package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.ac.aber.dcs.sta17.cs12420.as.model.GameModel;
import uk.ac.aber.dcs.sta17.cs12420.as.model.WordList;

public class GuiVer implements WindowListener  {

	private JFrame frame;
	private InteractionPane interaction;
	private ScreenPane screen;
	private Boolean exitOnClose = false;
	private ListHandelingPane listpane;

	@SuppressWarnings("unused")
	private WordList list;
	private GameModel game;

	public GuiVer(WordList list) {
		this.list = list;
		game = new GameModel(list);

		frame = new JFrame();
		interaction = new InteractionPane(game,list);
		screen = new ScreenPane(game);

		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					buildWindow();
				}
			});
		} catch (Exception e) {
			System.out.println("invokeLater exception" + e);
		}
	}

	public GuiVer(WordList list, Boolean exitOnClose) {
		this.list = list;
		game = new GameModel(list);
		listpane = new ListHandelingPane(list);
		this.exitOnClose = exitOnClose;

		frame = new JFrame();
		interaction = new InteractionPane(game,list);
		screen = new ScreenPane(game);

		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					buildWindow();
				}
			});
		} catch (Exception e) {
			System.out.println("invokeLater exception" + e);
		}
	}

	private void buildWindow() {
		int xsize = 520;
		frame.setTitle("Hangman Pirate Edition");
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(this);
		
		if (exitOnClose == true) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(listpane, BorderLayout.SOUTH);
			xsize = 550;
		}

		// Create and pack the Elements

		frame.add(interaction, BorderLayout.NORTH);

		// set up for animation
		frame.add(screen, BorderLayout.CENTER);

		// Get the screen size
		GraphicsConfiguration gc = frame.getGraphicsConfiguration();
		Rectangle bounds = gc.getBounds();

		// Set the Location and Activate
		Dimension size = frame.getPreferredSize();
		frame.setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
				(int) ((bounds.height / 3) - (size.getHeight() / 2)));

		frame.pack();
		
		frame.setSize(538, xsize);
		frame.setVisible(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		screen.closeThreads();
	}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}

}
