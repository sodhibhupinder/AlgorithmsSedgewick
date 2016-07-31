package com.algo.problems;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author baddie
 *Queries with Fixed
Length
You are given a sequence that consists of integers. There are queries. For each query, you will be
given an integer , and you need to calculate this equation:
Input Format
The first line consists of two space separated integers: and .
The next line consists of space separated integers: , ,...,
The following lines contains a single integer: .
Constraints
Output Format
Output lines, each denoting the answer to the respective query.
Sample Input
5 5
1 2 3 4 5
1
2
3
4
5
Sample Output
1
2
3
4
5
Explanation
Each prefix has the least maximum value among the consecutive subsequences that have the same size.
 */
public class QueriesWithFixedLength {
	static Deque<Long> data = new LinkedList<>();
    static Deque<Long> maximums=new LinkedList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int Q = in.nextInt();
		long[] numbers = new long[N];
		for (int i = 0; i < N; i++) {
			numbers[i]=in.nextLong();
		}
		for (int i = 0; i < Q; i++) {
			int d = in.nextInt();
			long result = slidingWindowMax(numbers, d);
			System.out.println(result);
		}
		System.out.println();
	}

	
	public static long maxSlidingWindow(long[] numbers, int k) {
	    if(numbers==null||numbers.length==0)
	        return 0;
	 
	    long[] result = new long[numbers.length-k+1];
	 
	    LinkedList<Integer> deque = new LinkedList<Integer>();
	    for(int i=0; i<numbers.length; i++){
	        if(!deque.isEmpty()&&deque.peekFirst()==i-k) 
	            deque.poll();
	 
	        while(!deque.isEmpty()&&numbers[deque.peekLast()]<numbers[i]){
	            deque.removeLast();
	        }    
	 
	        deque.offer(i);
	 
	        if(i+1>=k)
	            result[i+1-k]=numbers[deque.peek()];
	    }
	 
	    return result[result.length-1];
	}
	public static long slidingWindowMax(final long[] numbers, final int w) {
	    final long[] max_left = new long[numbers.length];
	    final long[] max_right = new long[numbers.length];

	    max_left[0] = numbers[0];
	    max_right[numbers.length - 1] = numbers[numbers.length - 1];

	    for (int i = 1; i < numbers.length; i++) {
	        max_left[i] = (i % w == 0) ? numbers[i] : Math.max(max_left[i - 1], numbers[i]);

	        final int j = numbers.length - i - 1;
	        max_right[j] = (j % w == 0) ? numbers[j] : Math.max(max_right[j + 1], numbers[j]);
	    }

	    final long[] sliding_max = new long[numbers.length - w + 1];
	    for (int i = 0, j = 0; i + w <= numbers.length; i++) {
	        sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
	    }

	    return sliding_max[0];
	}
    
}

