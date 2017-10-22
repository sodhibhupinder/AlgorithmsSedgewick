package com.algo.problems;

import java.util.*;

public class DijkstraSolution1 {
	static class Pair {
		int vertex = 0;
		int dist = Integer.MAX_VALUE;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
			int N = in.nextInt();
			int M = in.nextInt();
			ArrayList<int[]>[] graph = (ArrayList<int[]>[]) new ArrayList[N + 1];
			PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {

				@Override
				public int compare(Pair p1, Pair p2) {
					// TODO Auto-generated method stub
					return p1.dist - p2.dist;
				}
			});
			for (int j = 0; j < graph.length; j++) {
				graph[j] = new ArrayList<int[]>();
			}
			for (int j = 0; j < M; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int r = in.nextInt();

				int[] t = { y, r };
				int[] b = { x, r };
				graph[x].add(t);
				graph[y].add(b);
			}
			int S = in.nextInt();
			HashMap<Integer, Pair> locator = new HashMap<Integer, Pair>();
			Pair sp = new Pair();
			sp.dist = 0;
			sp.vertex = S;
			pq.add(sp);
			locator.put(S, sp);

			for (int j = 1; j < graph.length; j++) {
				if (j != S) {
					Pair p = new Pair();
					p.vertex = j;
					pq.add(p);
					locator.put(j, p);
				}
			}

			int[] dist = new int[N + 1];
			while (!pq.isEmpty()) {

				Pair p = pq.remove();
				if (p.dist == Integer.MAX_VALUE)
					break;
				dist[p.vertex] = p.dist;
				for (int[] t : graph[p.vertex]) {
					int dnew = t[1] + p.dist;
					Pair p2 = locator.get(t[0]);
					if (p2.dist > dnew) {
						p2.dist = dnew;
						pq.remove(p2);
						pq.add(p2);
					}
				}

			}
			StringBuilder res = new StringBuilder(" ");
			for (int j = 1; j < dist.length; j++) {
				if (j != S) {
					if (dist[j] != 0)
						res.append(dist[j] + " ");
					else
						res.append(-1 + " ");
				}
			}
			System.out.println(res.toString().trim());

		}
	}
}
