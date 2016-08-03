package com.algo.heap.problems;

import java.util.*;

class MaxComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer x, Integer y) {

		return y.compareTo(x);
	}
}

/**
 * The median of a set of integers is the midpoint value of the data set for
 * which an equal number of integers are less than and greater than the value.
 * To find the median, you must first sort your set of integers in
 * non-decreasing order, then:
 * 
 * If your set contains an odd number of elements, the median is the middle
 * element of the sorted sample. In the sorted set , is the median. If your set
 * contains an even number of elements, the median is the average of the two
 * middle elements of the sorted sample. In the sorted set , is the median.
 * Given an input stream of integers, you must perform the following task for
 * each integer:
 * 
 * Add the integer to a running list of integers. Find the median of the updated
 * list (i.e., for the first element through the element). Print the list's
 * updated median on a new line. The printed value must be a double-precision
 * number scaled to decimal place (i.e., format). Input Format
 * 
 * The first line contains a single integer, , denoting the number of integers
 * in the data stream. Each line of the subsequent lines contains an integer, ,
 * to be added to your list.
 * 
 * Constraints
 * 
 * Output Format
 * 
 * After each new integer is added to the list, print the list's updated median
 * on a new line as a single double-precision number scaled to decimal place
 * (i.e., format).
 * 
 * Sample Input
 * 
 * 6 12 4 5 3 8 7 Sample Output
 * 
 * 12.0 8.0 5.0 4.5 5.0 6.0 Explanation
 * 
 * There are integers, so we must print the new median on a new line as each
 * integer is added to the list:
 * 
 * 
 * @author baddie
 *
 */
public class FindRunningMedian {

	public static double getMedian(int ip, double m, PriorityQueue<Integer> l_heap, PriorityQueue<Integer> r_heap) {

		int diff = l_heap.size() - r_heap.size();
		// System.out.println("\n\nip : " + ip);

		switch (diff) {

		case 0:
			if (ip <= m) {

				l_heap.offer(ip);
				m = (int) l_heap.peek();
			} else {

				r_heap.offer(ip);
				m = (int) r_heap.peek();
			}
			break;

		case 1:
			if (ip <= m) {

				r_heap.offer(l_heap.poll());
				l_heap.offer(ip);
			} else {
				r_heap.offer(ip);
			}
			m = ((double) l_heap.peek() + (double) r_heap.peek()) / 2;
			break;

		case -1:
			if (ip > m) {

				l_heap.offer(r_heap.poll());
				r_heap.offer(ip);
			} else {
				l_heap.offer(ip);
			}
			m = ((double) l_heap.peek() + (double) r_heap.peek()) / 2;
			break;
		}

		// System.out.println("l_heap : " + l_heap);
		// System.out.println("r_heap : " + r_heap);
		return m;
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		MaxComparator max_comparator = new MaxComparator();
		int N = in.nextInt();
		PriorityQueue<Integer> l_heap = new PriorityQueue<Integer>(N / 2, max_comparator);
		PriorityQueue<Integer> r_heap = new PriorityQueue<Integer>();
		double m = 0;
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			int n = in.nextInt();
			arr[i] = n;
			m = getMedian(arr[i], m, l_heap, r_heap);
			System.out.println(m);
		}
	}

}
