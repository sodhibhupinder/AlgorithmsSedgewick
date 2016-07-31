package com.algo.problems;

import java.util.*;

/**
 * <b>Print all subarrays with 0 sum </b> 
 * Given an array, print all subarrays in the
 * array which has sum 0.
 * 
 * <b>Examples:</b>
 * 
 * Input: arr = [6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7] Output: Subarray found
 * from Index 2 to 4 Subarray found from Index 2 to 6 Subarray found from Index
 * 5 to 6 Subarray found from Index 6 to 9 Subarray found from Index 0 to 10
 * Related posts: Find if there is a subarray with 0 sum
 * 
 * We strongly recommend you to minimize your browser and try this yourself
 * first.
 * 
 * A simple solution is to consider all subarrays one by one and check if sum of
 * every subarray is equal to 0 or not. The complexity of this solution would be
 * O(n^2).
 * 
 * A better approach is to use Hashing.
 * 
 * Do following for each element in the array
 * 
1.Maintain sum of elements encountered so far in a variable (say sum).
2. If current sum is 0, we found a subarray starting from index 0 and ending at index current index
3. Check if current sum exists in the hash table or not.
4. If current sum exists in the hash table, that means we have subarray(s) present with 0 sum that ends at current index.
5. Insert current sum into the hash table
 * 
 * @author baddie
 *
 */
public class ZeroSumSubArray {

	static HashMap<Integer, ArrayList<Integer>> hashing = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, List<Integer>> indexMap = new HashMap<Integer, List<Integer>>();

	public static void filterZeroSumSubArrays(int[] arr) {
		 // Maintains sum of elements so far
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			// add current element to sum
			sum += arr[i];

			if (arr[i] == 0) {
				if (indexMap.get(i) != null) {
					ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(i);
					list.add(i);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					indexMap.put(i, list);
				}
			}
			// if sum is 0, we found a subarray starting
	        // from index 0 and ending at index i
			if (sum == 0) {
				if (indexMap.get(sum) != null) {
					ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(i);
					list.add(i);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					indexMap.put(sum, list);
				}
			}

			if (hashing.get(sum) != null) {

				ArrayList<Integer> elements = hashing.get(sum);

				for (int elem : elements) {

					if (indexMap.get(elem + 1) != null) {
						ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(elem + 1);
						list.add(i);
					} else {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(i);
						indexMap.put(elem + 1, list);
					}
					// indexMap.put(elem+1, i);
				}

				hashing.get(sum).add(i);
			} else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(i);
				hashing.put(sum, list);
			}
		}
	}
	// Utility function to print all subarrays with sum 0
	private static void print() {
		for (Map.Entry<Integer, List<Integer>> entry : indexMap.entrySet()) {

			ArrayList<Integer> list = (ArrayList<Integer>) entry.getValue();
			for (int i : list) {
				System.out.println("SubArray with zero sum is between indexes " + entry.getKey() + " - " + i);
			}
		}
	}
	// Driver code
	public static void main(String args[]) {
		int[] arr = { 6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7 };
		filterZeroSumSubArrays(arr);
		print();
	}
}
