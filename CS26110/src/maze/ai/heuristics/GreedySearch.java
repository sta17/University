package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeCell;
import maze.core.MazeExplorer;

public class GreedySearch implements BestFirstHeuristic<MazeExplorer> {

	/**
	 * Id and principle sort of provided Dan, e.g. Greedy search don't use Depth, implementation by me.
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
		return false;
	}
	
}
