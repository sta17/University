package uk.ac.aber.dcs.sta17.cs21120.assignment1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class SingleEliminationTest{
private SingleElimination current;
@SuppressWarnings("rawtypes")
private ArrayList list;
	
	@SuppressWarnings("rawtypes")
	@Before
	public void setup(){
		current = new SingleElimination();
		list = new ArrayList();
	}
	
	@SuppressWarnings("unchecked")
	private void setup2(boolean set){
		String input = "one";
		list.add(input);
		if(set == true){
			current.setPlayers(list);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getPositionInput0Test() {
		String input = "one";
		list.add(input);
		current.setPlayers(list);
		
		String value2 = current.getPosition(0);
		assertEquals("Input Miss Match",input,value2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getPositionInput1Test() {
		setup2(false);
		String secondinput = "two";
		list.add(secondinput);
		current.setPlayers(list);
		
		String nuller = current.getPosition(1);
		assertNull(nuller);
	}
	
	@Test
	public void hasNextMatchMatches0Test() {
		setup2(true);
		assertFalse("More than 0 matches", current.hasNextMatch());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void hasNextMatchMatches1Test() {
		setup2(false);
		list.add("two");
		current.setPlayers(list);
		assertTrue(current.hasNextMatch());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void nextMatchRemovalandRightMatchTest() {
		setup2(false);
		list.add("two");
		current.setPlayers(list);
		@SuppressWarnings("unused")
		Match temp = current.nextMatch();
		assertFalse("Match not removed", current.hasNextMatch());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void nextMatchMatchbackinpileagainTest() {
		setup2(false);
		list.add("two");
		list.add("three");
		list.add("four");
		current.setPlayers(list);
		@SuppressWarnings("unused")
		Match match1 = current.nextMatch();
		Match match2 = current.nextMatch();
		Match match3 = current.nextMatch();
		assertEquals(match1,match3);
	}
	
	@Test(expected=NoNextMatchException.class)
	public void nextMatchExceptionThrownTest() {
		setup2(true);
		@SuppressWarnings("unused")
		Match temp = current.nextMatch();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void setMatchWinnerWinnerisaddedtoQueueTest() {		
		String testvalue = "one";
		setup2(false);
		list.add("two");
		current.setPlayers(list);
		@SuppressWarnings("unused")
		Match temp = current.nextMatch();
		current.setMatchWinner(true);
		assertEquals("Input Miss Match",testvalue,current.getPosition(0));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void setMatchWinnerMakesNewMatchTest() {
		setup2(false);
		list.add("two");
		list.add("three");
		current.setPlayers(list);
		@SuppressWarnings("unused")
		Match temp = current.nextMatch();
		current.setMatchWinner(true);
		assertTrue(current.hasNextMatch());
	}
}