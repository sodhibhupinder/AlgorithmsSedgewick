package com.algo.problems;

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
public class AndXorOr {public static void main(String[] args) {

	int[] ar = handleInputArray();
	Stack<Integer> s = new Stack<Integer>();
	int i = 0, top = 0;
	long max = 0, cur = 0;
	while (i < ar.length) {
		if (s.size() == 0 || ar[s.peek()] <= ar[i])
			s.push(i++);
		else {
			top = s.peek();
			s.pop();
			cur = ar[top] ^ ar[i];
			if (max < cur)
				max = cur;
		}
	}
	while (s.size() != 0) {
		top = s.peek();
		s.pop();
		cur = ar[top] ^ ar[s.size() == 0 ? 0 : s.peek()];
		if (max < cur)
			max = cur;
	}
	System.out.println(max);
}



/** Handles Input
 * @return
 */
private static int[] handleInputArray() {
	try(Scanner sc = new Scanner(System.in)){
	int[] ar = new int[sc.nextInt()];
	for (int i = 0; i < ar.length; i++) {
		ar[i] = sc.nextInt();
	}
	return ar;
	}catch (Exception e) {
		throw e;
	}
}}
