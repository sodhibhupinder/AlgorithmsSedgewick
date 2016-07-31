package com.algo.problems;

import java.util.Scanner;
import java.util.Stack;

/**
 * A queue is an abstract data type that maintains the order in which elements
 * were added to it, allowing the oldest elements to be removed from the front
 * and new elements to be added to the rear. This is called a First-In-First-Out
 * (FIFO) data structure because the first element added to the queue (i.e., the
 * one that has been waiting the longest) is always the first one to be removed.
 * 
 * A basic queue has the following operations:
 * 
 * Enqueue: add a new element to the end of the queue. Dequeue: remove the
 * element from the front of the queue and return it. In this challenge, you
 * must first implement a queue using two stacks. Then process queries, where
 * each query is one of the following types:
 * 
 * 1 x: Enqueue element into the end of the queue. 2: Dequeue the element at the
 * front of the queue. 3: Print the element at the front of the queue. Input
 * Format
 * 
 * The first line contains a single integer, , denoting the number of queries.
 * Each line of the subsequent lines contains a single query in the form
 * described in the problem statement above. All three queries start with an
 * integer denoting the query , but only query is followed by an additional
 * space-separated value, , denoting the value to be enqueued.
 * 
 * Constraints
 * 
 * It is guaranteed that a valid answer always exists for each query of type .
 * Output Format
 * 
 * For each query of type , print the value of the element at the front of the
 * queue on a new line.
 * 
 * Sample Input
 * 
 * 10 1 42 2 1 14 3 1 28 3 1 60 1 78 2 2 Sample Output
 * 
 * 14 14 Explanation
 * 
 * We perform the following sequence of actions:
 * 
 * Enqueue ; . Dequeue the value at the head of the queue, ; . Enqueue ; . Print
 * the value at the head of the queue, ; . Enqueue ; . Print the value at the
 * head of the queue, ; . Enqueue ; . Enqueue ; . Dequeue the value at the head
 * of the queue, ; . Dequeue the value at the head of the queue, ; .
 * 
 * @author baddie
 *
 */
public class QueueUsingTwoQueue {
	private static Stack<Integer> inbox = new Stack<Integer>();
	private static Stack<Integer> outbox = new Stack<Integer>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int Q = in.nextInt();
		for (int i = 0; i < Q; i++) {
			int typeOfQuery = in.nextInt();
			switch (typeOfQuery) {
			case 1:
				int x = in.nextInt();
				queue(x);
				break;
			case 2:
				dequeue();
				break;
			case 3:
				print();
				break;
			default:
				break;
			}

		}
	}

	public static void queue(Integer item) {
		inbox.push(item);
	}

	public static void print() {
		if (outbox.isEmpty()) {
			while (!inbox.isEmpty()) {
				outbox.push(inbox.pop());
			}
		}
		System.out.println(outbox.peek());
	}

	public static Integer dequeue() {
		if (outbox.isEmpty()) {
			while (!inbox.isEmpty()) {
				outbox.push(inbox.pop());
			}
		}
		return outbox.pop();
	}

}
