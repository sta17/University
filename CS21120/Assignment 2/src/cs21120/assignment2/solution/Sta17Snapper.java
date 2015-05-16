package cs21120.assignment2.solution;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

import cs21120.assignment2.FloatImage;
import cs21120.assignment2.ISnapper;

/**
 * 
 * This is the class that uses Dijkstra's algorithm to find a path along the
 * edges in the provided FloatImage array.
 * 
 * Had big problems getting my head around the task and how to implement
 * Dijktra's algorithm, as on paper so was it easy enough, but i could not get
 * my head around it, so i visited Bernie Tiddeman and we went over it for 10
 * minuttes and then i went back to flat and coded it pretty much straight out,
 * spend the next 3-2 days up until now, debugging what turned out to be 3 small
 * bugs in the initial code, forgot to initialise the priority queue, swapped
 * edge start and end point at one point, and had some unsafe code somewhere,
 * after those 3 fixes, it worked perfectly.
 * 
 * 
 * @version Final
 * @author Steven Aanetsen (sta17)
 *
 */
public class Sta17Snapper implements ISnapper, Runnable {
	private FloatImage[] edges;
	private PriorityBlockingQueue<Edge> unvisited;
	private Point map[][];
	private boolean visited[][];
	private Point startpoint;

	/**
	 * This class implements the Runnable interface, so that it can be run like
	 * a thread, once a thread starts, this is the method that is run upon
	 * thread start. This method will generate a 2D array map of points, stored
	 * locally in class based on the provided start point.
	 * 
	 */
	@Override
	public void run() {
		// Sets up the initial points, to avoid having to deal with an empty
		// unvisited queue in the while loop.
		ArrayList<Edge> surroundingpoints = findConnectedPoints(new Edge(null,
				startpoint, 0));
		for (int i = 0; i < surroundingpoints.size(); i++) {
			unvisited.add(surroundingpoints.get(i));
		}
		visited[startpoint.x][startpoint.y] = true;
		map[startpoint.x][startpoint.y] = null;
		// starts the algorithm loop
		while (!unvisited.isEmpty()) {
			Edge e = unvisited.poll();
			// checks if the end of the edge class is visited or not, skip it if
			// it is.
			if (visited[e.end.x][e.end.y] != true) {
				visited[e.end.x][e.end.y] = true;
				map[e.end.x][e.end.y] = e.start;
				surroundingpoints = findConnectedPoints(e);
				for (int i = 0; i < surroundingpoints.size(); i++) {
					unvisited.add(surroundingpoints.get(i));
				}
			}
		}
	}

	/**
	 * This method generates a path based on the mapped image from method
	 * setSeed and based on provided x and y coordinates navigates back to start
	 * generating a path as it navigates.
	 * 
	 * @param x
	 *            - the x coordinates for the end point
	 * @param y
	 *            - the y coordinates for the end point
	 * @return path - is the LinkedList that contains the generated path.
	 */
	public LinkedList<Point> getPath(int x, int y) {
		LinkedList<Point> path = new LinkedList<Point>();
		Point p = new Point(x, y);
		while (p != null) {// navigating the mapped image
			path.add(p);
			Point p2 = map[p.x][p.y];
			p = p2;
		}
		return path;
	}

	/**
	 * 
	 * Sets up the class for generating the 2D array map of points, from which
	 * the path is found in, acts as constructor as it initalises most local
	 * class variables. Also starts the thread which makes the 2D array map of
	 * points.
	 * 
	 * @param x
	 *            - primitive int, x coordinates of start point.
	 * @param y
	 *            - primitive int, y coordinates of start point.
	 * @param edges
	 *            - FloatImage class, is an Array. this is the image to which
	 *            the class is supposed to work on.
	 * 
	 */
	public void setSeed(int x, int y, FloatImage[] edges) {
		startpoint = new Point(x, y);
		this.edges = edges;
		map = new Point[edges[0].getWidth()][edges[0].getHeight()];
		visited = new boolean[edges[0].getWidth()][edges[0].getHeight()];
		unvisited = new PriorityBlockingQueue<Edge>();
		Thread t1 = new Thread(this);
		t1.start();
	}

