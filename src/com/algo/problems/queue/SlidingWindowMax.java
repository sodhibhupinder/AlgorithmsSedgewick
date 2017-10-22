package com.algo.problems.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/queries-with-fixed-length/problem
/*Consider an -integer sequence, . We perform a query on  by using an integer, , to calculate the result of the following expression:

In other words, if we let , then you need to calculate .

Given  and  queries (each query consists of an integer, ), print the result of each query on a new line.

Input Format

The first line consists of two space-separated integers describing the respective values of  and . 
The second line consists of  space-separated integers describing the respective values of . 
Each of the  subsequent lines contains a single integer denoting the value of  for that query.

Constraints

Output Format

For each query, print an integer denoting the query's answer on a new line. After completing all the queries, you should have printed  lines.

Sample Input 0

5 5
33 11 44 11 55
1
2
3
4
5
Sample Output 0

11
33
44
44
55
Explanation 0

For , the answer is

. 
For , the answer is
. 
For , the answer is
. 
For , the answer is
. 
For , the answer is
.
Sample Input 1

5 5
1 2 3 4 5
1
2
3
4
5
Sample Output 1

1
2
3
4
5
Explanation 1

For each query, the "prefix" has the least maximum value among the consecutive subsequences of the same size.
*/

public class SlidingWindowMax { 
	// A Dequeue (Double ended queue) based method for printing maixmum element of
	// all subarrays of size k
	static void printMax(int arr[], int n, int k) {
		// Create a Double Ended Queue, Qi that will store indexes of array elements
		// The queue will store indexes of useful elements in every window and it will
		// maintain decreasing order of values from front to rear in Qi, i.e.,
		// arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
		Deque<Integer> Qi = new LinkedList<Integer>();
		PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>();

		/* Process first k (or first window) elements of array */
		int i;
		for (i = 0; i < k; ++i) {
			// For very element, the previous smaller elements are useless so
			// remove them from Qi
			while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
				Qi.removeLast(); // Remove from rear

			// Add new element at rear of queue
			Qi.addLast(i);
		}

		// Process rest of the elements, i.e., from arr[k] to arr[n-1]
		for (; i < n; ++i) {
			// The element at the front of the queue is the largest element of
			// previous window, so print it
			minQueue.add(arr[Qi.peek()]);

			// Remove the elements which are out of this window
			while ((!Qi.isEmpty()) && Qi.peek() <= i - k)
				Qi.removeFirst();

			// Remove all elements smaller than the currently
			// being added element (remove useless elements)
			while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
				Qi.removeLast();

			// Add current element at the rear of Qi
			Qi.addLast(i);

		}

		// Print the maximum element of last window
		minQueue.add((arr[Qi.peek()]));
		System.out.println(minQueue.peek());
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int q = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
        	arr[i] = scn.nextInt();
        }
        for(int i=0; i<q; i++)
        {
        int k = scn.nextInt();
		printMax(arr, arr.length, k);
        }
		scn.close();
	}
}
