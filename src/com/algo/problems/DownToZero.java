package com.algo.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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
public class DownToZero {

	static Map<Integer, Integer> dpMap;
	static Queue<Integer> priorityQueue = new PriorityQueue<>();
	public static void main(String[] args) {
		dpMap = new HashMap<>();
		dpMap.put(0,0);
		dpMap.put(1,1);
		dpMap.put(2,2);
		dpMap.put(3,3);
		Scanner in = new Scanner(System.in);
		List<Integer> output = new LinkedList<>();
		int i = in.nextInt();

		while (i > 0) {
			int num = in.nextInt();
			output.add(downToZero(num));
			System.out.println(dpMap);
			i--;
		}
		for (int j : output) {
			System.out.println(j);
		}
	}

	public static int downToZero(int num) {
		if(num<4)
		{
			return dpMap.get(num);
		}
		if (dpMap.containsKey(num)) {
			return dpMap.get(num);
		}
		int ret = 0;
		Set<Integer> factors = getAllFactors(num);
		for (Integer a : factors) {
			priorityQueue.add(1 + downToZero(a));
		}
		if (!priorityQueue.isEmpty()) {
		ret=priorityQueue.peek();
		dpMap.put(num, ret);
		}
		return 1+downToZero(num-1);
	}	
	

	private static Set<Integer> getAllFactors(int num) {
		Set<Integer> factors = new LinkedHashSet<>();
		int divisor = (int) Math.sqrt(num);
		while (divisor > 1) {
			if (num % divisor == 0) {
		            // If divisors are equal, print only one
		            if (num/divisor == divisor)
		            {
		            	factors.add(divisor);
		            }
		            else // Otherwise print both
		            {
		        		factors.add(Math.max(divisor,num/divisor));
		            }
			}
			divisor--;
		}
		System.out.println("factors of "+num+ " is " + factors);
		return factors;
	}
}
