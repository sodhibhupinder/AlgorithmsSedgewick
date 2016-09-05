package com.algo.uf.problems;

import java.util.Scanner;

public class ComponentsGraph {

    private int[] id;    // id[i] = parent of i
    private int[] sz;    // sz[i] = number of objects in subtree rooted at i
    private int count;   // number of components
    // Create an empty union find data structure with N isolated sets.
    public ComponentsGraph(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // Return the number of disjoint sets.
    public int count() {
        return count;
    }

    // Return component identifier for component containing p
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

   // Are objects p and q in the same set?
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

  
   // Replace sets containing p and q with their union.
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make smaller root point to larger one
        if   (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else                 { id[j] = i; sz[i] += sz[j]; }
        count--;
    }
    
	public void printMinMax() {
		int minTree = Integer.MAX_VALUE;
		int maxTree = Integer.MIN_VALUE;
		for (int i = 1; i <= sz.length-1; i++) {
			// If it's a root node, then check the size
			if (find(i) == i) {
				if (sz[i] > maxTree) {
					maxTree = sz[i];
				}
				if (sz[i] < minTree && sz[i]!=1) {
					minTree = sz[i];
				}
			}
		}
		System.out.println(minTree+" "+maxTree);
	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		// Initialize UF
		ComponentsGraph uf = new ComponentsGraph(2*N+1);
		// read vertices of newly added edge
		for (int k = 1; k <= N; k++) {
			int i = scan.nextInt();
			int j = scan.nextInt();
			uf.union(i, j);
		}
		uf.printMinMax();
	}

}
