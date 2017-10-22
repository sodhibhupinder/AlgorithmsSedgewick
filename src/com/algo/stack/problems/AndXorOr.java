package com.algo.stack.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author baddie Given an array of distinct elements. Let and be the smallest
 *         and the next smallest element in the interval where .
 * 
 *         .
 * 
 *         where , are the bitwise operators , and respectively. Your task is to
 *         find the maximum possible value of .
 * 
 *         Input Format
 * 
 *         First line contains integer . Second line contains integers,
 *         representing elements of the array .
 * 
 *         Constraints
 * 
 * 
 *         Output Format
 * 
 *         Print the value of maximum possible value of .
 * 
 *         Sample Input
 * 
 *         5 9 6 3 5 2 Sample Output
 * 
 *         15 Explanation
 * 
 *         Consider the interval the result will be maximum.
 *
 * 
 * 
 */
public class AndXorOr {
	/*
	 * This problem uses a rather peculiar trick with a stack that isn't at all
	 * intuitive and you're likely not to have seen before. Suppose you have an
	 * array A and you want to know every pair of smallest integers for each
	 * subarray (i.e. given a subarray, you want the smallest integer and the second
	 * smallest integer, where a subarray is a contiguous subset of the original
	 * array). This can be done in linear time using a stack. Here's the pseudocode
	 * ('yield' means a pair of smallest integers for some subarray has been found):
	 * 
	 * For each int i in the array A 
	 * while stack is nonempty 
	 * yield (i, top of stack)
	 * if i is less than the top of the stack, pop the stack 
	 * otherwise break the while loop 
	 * push i onto stack 
	 * 
	 * The idea is that if you have a value b in your
	 * stack, and you encounter a value c which is smaller, then b cannot form a
	 * minimum pair with anything to the right of c. So once you yield the pair
	 * (b,c) you can safely dispose of b (you already handled possible pairs to the
	 * left). This is the main subroutine for solving this problem, but I'll leave
	 * that to you.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();
		Stack<Integer> stk = new Stack<>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			while (!stk.isEmpty()) {
				max = Math.max(max, arr[i] ^ stk.peek()); //yield function
				if (arr[i] < stk.peek()) {
					stk.pop();
				} else
					break;
			}
			stk.push(arr[i]);
		}
		System.out.println(max);
	}
}
