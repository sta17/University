package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeCell;
import maze.core.MazeExplorer;

public class ManhattenDistance implements BestFirstHeuristic<MazeExplorer> {
	
	/**
	 * Class created by me, solution shared with Dan Fryer as part of shared problem solving.
	 */
	public int getHeuristic(MazeExplorer node, MazeExplorer goal) {
		
		MazeCell goalloc, nodeloc;
		
		nodeloc = node.getLocation();
		goalloc = goal.getLocation();
		
		int f = nodeloc.getManhattanDist(goalloc);
		
		return f;
	}

	@Override
	public Boolean useDepth() {
		return true;
	}
}