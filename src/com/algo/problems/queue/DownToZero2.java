package com.algo.problems.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author baddie You are given Q queries. Each query consists of a single
 *         number N . You can perform 2 operations on N in each move. If
 *         N=(axb)(a!=1,b!=1), , we can change N=max(a,b)or decrease the value
 *         of N by 1.
 * 
 *         Determine the minimum number of moves required to reduce the value of
 *         N to 0.
 * 
 *         Input Format
 * 
 *         The first line contains the integer Q. The next lines each contain an
 *         integer, N .
 * 
 *         Constraints 1<=Q<=10^3 0<=N<=10^6
 * 
 *         Output Format
 * 
 *         Output Q lines. Each line containing the minimum number of moves
 *         required to reduce the value of N to 0.
 * 
 *         Sample Input
 * 
 *         1 3 Sample Output
 * 
 *         3 Explanation
 * 
 *         We only have one option that gives the minimum number of moves.
 *         Follow 3-> 2->1 -> 0. Hence, 3 moves.
 * 
 */
public class DownToZero2 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int Q = in.nextInt();
		for (int a0 = 0; a0 < Q; a0++) {
			int n = in.nextInt();
			HashMap<Integer, Integer> map = new HashMap<>();
			Queue<Integer> queue = new ArrayDeque<>();
			queue.add(n);
			map.put(n, 0);
			while (!queue.isEmpty() && !map.containsKey(0)) {
				int num = queue.poll();
				int step = map.get(num);
				for (int i = 2; i <= Math.sqrt(num); i++) {
					if (num % i == 0 && num / i != 1) {
						if (!map.containsKey(num / i) || map.get(num / i) > step + 1) {
							map.put(num / i, step + 1);
							queue.add(num / i);
						}
					}
				}
				if (!map.containsKey(num - 1) || map.get(num - 1) > step + 1) {
					queue.add(num - 1);
					map.put(num - 1, step + 1);
				}
			}
			System.out.println(map.get(0));

		}
		in.close();
	}
}
