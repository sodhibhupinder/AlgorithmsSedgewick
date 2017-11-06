package com.algo.tree.problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UniqueColors {
	
	// Note: All elements of global arrays are
	// initially zero
	// All the arrays have been described above
	
	
	private static int maxn=1000005;
	static int max_color=1000005;
	static int[] bit= new int[maxn]; 
	static int[] vis_time= new int[maxn]; 
	static int[] end_time = new int[maxn];
	static int[] flat_tree = new int[2 * maxn];
	static List<List<Integer>> tree = new ArrayList<>(maxn);
	static List<List<Integer>> table = new ArrayList<>(max_color);
	static int[] traverser = new int[max_color];
	static boolean[] vis = new boolean[maxn];
	static int tim = 0;
	 
	//li, ri and index are stored in queries vector
	//in that order, as the sort function will use
	//the value li for comparison
	static List<Pair<Pair<Integer, Integer>, Integer>> queries = new ArrayList<>();;
	 
	//ans[i] stores answer to ith query
	static int[] ans = new int[maxn];
	 
	//update function to add val to idx in BIT
	static void update(int idx, int val)
	{
	    while ( idx < maxn )
	    {
	        bit[idx] += val;
	        idx += idx & -idx;
	    }
	}
	 
	//query function to find sum(1, idx) in BIT
	static int query(int idx)
	{
	    int res = 0;
	    while ( idx > 0 )
	    {
	        res += bit[idx];
	        idx -= idx & -idx;
	    }
	    return res;
	}
	 
	static void dfs(int v, int color[])
	{
		// mark the node visited
		vis[v] = true;

		// set visiting time of the node v
		vis_time[v] = ++tim;

		// use the color of node v to fill flat_tree[]
		flat_tree[tim] = color[v];
		List<Integer> vList = tree.get(v);
		for (int element : vList) {
				if (element<vis.length && !vis[element])
					dfs(element, color);
		}
		// set ending time for node v
		end_time[v] = ++tim;
		// setting its color in flat_tree[] again
		flat_tree[tim] = color[v];
	}
	 
	//function to add an edge(u, v) to the tree
	static void addEdge(int u, int v)
	{
		doAddEdge(u,v);
		doAddEdge(v,u);
	}

	private static void doAddEdge(int u,int v) {
		if(tree.get(u)==null)
		{
		List<Integer> array1 = new ArrayList<>();
		array1.add(v);
		tree.set(u, array1);
		}
		else
		{
			List<Integer> existingArray = tree.get(u);
			existingArray.add(v);
		}
	}
	 
	//function to build the table[] and also add
	//first occurrences of elements to the BIT
	static void hashMarkFirstOccurences(int n)
	{
	    for (int i = 1 ; i <= 2 * n ; i++)
	    {
	        table.get(flat_tree[i]).add(i);
	 
	        //if it is the first occurence of the element
	        //then add it to the BIT and increment traverser
	        if (table.get(flat_tree[i]).size() == 1)
	        {
	            //add the occurence to bit
	            update(i, 1);
	 
	            //make traverser point to next occurence
	            traverser[flat_tree[i]]++;
	        }
	    }
	}
	
	
	
	
	public static void main(String[] args)
	{
	   // testInput();
		
		try (final Scanner scanner = new Scanner(System.in)) {
			int numberOfNodes = scanner.nextInt();
			int[] colors = new int[numberOfNodes+1];
			colors[0]=0;
			for (int i = 1; i <= numberOfNodes; i++) {
				colors[i] = scanner.nextInt();
			}
			initializeTree(numberOfNodes);
			for (int i = 0; i < numberOfNodes-1; i++) {
				addEdge(scanner.nextInt(),scanner.nextInt());
			}
			int[] qVer = new int[numberOfNodes];
			for (int i = 0; i <numberOfNodes; i++) {
				qVer[i] = i+1;
			}
			int qn = qVer.length;
			countDistinctColors(colors, numberOfNodes, qVer, qn);
			
			scanner.close();
		}
	 
	}

	private static void testInput() {
		/*
	            1
	           / \
	          2   3
	         /|\  | \
	        4 5 6 7  8
	             /| \
	            9 10 11    */
	    int n = 11;
	    int color[] = {0, 2, 3, 3, 4, 1, 3, 4, 3, 2, 1, 1};
	    initializeTree(n);
	    // add all the edges to the tree
	    addEdge(1, 2);
	    addEdge(1, 3);
	    addEdge(2, 4);
	    addEdge(2, 5);
	    addEdge(2, 6);
	    addEdge(3, 7);
	    addEdge(3, 8);
	    addEdge(7, 9);
	    addEdge(7, 10);
	    addEdge(7, 11);
	 
	 
	    int qVer[] = {3, 2, 7};
	    
	    int qn = qVer.length;
	 
	    countDistinctColors(color, n, qVer, qn);
	}

	
	private static void initializeTree(int n) {
		for (int i = 0; i < n+1; i++) {
			tree.add(i,new ArrayList<Integer>());	
			table.add(i,new ArrayList<Integer>());	
		}
	}

	//function to process all the queries and store thier answers
	static void processQueries()
	{
	    int j = 1;
	    for (int i=0; i<queries.size(); i++)
	    {
	        //for each query remove all the ocurences before its li
	        //li is the visiting time of the node
	        //which is stored in first element of first pair
	        for ( ; j < queries.get(i).getFirst().getFirst() ; j++ )
	        {
	            int elem = flat_tree[j];
	 
	            //update(i, -1) removes an element at ith index
	            //in the BIT
	            update( table.get(elem).get(traverser[elem]-1), -1);
	 
	            //if there is another occurrence of the same element
	            if ( traverser[elem] < table.get(elem).size() )
	            {
	                //add the occurrence to the BIT and
	                //increment traverser
	                update(table.get(elem).get(traverser[elem]), 1);
	                traverser[elem]++;
	            }
	        }
	 
	        //store the answer for the query, the index of the query
	        //is the second element of the pair
	        //And ri is stored in second element of the first pair
	        ans[queries.get(i).getSecond()] = query(queries.get(i).getFirst().getSecond());
	    }
	}
	
	// Count distinct colors in subtrees rooted with qVer[0],
	// qVer[1], ...qVer[qn-1]
	static void countDistinctColors(int color[], int n, int qVer[], int qn)
	{
	    // build the flat_tree[], vis_time[] and end_time[]
	    dfs(1, color);
	 
	    // add query for u = 3, 2 and 7
	    for (int i=0; i<qn; i++)
	    {
	    	Pair<Integer,Integer> pair1 = new Pair<Integer,Integer>(vis_time[qVer[i]],end_time[qVer[i]]);
	    	Pair<Pair<Integer,Integer>,Integer> pair2 = new Pair<Pair<Integer,Integer>,Integer>(pair1,i);
	        queries.add(pair2);
	    }
	    // sort the queries in order of increasing vis_time
	    sort(queries);
	 
	    // make table[] and set '1' at first occurences of elements
	    hashMarkFirstOccurences(n);
	    
	    // process queries
	    processQueries();
	 
	    // print all the answers, in order asked
	    // in the question
	    for (int i=0; i<queries.size() ; i++)
	    {
	       System.out.println("Distinct colors in the corresponding subtree is: " + ans[i] );
	    }
	}
	
	private static void sort(List<Pair<Pair<Integer, Integer>, Integer>> queries2) {
		queries2.sort(new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
		    @Override
		    public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
		        return o1.getFirst().getFirst().compareTo(o2.getFirst().getFirst());
		    }
		});
	}

}
class Pair<L,R> {

	  private final L first;
	  private final R second;

	  public Pair(L first, R second) {
	    this.first = first;
	    this.second = second;
	  }

	  public L getFirst() { return first; }
	  public R getSecond() { return second; }

	  @Override
	  public int hashCode() { return first.hashCode() ^ second.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (!(o instanceof Pair)) return false;
	    Pair pairo = (Pair) o;
	    return this.first.equals(pairo.getFirst()) &&
	           this.second.equals(pairo.getSecond());
	  }

	}
