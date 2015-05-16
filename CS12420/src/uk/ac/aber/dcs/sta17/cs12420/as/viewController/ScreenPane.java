package uk.ac.aber.dcs.sta17.cs12420.as.viewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import uk.ac.aber.dcs.sta17.cs12420.as.model.GameModel;

/**
 * This is the class that actually displays the animation puts a picture of aber
 * in background and runs 3 faces over the top
 * 
 * @author Steven
 * @version 18.04.2014
 * 
 */
@SuppressWarnings("serial")
public class ScreenPane extends JPanel implements Observer {

	private GameModel game;
	private MediaTracker mTracker;

	private Image backgroundImage; // needed for background to be loaded
	private int backgroundImageID; // just 0 for this here

	private Image seaImage; // needed for background to be loaded
	private int seaImageID; // just 3 for this here

	private Image splashImage; // needed for background to be loaded
	private int splashImageID; // just 3 for this here

	private Image pirateImage[] = new Image[6]; // needed for the 10 images
	private int pirateImageID[] = new int[6]; // filled below
	private int xpirate = 245;
	private int walkingcycles = 0;
	private int pileupcycles = 0;

	private Image sharkImage[] = new Image[5]; // needed for the 10 images
	private int sharkImageID[] = new int[5]; // filled below
	private int xshark = 0, yshark = 0, sharkState = 0;

	private final int B_WIDTH = 522;
	private final int INITIAL_sharkX = -100;
	private final int INITIAL_sharkY = 300;
	private final int DELAY = 25;

	private SharkThread shark;
	private PirateThread pirate;
	private int currentpirate = 0; // which image currently needed in animation
	private int currentshark = 0; // which image currently needed in animation
	private boolean isMoving;
	private boolean sharkTime;
	private boolean gameOver;

	/**
	 * sets up Screen. Constructor.
	 * 
	 * @param game
	 *            the game that is supposed to be played.
	 */
	public ScreenPane(GameModel game) {
		isMoving = false;
		sharkTime = false;
		gameOver = false;

		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		setMinimumSize(new Dimension(522, 399));
		setBorder(BorderFactory.createLineBorder(Color.black, 6));

		mTracker = new MediaTracker(this);
		// set up background

		// ---------------------------------------------
		// Set up the background Image
		// --------------------------------------------
		backgroundImageID = 0;
		String imgName = "/img/ship.png";
		URL imageURL = getClass().getResource(imgName);
		backgroundImage = Toolkit.getDefaultToolkit().getImage(imageURL);

		mTracker.addImage(backgroundImage, backgroundImageID);
		try {
			mTracker.waitForID(backgroundImageID);
		} catch (InterruptedException e) {
			System.out.println("Error loading background");
		}

		// ---------------------------------------------
		// Set up the front sea image
		// --------------------------------------------
		seaImageID = 3;
		imgName = "/img/sea.png";
		imageURL = getClass().getResource(imgName);
		seaImage = Toolkit.getDefaultToolkit().getImage(imageURL);

		mTracker.addImage(seaImage, seaImageID);
		try {
			mTracker.waitForID(seaImageID);
		} catch (InterruptedException e) {
			System.out.println("Error loading sea image");
		}

		// ---------------------------------------------
		// Set up the front sea image
		// --------------------------------------------
		splashImageID = 4;
		imgName = "/img/splash.png";
		imageURL = getClass().getResource(imgName);
		splashImage = Toolkit.getDefaultToolkit().getImage(imageURL);

		mTracker.addImage(splashImage, splashImageID);
		try {
			mTracker.waitForID(splashImageID);
		} catch (InterruptedException e) {
			System.out.println("Error loading sea image");
		}

		// ---------------------------------------------
		// Set up the 5 pirate images
		// --------------------------------------------
		for (int i = 0; i < 6; i++) {
			pirateImageID[i] = 4;
			imgName = "/img/pirate0" + i + ".png";
			imageURL = getClass().getResource(imgName);
			pirateImage[i] = Toolkit.getDefaultToolkit().getImage(imageURL); // d00, d01, d02, d03
			mTracker.addImage(pirateImage[i], pirateImageID[i]);
			try {
				mTracker.waitForID(pirateImageID[i]);
			} catch (InterruptedException e) {
				System.out.println("Error loading pirate image" + i);
			}
		}

		// ---------------------------------------------
		// Set up the 5 shark images
		// --------------------------------------------
		for (int i = 0; i < 5; i++) {
			sharkImageID[i] = 2;
			imgName = "/img/shark0" + i + ".png";
			imageURL = getClass().getResource(imgName);
			sharkImage[i] = Toolkit.getDefaultToolkit().getImage(imageURL); // shark00, shark01, shark02,
												// shark03, shark 04
			mTracker.addImage(sharkImage[i], sharkImageID[i]);
			try {
				mTracker.waitForID(sharkImageID[i]);
			} catch (InterruptedException e) {
				System.out.println("Error loading shark image" + i);
			}
		}
		xshark = INITIAL_sharkX;
		yshark = INITIAL_sharkY;

		this.game = game;
		game.addObserver(this);

		currentpirate = 0;
		currentshark = 0;

		repaint();

		shark = new SharkThread();
		shark.setName("shark");
		shark.start();
	}