	/**
	 * Returns an arraylist of the surrounding edges to the currentedge
	 * parameters end point. It checks to see if the surrounding point is within
	 * the image and if it is not already visited, and generate an edge based on
	 * the legal surrounding Points. Its sums the old weight and adds weights up
	 * to the current point in the new edge that is generated.
	 * 
	 * @param currentedge
	 *            - Edge class, is a Edge to which its end points surrounding
	 *            legal points are found.
	 * @return tempedges - ArrayList of edges surrounding the current point.
	 */
	private ArrayList<Edge> findConnectedPoints(Edge currentedge) {
		int height = edges[0].getHeight();
		int width = edges[0].getWidth();
		ArrayList<Edge> tempedges = new ArrayList<Edge>();
		Edge tempedge = null;
		Point currentpoint = currentedge.end;

		if ((currentpoint.y) < height && (currentpoint.x + 1) < width
				&& (currentpoint.y) > 0 && (currentpoint.x + 1) > 0) {
			if (visited[currentpoint.x + 1][currentpoint.y] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x + 1,
						currentpoint.y), edges[0].get_nocheck(currentpoint.x,
						currentpoint.y) + currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		if ((currentpoint.y + 1) < height && currentpoint.x < width
				&& (currentpoint.y + 1) > 0 && currentpoint.x > 0) {
			if (visited[currentpoint.x][currentpoint.y + 1] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x,
						currentpoint.y + 1), edges[2].get_nocheck(
						currentpoint.x, currentpoint.y) + currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		if ((currentpoint.y + 1) < height && (currentpoint.x + 1) < width
				&& (currentpoint.y + 1) > 0 && (currentpoint.x + 1) > 0) {
			if (visited[currentpoint.x + 1][currentpoint.y + 1] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x + 1,
						currentpoint.y + 1), edges[1].get_nocheck(
						currentpoint.x, currentpoint.y) + currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		if ((currentpoint.y + 1) < height && (currentpoint.x - 1) < width
				&& (currentpoint.y + 1) > 0 && (currentpoint.x - 1) > 0) {
			if (visited[currentpoint.x - 1][currentpoint.y + 1] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x - 1,
						currentpoint.y + 1), edges[3].get_nocheck(
						currentpoint.x, currentpoint.y) + currentedge.weight);
				tempedges.add(tempedge);
			}
		}

		if ((currentpoint.y - 1) < height && (currentpoint.x + 1) < width
				&& (currentpoint.y - 1) > 0 && (currentpoint.x + 1) > 0) {

			if (visited[currentpoint.x + 1][currentpoint.y - 1] != true) {

				tempedge = new Edge(currentpoint, new Point(currentpoint.x + 1,
						currentpoint.y - 1), edges[3].get_nocheck(
						currentpoint.x + 1, currentpoint.y - 1)
						+ currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		if ((currentpoint.y - 1) < height && (currentpoint.x - 1) < width
				&& (currentpoint.y - 1) > 0 && (currentpoint.x - 1) > 0) {

			if (visited[currentpoint.x - 1][currentpoint.y - 1] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x - 1,
						currentpoint.y - 1), edges[1].get_nocheck(
						currentpoint.x - 1, currentpoint.y - 1)
						+ currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		if ((currentpoint.y - 1) < height && currentpoint.x < width
				&& (currentpoint.y - 1) > 0 && currentpoint.x > 0) {

			if (visited[currentpoint.x][currentpoint.y - 1] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x,
						currentpoint.y - 1), edges[2].get_nocheck(
						currentpoint.x, currentpoint.y - 1)
						+ currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		if (currentpoint.y < height && (currentpoint.x - 1) < width
				&& currentpoint.y > 0 && (currentpoint.x - 1) > 0) {
			if (visited[currentpoint.x - 1][currentpoint.y] != true) {
				tempedge = new Edge(currentpoint, new Point(currentpoint.x - 1,
						currentpoint.y), edges[0].get_nocheck(
						currentpoint.x - 1, currentpoint.y)
						+ currentedge.weight);
				tempedges.add(tempedge);
			}
		}
		return tempedges;
	}

	/**
	 * 
	 * This the edge for the Dijekstras algorithm class, contains, start and end
	 * point, + weight. Also includes a compareTo method for finding out if the
	 * weight is higher, lower or equal.
	 * 
	 * @author Steven Aanetsen (sta17)
	 *
	 */
	class Edge implements Comparable<Edge> {
		private Point start;
		private Point end;
		private float weight;

		/**
		 * 
		 * @param start
		 *            - Point class, the start of the edge,
		 * @param end
		 *            - Point class, the end of the edge.
		 * @param weight
		 *            - primitive float, is the actual weight of the edge.
		 */
		public Edge(Point start, Point end, float weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		/**
		 * Returns a negative integer, zero, or a positive integer as this
		 * object is less than, equal to, or greater than the specified object.
		 * 
		 * @param e
		 *            - Edge class, this the edge that the current is supposed
		 *            to be compared against.
		 * 
		 */
		@Override
		public int compareTo(Edge e) {
			if (weight < e.weight) {
				return -1;
			} else if (weight > e.weight) {
				return 1;
			}
			return 0;
		}
	}

}