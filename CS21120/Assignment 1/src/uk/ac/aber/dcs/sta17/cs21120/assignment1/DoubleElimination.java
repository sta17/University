package uk.ac.aber.dcs.sta17.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class DoubleElimination implements IManager {
	ArrayList<String> winnerQueue;	// starting queue and winner queue.
	ArrayList<String> loserQueue;	// the queue the losers are put in.
	ArrayList<Match> winnerMatches;	// Winner matches.
	ArrayList<Match> loserMatches;	// loser matches
	Match currentMatch;			    // this is the current match.
	Boolean whichMatch;   	// is true for winner queue and false for loser queue.
	int finalMatch;		// if 1 then the final match has not been made, 
						// if 2 then it has been made but not played,
						// if 3 it has been played.

	/**
	 * Creates the class, sets the basic variables, but not all, some are lacking due to lacking players.
	 */
	public DoubleElimination(){
		winnerMatches = new ArrayList<Match>();
		loserMatches = new ArrayList<Match>();
		loserQueue = new ArrayList<String>();
		currentMatch = null;
		whichMatch = true;
	}
	
	/**
	 *  sets the players and make matches, if there are less than 3 players, then it sets that there will only be 1 match,
	 *  because, with only 2 players, then there will only be 1 match, unless, they are going to play against each other twice.
	 *  if there is only 1 player provided, then it will set the matches as done, because, there will be no match to play.
	 */
	@Override
	public void setPlayers(ArrayList<String> players) {
		winnerQueue = players;
		if(winnerQueue.size() == 1){
			finalMatch = 3;
		}else if(winnerQueue.size() == 2){
			finalMatch = 2;
		} else {
			finalMatch = 1;
		}
		makeMatch();
	}

	/**
	 * checks finalMatch to see if the matches are done, if not, it checks to see that both 
	 * winnerMatches and loserMatches has any match.
	 */
	@Override
	public boolean hasNextMatch() {
		boolean match = true;
		if ((finalMatch == 3 || (winnerMatches.size() == 0) && (loserMatches.size() == 0))) {
			match = false;
		}
		return match;
	}

	/**
	 * what it does first is to check if there already is a match in play, 
	 * if there is this is added to the back of its match queue.
	 * Then it checks the Match Queues, for the biggest and provides that match.
	 */
	@Override
	public Match nextMatch() throws NoNextMatchException {
		if(currentMatch != null){
			if(whichMatch == true){
			winnerMatches.add(currentMatch);
			currentMatch = null;
			} else {
			loserMatches.add(currentMatch);
			currentMatch = null;
			}
		}
		
		if((winnerMatches.size() != 0) || (loserMatches.size() != 0)){
			if(winnerMatches.size() >= loserMatches.size()){
				currentMatch = winnerMatches.get(0);
				winnerMatches.remove(0);
				whichMatch = true;
			}else{// if not a winner match, provide a loser match.
				currentMatch = loserMatches.get(0);
				loserMatches.remove(0);
				whichMatch = false;
			}
		}else{// gets thrown if there actually are no matches to provide.
			throw new NoNextMatchException("There are no more matches");
		}
		return currentMatch;
	}

	/**
	 * Does as ordered and depending on who won, the players get set, 
	 * then depending on if it was a winner or loser match, the players get sorted out.
	 * It then checks to see if this match was the final match, if it was, set to next stage.
	 */
	@Override
	public void setMatchWinner(boolean player1) {
		if(currentMatch != null){
			String winner;
			String loser;
			if (player1 == true) {
				winner = currentMatch.getPlayer1();
				loser = currentMatch.getPlayer2();
			} else {
				winner = currentMatch.getPlayer2();
				loser = currentMatch.getPlayer1();
			}
		
			if(whichMatch == true){
				winnerQueue.add(winner);
				loserQueue.add(loser);
			}else{
				loserQueue.add(winner);
			}
			
			// sorts the players and queues out.
			if(finalMatch == 2){
				finalMatch = 3;
			} else if((winnerQueue.size() + loserQueue.size()) == 2 && (winnerMatches.size() +loserMatches.size()) == 0){
				String firstplayer = winnerQueue.get(0);
				winnerQueue.remove(0);
				String secondplayer = loserQueue.get(0);
				loserQueue.remove(0);
				Match finalmatch = new Match(firstplayer, secondplayer);
				winnerMatches.add(finalmatch);
				finalMatch = 2;
			} else if((winnerQueue.size() + loserQueue.size()) == 1 && (winnerMatches.size() +loserMatches.size()) == 0){
				finalMatch = 3; // if there is for some reason only 1 player at this point, 
								// then final match cannot be played, so skip and set game to done.
			}
			makeMatch();
			currentMatch = null;
		}
	}
	
	/**
	 * finalMatch is used to check if game is over, otherwise, it is straight forward.
	 */
	@Override
	public String getPosition(int n) {
		String position = "";
		if(finalMatch != 3){
			position = null;
		} else {
			if(n == 0){
				position = winnerQueue.get(0);
			}else{
				position = loserQueue.get(0);
			}
		}
		return position;
	}
	
	/**
	 * makes matches for the match queues depending on the player queues.
	 */
	private void makeMatch() {
		while(winnerQueue.size() > 1){
			for (int i = 0; i < (winnerQueue.size() / 2); i++) {
				String player1 = winnerQueue.get(0);
				winnerQueue.remove(0);
				String player2 = winnerQueue.get(0);
				winnerQueue.remove(0);
				Match winnermatch = new Match(player1, player2);
				winnerMatches.add(winnermatch);
			}
		} while(loserQueue.size() > 1){
			for (int i = 0; i < (loserQueue.size() / 2); i++) {
				String player1 = loserQueue.get(0);
				loserQueue.remove(0);
				String player2 = loserQueue.get(0);
				loserQueue.remove(0);
				Match losermatch = new Match(player1, player2);
				loserMatches.add(losermatch);
			}
		}
	}
}