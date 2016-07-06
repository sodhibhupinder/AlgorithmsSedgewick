/******************************************************************************
 *  Compilation:  javac FloydWarshall.java
 *  Execution:    java FloydWarshall V E
 *  Dependencies: AdjMatrixEdgeWeightedDigraph.java
 *
 *  Floyd-Warshall all-pairs shortest path algorithm.
 *
 *  % java FloydWarshall 100 500
 *
 *  Should check for negative cycles during triple loop; otherwise
 *  intermediate numbers can get exponentially large.
 *  Reference: "The Floyd-Warshall algorithm on graphs with negative cycles"
 *  by Stefan Hougardy
 *
 ******************************************************************************/

package com.algo.problems;

import java.util.Scanner;
import java.util.Stack;

/**
 * The <tt>FloydWarshall</tt> class represents a data type for solving the
 * all-pairs shortest paths problem in edge-weighted digraphs with no negative
 * cycles. The edge weights can be positive, negative, or zero. This class finds
 * either a shortest path between every pair of vertices or a negative cycle.
 * <p>
 * This implementation uses the Floyd-Warshall algorithm. The constructor takes
 * time proportional to <em>V</em><sup>3</sup> in the worst case, where
 * <em>V</em> is the number of vertices. Afterwards, the <tt>dist()</tt>,
 * <tt>hasPath()</tt>, and <tt>hasNegativeCycle()</tt> methods take constant
 * time; the <tt>path()</tt> and <tt>negativeCycle()</tt> method takes time
 * proportional to the number of edges returned.
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class FloydWarshall {
	private boolean hasNegativeCycle; // is there a negative cycle?
	private double[][] distTo; // distTo[v][w] = length of shortest v->w path
	private DEdge[][] edgeTo; // edgeTo[v][w] = last edge on shortest v->w path

	/**
	 * Computes a shortest paths tree from each vertex to to every other vertex
	 * in the edge-weighted digraph <tt>G</tt>. If no such shortest path exists
	 * for some pair of vertices, it computes a negative cycle.
	 * 
	 * @param G
	 *            the edge-weighted digraph
	 */
	public FloydWarshall(Scanner in, int V, int E) {
		distTo = new double[V+1][V+1];
		edgeTo = new DEdge[V+1][V+1];

		// initialize distances to infinity
		for (int v = 0; v < V+1; v++) {
			for (int w = 0; w < V+1; w++) {
				distTo[v][w] = Double.POSITIVE_INFINITY;
			}
		}

		// initialize distances using edge-weighted digraph's

		for (int e = 0; e < E; e++) {
			int v = in.nextInt();
			int w = in.nextInt();
			double weight = in.nextDouble();
			DEdge edge = new DEdge(v, w, weight);
			distTo[v][w] = edge.weight();
			edgeTo[v][w] = edge;
			// in case of self-loops
			if (distTo[v][v] >= 0.0) {
				distTo[v][v] = 0.0;
				edgeTo[v][v] = null;
			}
		}

		// Floyd-Warshall updates
		for (int i = 0; i < V+1; i++) {
			// compute shortest paths using only 0, 1, ..., i as intermediate
			// vertices
			for (int v = 0; v < V+1; v++) {
				if (edgeTo[v][i] == null)
					continue; // optimization
				for (int w = 0; w < V+1; w++) {
					if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
						distTo[v][w] = distTo[v][i] + distTo[i][w];
						edgeTo[v][w] = edgeTo[i][w];
					}
				}
				// check for negative cycle
				if (distTo[v][v] < 0.0) {
					hasNegativeCycle = true;
					return;
				}
			}
		}
		// assert check(G);
	}

	/**
	 * Is there a negative cycle?
	 * 
	 * @return <tt>true</tt> if there is a negative cycle, and <tt>false</tt>
	 *         otherwise
	 */
	public boolean hasNegativeCycle() {
		return hasNegativeCycle;
	}

	/**
	 * Is there a path from the vertex <tt>s</tt> to vertex <tt>t</tt>?
	 * 
	 * @param s
	 *            the source vertex
	 * @param t
	 *            the destination vertex
	 * @return <tt>true</tt> if there is a path from vertex <tt>s</tt> to vertex
	 *         <tt>t</tt>, and <tt>false</tt> otherwise
	 */
	public boolean hasPath(int s, int t) {
		return distTo[s][t] < Double.POSITIVE_INFINITY;
	}

	/**
	 * Returns the length of a shortest path from vertex <tt>s</tt> to vertex
	 * <tt>t</tt>.
	 * 
	 * @param s
	 *            the source vertex
	 * @param t
	 *            the destination vertex
	 * @return the length of a shortest path from vertex <tt>s</tt> to vertex
	 *         <tt>t</tt>; <tt>Double.POSITIVE_INFINITY</tt> if no such path
	 * @throws UnsupportedOperationException
	 *             if there is a negative cost cycle
	 */
	public double dist(int s, int t) {
		if (hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		return distTo[s][t];
	}

	/**
	 * Returns a shortest path from vertex <tt>s</tt> to vertex <tt>t</tt>.
	 * 
	 * @param s
	 *            the source vertex
	 * @param t
	 *            the destination vertex
	 * @return a shortest path from vertex <tt>s</tt> to vertex <tt>t</tt> as an
	 *         iterable of edges, and <tt>null</tt> if no such path
	 * @throws UnsupportedOperationException
	 *             if there is a negative cost cycle
	 */
	public Iterable<DEdge> path(int s, int t) {
		if (hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		if (!hasPath(s, t))
			return null;
		Stack<DEdge> path = new Stack<DEdge>();
		for (DEdge e = edgeTo[s][t]; e != null; e = edgeTo[s][e.from()]) {
			path.push(e);
		}
		return path;
	}

	/**
	 * Unit tests the <tt>FloydWarshall</tt> data type.
	 */
	public static void main(String[] args) {

		// random graph with V vertices and E edges, parallel edges allowed
		try (Scanner scan = new Scanner(System.in)) {
			int V = scan.nextInt();
			int E = scan.nextInt();

			// run Floyd-Warshall algorithm
			FloydWarshall spt = new FloydWarshall(scan, V, E);

			// print all-pairs shortest path distances
			int Q = scan.nextInt();
			for (int q = 0; q < Q; q++) {
				int v = scan.nextInt();
				int w = scan.nextInt();
				if (spt.hasPath(v, w))
					System.out.println(new Double(spt.dist(v, w)).intValue());
				else if (v==w)
					System.out.println("0");
				else
					System.out.println("-1");
			}

			// print all-pairs shortest paths
//			for (int v = 0; v < V; v++) {
//				for (int w = 0; w < V; w++) {
//					if (spt.hasPath(v, w)) {
//						System.out.printf("%d to %d (%5.2f)  ", v, w, spt.dist(v, w));
//						for (DEdge e : spt.path(v, w))
//							System.out.print(e + "  ");
//						System.out.println();
//					} else {
//						System.out.printf("%d to %d no path\n", v, w);
//					}
//				}
//			}

		}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}

class DEdge {
	private final int v;
	private final int w;
	private final double weight;

	/**
	 * Create a directed edge from v to w with given weight.
	 */
	public DEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * Return the vertex where this edge begins.
	 */
	public int from() {
		return v;
	}

	/**
	 * Return the vertex where this edge ends.
	 */
	public int to() {
		return w;
	}

	/**
	 * Return the weight of this edge.
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Return a string representation of this edge.
	 */
	public String toString() {
		return v + "->" + w + " " + String.format("%5.2f", weight);
	}

}

/******************************************************************************
 * Copyright 2002-2015, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
