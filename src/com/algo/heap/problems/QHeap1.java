package com.algo.heap.problems;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author baddie
 *
 */
public class QHeap1 {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int n = scanner.nextInt();
	    PriorityQueue<Long> heap = new PriorityQueue<Long>(n);
	    for (; n>0; n--) {
	        int queryType = scanner.nextInt();
	        if (queryType==1) {
	        	long input = scanner.nextLong();
	            heap.add(input);
	        } else if (queryType==2) {
	        	long input = scanner.nextLong();
	            heap.remove(input);
	        } else if (queryType==3) {
	            System.out.println(heap.peek());
	        }
	    }
	}
}