	@Override
	/**
	 * This is part of the Observable/observer pattern. Takes in values and based on that, triggers the right animations.
	 * 
	 * @param target - the object to be observed
	 * @param value - the value sent to method. 
	 * 
	 */
	public void update(Observable target, Object value) {
		if (value.equals(0)) {
			if (isMoving == false) {
				isMoving = true;
				walkingcycles = game.preSetGuessLeft() * 6;
				startThread();
			} else {
				pileupcycles += game.preSetGuessLeft() * 6;
			}
		} else if (value.equals(10)) {
			newGame();
			repaint();
		} else if (value.equals(1)) {
			if (isMoving == false) {
				walkingcycles = 6;
				isMoving = true;
				startThread();
			} else {
				pileupcycles += 6;
			}
		} else if (value.equals(5)) {
			if (isMoving == false) {
				walkingcycles = 30;
				isMoving = true;
				startThread();
			} else {
				pileupcycles += 30;
			}
		}
	}

	/**
	 * draw the screen and its image components
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(pirateImage[currentpirate], xpirate, 190, this);
		if (gameOver == true) {
			g.drawString("Game Over", 200, 190);
		}
		g.drawImage(sharkImage[currentshark], xshark, yshark, this);
		g.drawImage(seaImage, 0, 0, this);
		if (sharkState == 2 && sharkTime == true) {
			g.drawImage(splashImage, 0, 220, this);
		}
	}

	public void closeThreads() {

		if (!shark.isInterrupted()) {
			shark.interrupt();
		}
	}

	/*
	 * ================================= Private Methods here
	 * =================================
	 */

	/**
	 * basically resets the stats for the game.
	 */
	private void newGame() {
		pileupcycles = 0;
		walkingcycles = 0;
		isMoving = false;
		gameOver = false;
		sharkTime = false;
		xshark = INITIAL_sharkX;
		yshark = INITIAL_sharkY;
		xpirate = 245;
		currentpirate = 0;
		currentshark = 0;
		sharkState = 0;
	}

	/**
	 * called when button pressed to start the animation
	 */
	private void startThread() {
		currentpirate = 0;

		pirate = new PirateThread();
		pirate.setName("pirate");
		pirate.start();

	}

	/**
	 * Handles the sharks movements, normally if there is no sharkTime moments,
	 * then it simply moves shark forward. otherwise, it does special animation
	 * before turning of special sharkTime event.
	 */
	class SharkThread extends Thread {

		@Override
		public void run() {
			try {
				// currentshark = 0;
				while (!Thread.currentThread().isInterrupted()) {
					long beforeTime, timeDiff, sleep;

					beforeTime = System.currentTimeMillis();

					if (sharkTime == true && xshark == INITIAL_sharkX) {
						sharkState = 1;
					}

					if (sharkState == 1 || sharkState == 2 || sharkState == 3) {
						if (xshark == -50) {// jump up and grab guy
							sharkState = 2;
							currentshark = 2;
						} else if (sharkState == 2 && yshark == 205) {// down
																		// again
																		// with
																		// x
																		// and y
							currentpirate = 5;
							currentshark = 3;
							sharkState = 3;
						} else if (sharkState == 3 && yshark == 300) {// continue
																		// forward
																		// with
																		// guy
																		// in
																		// mouth
							sharkState = 4;
							currentshark = 4;
						}

						if (xshark > B_WIDTH) {
							xshark = INITIAL_sharkX;
							if (sharkState == 4) {
								sharkTime = false;
							}
						} else if (sharkState == 2) {
							xshark += 1;
							yshark -= 1;
						} else if (sharkState == 3) {
							xshark += 1;
							yshark += 1;
						} else {// move forward until end of screen
							xshark += 1;
						}
					} else {// move forward until end of screen
						xshark += 1;
						if (xshark > B_WIDTH) {
							xshark = INITIAL_sharkX;
							if (sharkState == 4) {
								sharkTime = false;
							}
						}
					}

					repaint();

					timeDiff = System.currentTimeMillis() - beforeTime;
					sleep = DELAY - timeDiff;

					if (sleep < 0) {
						sleep = 2;
					}

					Thread.sleep(sleep);

					beforeTime = System.currentTimeMillis();
				}

			} catch (InterruptedException e) {
			}

		}
	}

	/**
	 * do the animation - here we are doing 4 images d00, d01, d02, d03 and then
	 * repeating x amount of times obviously adjust what you animate to what you
	 * want
	 */
	class PirateThread extends Thread {

		@Override
		public void run() {
			while (isMoving == true && !Thread.currentThread().isInterrupted()) {
				for (int j = 0; j < walkingcycles; j++) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						break;
					}
					xpirate -= 2;
					if (walkingcycles == 0) {
						xpirate = 245;
						currentpirate = 0;
					} else {
						currentpirate = (currentpirate + 1) % 4;
					}
					repaint(); // calls paintComponent()
				}
				if (pileupcycles != 0) {
					walkingcycles = pileupcycles;
					pileupcycles = 0;
				} else {
					if (xpirate == 125 || xpirate == 127) {
						gameOver = true;
						sharkTime = true;
						currentshark = 1;
					}
					walkingcycles = 0;
					isMoving = false;
					currentpirate = 0;
					repaint();
					Thread.currentThread().interrupt();

				}
			}
		}

	}

}
