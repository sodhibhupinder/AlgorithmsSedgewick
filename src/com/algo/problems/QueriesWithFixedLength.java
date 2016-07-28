package com.algo.problems;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

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
			long result = maxInWindows(numbers, d);
			System.out.println(result);
		}
		System.out.println();
	}

	public static  Long maxSlidingWindow(long[] nums, int k) {
		Deque<Long> results = new LinkedList<>();
		data.clear();
		maximums.clear();
        k = Math.min(k, (int)nums.length);
        int i = 0;
        for (;i < k - 1; ++i) //push first k - 1 numbers;
        {
        	Enqueue(nums[i]);
        }
        for (; i < nums.length; ++i)
        {
        	Enqueue(nums[i]);            // push a new element to queue;
            results.addLast(Max()); 	// report the current max in queue;
            Dequeue();                    // pop first element in queue;
        }
        
        return results.getFirst();
    }
	public static  Long  maxInWindows(long[] numbers, int windowSize)
	{
		Deque<Long> maxInSlidingWindows = new LinkedList<>();;
		data.clear();
		maximums.clear();
	    if(numbers.length >= windowSize && windowSize > 1)
	    {
	    	Deque<Integer>  indices = new LinkedList<>();

	        for(int i = 0; i < windowSize; ++i)
	        {
	            while(!indices.isEmpty() && numbers[i] >= numbers[indices.getLast()])
	                indices.removeLast();

	            indices.addLast(i);
	        }

	        for(int i = windowSize; i < numbers.length; ++i)
	        {
	            maxInSlidingWindows.addLast(numbers[indices.getFirst()]);

	            while(!indices.isEmpty() && numbers[i] >= numbers[indices.getLast()])
	                indices.removeLast();
	            if(!indices.isEmpty() && indices.getFirst() <= i - windowSize)
	                indices.removeFirst();

	            indices.addLast(i);
	        }
	        maxInSlidingWindows.addLast(numbers[indices.getLast()]);
	    }

	    return maxInSlidingWindows.getFirst();
	}
    public static void Enqueue(long nums)
	{
		data.addLast(nums);
		while (!maximums.isEmpty()  && maximums.getLast() <= nums) {
			maximums.removeLast();
		}
		maximums.addLast(nums);
	}

    public static Long Dequeue()
	{
    	Long result = data.getFirst();
		data.removeFirst();

		if (!maximums.isEmpty() && result == maximums.getFirst()) {
			maximums.removeFirst();
		}

		return result;
	}
    public static Long Max()
    {
        return maximums.getFirst();
    }
}

