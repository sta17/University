package uk.ac.aber.dcs.sta17.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class SingleElimination implements IManager {
	ArrayList<String> playersQueue;
	ArrayList<Match> matches;
	Match currentMatch;

	/**
	 * Creates the class, sets the basic variables, but not all, some are lacking due to lacking players.
	 */
	public SingleElimination(){
		currentMatch = null;
		matches = new ArrayList<Match>();
	}
	
	/**
	 *  sets the players and make matches,
	 */
	@Override
	public void setPlayers(ArrayList<String> players) {
		playersQueue = players;
		makeMatch();
	}

	/**
	 * checks for matches, if there are none, set to false, otherwise true.
	 */
	@Override
	public boolean hasNextMatch() {
		boolean match = true;
		if (matches.size() == 0) {
			match = false;
		}
		return match;
	}

	/**
	 * what it does first is to check if there already is a match in play, 
	 * if there is this is added to the back of its match queue.
	 * Then it checks the Match Queue for matches, get the first one and returns it.
	 */
	@Override
	public Match nextMatch() throws NoNextMatchException {
		if(currentMatch != null){
			matches.add(currentMatch);
			currentMatch = null;
		}
		if(matches.size() != 0){
			currentMatch = matches.get(0);
			matches.remove(0);
		}else{
			throw new NoNextMatchException("There are no more matches");
		}
		return currentMatch;
	}

	/**
	 * Does as ordered and depending on who won, the players get set, 
	 * the winner gets added to player queue, and the match gets erased.
	 */
	@Override
	public void setMatchWinner(boolean player1) {
		if(currentMatch != null){
			String winner;
			if (player1 == true) {
				winner = currentMatch.getPlayer1();
			} else {
				winner = currentMatch.getPlayer2();
			}
			playersQueue.add(winner);
			makeMatch();
			currentMatch = null;
		}
	}

	/**
	 * match.size is used to check if game is over, otherwise, it is straight forward.
	 */
	@Override
	public String getPosition(int n) {
		String position = null;
		if(matches.size() > 0 ){
			position = null;
		} else {
			if(n == 0){
				position = playersQueue.get(0);
				playersQueue.remove(0);
			}else{
				position = "NO RUNNER UP";
				// text instead of null, is done to prevent null pointers, 
				// which the factories and your code was not designed to prevent.
			}
		}
		return position;
	}
	
	/**
	 * makes matches, takes 2 players, add to match, add match, etc.
	 */
	private void makeMatch() {
		while(playersQueue.size() > 1){
			for (int i = 0; i < (playersQueue.size() / 2); i++) {
				String player1 = playersQueue.get(0);
				playersQueue.remove(0);
				String player2 = playersQueue.get(0);
				playersQueue.remove(0);
				Match match = new Match(player1, player2);
				matches.add(match);
			}
		}	
	}
}
