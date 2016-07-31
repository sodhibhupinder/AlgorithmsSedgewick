package com.algo.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This problem can be solved in less than O(n) using a modified binary search.
 * The idea is to recursively divide the array into two equal subarrays if its
 * end elements are different. If both its end elements are same, that means
 * that all elements in the subarray is also same as the array is already
 * sorted. We then simply increment the count of the element by size of the
 * subarray.
 * 
 * The time complexity of above approach is O(m log n), where m is number of
 * distinct elements in the array of size n. Since m <= M (a constant), the time
 * complexity of this solution is O(log n).
 * Program to count number of occurrences of
// each element in the array in less than O(n) time
 * @author baddie
 *
 */
public class FindFrequencyUsingBinarySearch {

	public static void main(String[] args) {
		int[] arr = { 1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10 };
		findOccurences(arr);
	}
	// A wrapper over recursive function 
	// binarySearch(). It print number of 
	// occurrences of each element in the array.
	public static void findOccurences(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		binarySearch(arr, 0, arr.length - 1, map);
		print(map);
	}
	// Print the frequencies
	private static void print(Map<Integer, Integer> map) {
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " Occured " + entry.getValue());
		}
	}
	// A recursive function to count number of occurrences 
	// for each element in the array without traversing 
	// the whole array
	private static void binarySearch(int[] arr, int low, int high, Map<Integer, Integer> map) {
		if (low > high)
			return;
		else {
			int mid = low + (high - low) / 2;
			 // If element at index low is equal to element 
		    // at index high in the array
			if (arr[low] == arr[high]) {
				// increment the frequency of the element
		        // by count of elements between high and low
				put(map, arr[low], high - low + 1);
			} else {
				// Find mid and recurse for left and right 
		        // subarray
				binarySearch(arr, low, mid, map);
				binarySearch(arr, mid + 1, high, map);
			}
		}
	}

	private static void put(Map<Integer, Integer> map, int key, int value) {
		if (map.get(key) == null) {
			map.put(key, 0);
		}
		map.put(key, map.get(key) + value);
	}

}
