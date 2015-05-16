package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeCell;
import maze.core.MazeExplorer;

public class PlussTreasure implements BestFirstHeuristic<MazeExplorer> {

	/**
	 * by me.
	 */
	@Override
	public int getHeuristic(MazeExplorer node, MazeExplorer goal) {
		MazeCell goalloc, nodeloc;
		nodeloc = node.getLocation();
		goalloc = goal.getLocation();
		//int f = nodeloc.getManhattanDistOver(goalloc) *5;
		int f = nodeloc.getManhattanDist(goalloc);
		int t = 50 * node.numTreasures();
		//System.out.println("f ="+f);
		//System.out.println("Treasures="+node.numTreasures());
		//f =+ node.numTreasures();
		f =+ t;
		//f =- 5 * node.numTreasures();
		//System.out.println("combined ="+f);
		
		return f;
	}

	@Override
	public Boolean useDepth() {
		return true;
	}

}
