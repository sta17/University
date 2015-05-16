package maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import maze.ai.core.BestFirstObject;

//represents an explorer collecting treasure
public class MazeExplorer implements BestFirstObject<MazeExplorer> {
	private Maze m; //the maze
	private MazeCell location; //the current location/state
	private TreeSet<MazeCell> treasureFound; //list of found treasure
	
	public MazeExplorer(Maze m, MazeCell location) {
		this.m = m;
		this.location = location;
		treasureFound = new TreeSet<MazeCell>();
	}
	
	public MazeCell getLocation() {return location;}

	//This method generates and returns the child states
	public ArrayList<MazeExplorer> getSuccessors() {
		ArrayList<MazeExplorer> result = new ArrayList<MazeExplorer>();
		ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>(4);
		neighbors = m.getNeighbors(location);
		
		//if the current location contains treasure then add it to the list of treasures found
		if (m.isTreasure(location)) {
			treasureFound.add(location);
			}
		
		// TODO: It should add as a successor every adjacent, unblocked neighbour square. 
		// Also it needs to make sure that the current list of found treasure is present in each child state
		
		//System.out.println(neighbors.size());
		
		for(int i = 0;i < neighbors.size(); i++){//My Work, shared with Dan Fryer as we coorporated.
			//System.out.println(i);
			MazeCell cell = neighbors.get(i);
			
					if(!m.blocked(location,cell)){
						MazeExplorer tempExplorer;
						tempExplorer = new MazeExplorer(m, cell);
						tempExplorer.addTreasures(treasureFound);
						result.add(tempExplorer);
					}
		}
		
        return result;
	}
	
	//number of treasures found so far
	public int numTreasures() {
		return treasureFound.size();
	}
	
	//add the collection of treasures to the current set of treasures
	public void addTreasures(Collection<MazeCell> treasures) {
		treasureFound.addAll(treasures);
	}
	
	public String toString() {
		StringBuilder treasures = new StringBuilder();
		for (MazeCell t: treasureFound) {
			treasures.append(";");
			treasures.append(t.toString());
		}
		return "@" + location.toString() + treasures.toString();
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof MazeExplorer) {
			return achieves((MazeExplorer)other);
		} else {
			return false;
		}
	}

	//The search can stop when this condition is met - i.e. we've reached the goal state having picked up all treasures in the maze
	public boolean achieves(MazeExplorer goal) {
		return this.location.equals(goal.location) && this.treasureFound.equals(goal.treasureFound);
	}

}
