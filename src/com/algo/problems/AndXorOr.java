package com.algo.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author baddie
 *Given an array  of  distinct elements. Let  and  be the smallest and the next smallest element in the interval  where .

.

where , are the bitwise operators ,  and  respectively. 
Your task is to find the maximum possible value of .

Input Format

First line contains integer . 
Second line contains  integers, representing elements of the array .

Constraints 
 

Output Format

Print the value of maximum possible value of .

Sample Input

5
9 6 3 5 2
Sample Output

15
Explanation

Consider the interval  the result will be maximum. 


 *
 */
public class AndXorOr {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		for (int i = 0; i < n; i++)
			System.out.println(count(list, scanner.nextInt()));
	}

	// using dynamic programming in bottom up
	public static int count(ArrayList<Integer> list, int item) {
		for (int i = item; i > 0; i--) {
			List<Integer> factors = factors(i);
			// for each factor calculate min
			int min = 1 + list.get(i - 1);
			for (int f : factors) {
				min = min(min, 1 + list.get(i / f));
			}
			list.add(min);
			return list.get(item);
		}
		return item;
	}

	public static int min(int a, int b) {
		return a<b?a:b;
	}

	public static List<Integer> factors(int n) {
		int lastfact = 1;
		ArrayList list = new ArrayList<>();
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				list.add(i);
		}
		return list;
	}
}


