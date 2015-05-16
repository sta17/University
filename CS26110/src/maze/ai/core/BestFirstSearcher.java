package maze.ai.core;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class BestFirstSearcher<T extends BestFirstObject<T>>{
    private ArrayList<T> solution;
    private int depth, numNodes; //keeps track of the depth and number of nodes examined in the search
    private BestFirstHeuristic<T> h; //the heuristic to be used
    private boolean solutionFound;
    
    private final static boolean debug = false;
    
    //constructor
    public BestFirstSearcher(BestFirstHeuristic<T> bfh) {
        h = bfh; 
        reset();
    }
    
    //This performs the main best-first search
    // start is the start node, target is the target node
    public void solve(T start, T target) {
        reset();
        
        Set<T> visited = new HashSet<T>();
        SearchNode best = new SearchNode(null, start, target, h);
        PriorityQueue<SearchNode> openList = new PriorityQueue<SearchNode>();
        openList.add(best);
        
        solutionFound = false;
        while (openList.size() > 0 && !solutionFound) {
            best = openList.poll();
            if (debug) {System.out.println("best: " + best.getObject());}
            if (!visited.contains(best.getObject())) {
                visited.add(best.getObject());
                if (best.getObject().achieves(target)) {
                    solutionFound = true;
                } else {
                	addSuccessors(best, openList, target);
                }
            }
        }
        
        if (solutionFound) {
            reconstructMoves(best);
        }
    }
  
    //you will need to modify this (only the depth is recorded here at the moment)
    private void addSuccessors(SearchNode best, PriorityQueue<SearchNode> openList, T target) {
        for (T p: best.getObject().getSuccessors()) {
            numNodes++;
            SearchNode newNode = new SearchNode(best, p, target, h);
            this.depth = Math.max(newNode.getDepth(), depth);
  
            openList.add(newNode);
        }    	
    }
    
    private void reconstructMoves(SearchNode searcher) { 
        while (searcher != null) {
            solution.add(searcher.getObject());
            searcher = searcher.getParent();
        }
        
        for (int i = 0; i < solution.size() / 2; ++i) {
            T temp = solution.get(i);
            int other = solution.size() - i - 1;
            solution.set(i, solution.get(other));
            solution.set(other, temp);
        }
    }
    
    //you will need to modify this
    private class SearchNode implements Comparable{
        private int depth;
        private T node;
        private T goal;
        private SearchNode parent;
        private BestFirstHeuristic<T> h;
        
        public SearchNode(SearchNode parent, T pNode, T goal, BestFirstHeuristic<T> bfh) {
            node = pNode;
            this.parent = parent;
            depth = (parent == null) ? 0 : parent.depth + 1;
            h = bfh;
            this.goal = goal;
        }
        
        public int getDepth() {return depth;}
        
        public T getObject() {return node;}
        
        public SearchNode getParent() {return parent;}

        /**
         * Most of the stuff is by me, the green bits/commented out is where i have experimented,
         *  like always, this is done in corporation with Dan Fryer.
         */
		public int compareTo(Object arg0) {
			/*
			System.out.println("search node comparable working test");
			System.out.println("node");
			System.out.println(node.toString());
			System.out.println(node.getClass().getName());
			System.out.println("goal");
			System.out.println(goal.toString());
			System.out.println(goal.getClass().getName());
			
			MazeExplorer g = (MazeExplorer) goal;
			MazeExplorer n = (MazeExplorer) node;
			MazeCell goalloc, nodeloc;
			nodeloc = n.getLocation();
			goalloc = g.getLocation();
			int f = depth + nodeloc.getManhattanDist(goalloc);
			*/
			//Coorporated with Dan Fryer here.
			SearchNode sNode = (SearchNode) arg0;
			int f1, f2;
			
			if(h.useDepth()==false){
				f1 = h.getHeuristic(node, goal);
				f2 = sNode.h.getHeuristic(node, goal);
			}else{
				f1 = depth + h.getHeuristic(node, goal);
	        	f2 = sNode.depth + sNode.h.getHeuristic(node, goal);
			}

        		if (f1 < f2) {
        			return -1;
        		} else if (f1 > f2) {
        			return 1;
        		} else {
        			return 0;
        		}
        	}
        
    }
    
    private void reset() {
        solution = new ArrayList<T>();
        depth = -1;
        numNodes = 0;
        solutionFound = false;
    }
    
    // Pre: 0 <= n <= getMaxStep(); success()
    public T get(int n) {
        return solution.get(n);
    }
    
    public boolean success() {return solutionFound;}

    // Pre: success()
    public int numSteps() {return solution.size();}
    
    // Pre: success()
    public int getMaxDepth() {return depth;}
    
    // Pre: success()
    public int getNumNodes() {return numNodes;}
    
    // Pre: success()
    public double getBranchingFactor(double maxError) {
        double lo = 0;
        double hi = (double)numNodes / (double)depth;
        double error = 0;
        double bGuess = 0;
        do {
            bGuess = (lo + hi) / 2;
            error = computeError(bGuess);
            if (error > 0) {
                hi = bGuess;
            } else {
                lo = bGuess;
            }
        } while (Math.abs(error) > maxError);
        return bGuess;
    }
    
    private double computeError(double bGuess) {
        double sum = 0;
        for (int d = 1; d <= depth; ++d) {
            sum += Math.pow(bGuess, d);
        }
        return sum - numNodes;
    }
}