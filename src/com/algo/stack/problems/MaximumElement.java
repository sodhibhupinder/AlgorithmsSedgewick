package com.algo.stack.problems;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author baddie You have an empty sequence, and you will be given queries.
 *         Each query is one of these three types:
 * 
 *         1 x -Push the element x into the stack. 2 -Delete the element present
 *         at the top of the stack. 3 -Print the maximum element in the stack.
 *         Input Format
 * 
 *         The first line of input contains an integer, . The next lines each
 *         contain an above mentioned query. (It is guaranteed that each query
 *         is valid.)
 * 
 *         Constraints
 * 
 * 
 * 
 *         Output Format
 * 
 *         For each type query, print the maximum element in the stack on a new
 *         line.
 * 
 *         Sample Input
 * 
 *         10 1 97 2 1 20 2 1 26 1 20 2 3 1 91 3 Sample Output
 * 
 *         26 91
 */
public class MaximumElement {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int max = Integer.MIN_VALUE;
		Stack<StackNode> stack = new Stack<StackNode>();

		while (n > 0) {
			int choice = sc.nextInt();
			if (choice == 1) {
				int val = sc.nextInt();
				max = Math.max(val, max);

				stack.add(new StackNode(val, max));
			} else if (choice == 2) {
				if (!stack.isEmpty())
					stack.pop();
				// reset max
				if (stack.isEmpty())
					max = Integer.MIN_VALUE;
				else
					max = stack.peek().curMax;
			} else if (choice == 3) {
				if (!stack.isEmpty()) {
					System.out.println(stack.peek().curMax);
				}
			}

			n--;
		}
		sc.close();
	}

	private static class StackNode {
		int val;
		int curMax;

		public StackNode(int val, int curMax) {
			this.val = val;
			this.curMax = curMax;
		}

		public String toString() {
			return val + " [" + curMax + "]";
		}

	}
}