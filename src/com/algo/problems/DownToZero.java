package com.algo.problems;

import java.util.HashMap;
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
		System.out.println("Computing subproblem for "+ num);
		if(num>0 && num<4)
		{
			return dpMap.get(num);
		}
		if (dpMap.containsKey(num)) {
			return dpMap.get(num);
		}
		int ret=Integer.MAX_VALUE;
		Set<Integer> factors = getAllFactors(num);
		Queue<Integer> priorityQueue = new PriorityQueue<>();
		if (factors.size()== 0){
	        ret= 1 + downToZero(num-1);
	        priorityQueue.add(ret);
	    }
		for (Integer a : factors) {
			System.out.println("invoking downtozero for number " +a);
			priorityQueue.add(1 + downToZero(a));
			System.out.println("invoking downtozero for number " +(a-1));
			priorityQueue.add(2 + downToZero(a-1));			
		}
		if (!priorityQueue.isEmpty()) {
		ret = priorityQueue.peek();
		}
		dpMap.put(num,ret);		
		return ret;
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
		            	//factors.add(divisor-1);
		            }
		            else // Otherwise print both
		            {
		        		factors.add(Math.max(divisor,num/divisor));
		        		//factors.add(Math.max(divisor,num/divisor)-1);
		            }
			}
			divisor--;
		}
		System.out.println("factors of "+num+ " is " + factors);
		return factors;
	}
}
