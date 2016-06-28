package com.algo.problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
//https://www.hackerrank.com/challenges/bfsshortreach
public class BFSProblem1 {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int numberOfTestCases = in.nextInt();
			for (int i = 0; i < numberOfTestCases; i++) {
				int V = in.nextInt();
				if (V < 0)
					throw new RuntimeException("Number of vertices must be nonnegative");
				int E = in.nextInt();
				Graph G = new Graph(V);
				for (int j = 0; j < E; j++) {
					int v = in.nextInt();
					int w = in.nextInt();
					G.addEdge(v-1, w-1);
				}
				int sourceNode = in.nextInt();
				BreadthFirstPaths bfs = new BreadthFirstPaths(G, sourceNode-1);
				for (int v = 0; v < G.V(); v++) {
		            if (bfs.hasPathTo(v) && bfs.distTo(v)!=0) {
		                System.out.print(bfs.distTo(v)*6 +" ");		                
		             }
		            else if(bfs.distTo(v)!=0){
		            	System.out.print("-1" + " ");
		            }

		        }
				System.out.println();
			}
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
}

class Graph {
	private final int V;
	private int E;
	private LinkedList<Integer>[] adj;

	/**
	 * Create an empty graph with V vertices.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0)
			throw new RuntimeException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (LinkedList<Integer>[]) new LinkedList[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<Integer>();
		}
	}

	/**
	 * Create a random graph with V vertices and E edges. Expected running time
	 * is proportional to V + E.
	 */
	public Graph(int V, int E) {
		this(V);
		if (E < 0)
			throw new RuntimeException("Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = (int) (Math.random() * V);
			int w = (int) (Math.random() * V);
			addEdge(v, w);
		}
	}

	/**
	 * Copy constructor.
	 */
	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	/**
	 * Return the number of vertices in the graph.
	 */
	public int V() {
		return V;
	}

	/**
	 * Return the number of edges in the graph.
	 */
	public int E() {
		return E;
	}

	/**
	 * Add the edge v-w to graph.
	 */
	public void addEdge(int v, int w) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	/**
	 * Return the list of neighbors of vertex v as in Iterable.
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}

class BreadthFirstPaths {

	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // marked[v] = is there an s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private int[] distTo; // distTo[v] = number of edges shortest s-v path

	// single source
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		bfs(G, s);

		assert check(G, s);
	}

	// multiple sources
	public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		bfs(G, sources);
	}

	// BFS from single soruce
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		q.add(s);

		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.add(w);
				}
			}
		}
	}

	// BFS from multiple sources
	private void bfs(Graph G, Iterable<Integer> sources) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int s : sources) {
			marked[s] = true;
			distTo[s] = 0;
			q.add(s);
		}
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.add(w);
				}
			}
		}
	}

	// is there a path between s (or sources) and v?
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	// length of shortest path between s (or sources) and v
	public int distTo(int v) {
		return distTo[v];
	}

	// shortest path bewteen s (or sources) and v; null if no such path
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	// check optimality conditions for single source
	private boolean check(Graph G, int s) {

		// check that the distance of s = 0
		if (distTo[s] != 0) {
			System.out.println("distance of source " + s + " to itself = " + distTo[s]);
			return false;
		}

		// check that for each edge v-w dist[w] <= dist[v] + 1
		// provided v is reachable from s
		for (int v = 0; v < G.V(); v++) {
			for (int w : G.adj(v)) {
				if (hasPathTo(v) != hasPathTo(w)) {
					System.out.println("edge " + v + "-" + w);
					System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
					System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
					return false;
				}
				if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
					System.out.println("edge " + v + "-" + w);
					System.out.println("distTo[" + v + "] = " + distTo[v]);
					System.out.println("distTo[" + w + "] = " + distTo[w]);
					return false;
				}
			}
		}

		// check that v = edgeTo[w] satisfies distTo[w] + distTo[v] + 1
		// provided v is reachable from s
		for (int w = 0; w < G.V(); w++) {
			if (!hasPathTo(w) || w == s)
				continue;
			int v = edgeTo[w];
			if (distTo[w] != distTo[v] + 1) {
				System.out.println("shortest path edge " + v + "-" + w);
				System.out.println("distTo[" + v + "] = " + distTo[v]);
				System.out.println("distTo[" + w + "] = " + distTo[w]);
				return false;
			}
		}

		return true;
	}
}
