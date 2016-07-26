package com.algo.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
	
	static  List<Integer> primeNumberList = new ArrayList<>();
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		ArrayList<Integer> list = new ArrayList<>(n);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		for (int i = 0; i < n; i++)
			System.out.println(count(list, scanner.nextInt()));
	}

	public static int count(ArrayList<Integer> list, int item) {
		//SieveOfEratosthenes(item);
		for (int i = 4; i <= item; i++) {
			List<Integer> factors = factors(i);
			// for each factor calculate min

			int min = 1 + list.get(i - 1);
			if (!factors.isEmpty()) {
				for (int f : factors) {
					min = Math.min(min, 1 + list.get(i / f));
				}
			}
			list.add(min);
		}
		return (int) list.get(item);
	}

	static List<Integer> factors(int num) {
//		if(primeNumberList.contains(num))
//		{
//			return Collections.emptySet();
//		}
		int divisor = (int) Math.sqrt(num);
		List<Integer> factors = new ArrayList<>(divisor / 2);
		while (divisor > 1) {
			if (num % divisor == 0) {
				// If divisors are equal, print only one
				// if (num/divisor == divisor)
				// {
				// factors.add(divisor);
				// }
				// else // Otherwise print both
				// {
				// factors.add(Math.max(divisor,num/divisor));
				// }
				factors.add(divisor);
			}
			divisor--;
		}
		return factors;
	}

	public static void SieveOfEratosthenes(int n) {
		// Create a boolean array "prime[0..n]" and initialize
		// all entries it as true. A value in prime[i] will
		// finally be false if i is Not a prime, else true.
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);

		for (int p = 2; p * p <= n; p++) {
			// If prime[p] is not changed, then it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}
		for (int p = 2; p <= n; p++) {
			if (prime[p]) {
				primeNumberList.add(p);
			}
		}
	}
}
