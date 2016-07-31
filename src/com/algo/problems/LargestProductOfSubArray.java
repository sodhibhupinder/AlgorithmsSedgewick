package com.algo.problems;

/**
 * @author baddie Largest product of a subarray of size k Given an array
 *         consisting of n positive integers, and an integer k. Find the largest
 *         product subarray of size k, i.e., find maximum produce of k
 *         contiguous elements in the array where k <= n. Examples:
 * 
 *         Input: arr[] = {1, 5, 9, 8, 2, 4, 1, 8, 1, 2} k = 6 Output: 4608 The
 *         subarray is {9, 8, 2, 4, 1, 8}
 * 
 *         Input: arr[] = {1, 5, 9, 8, 2, 4, 1, 8, 1, 2} k = 4 Output: 720 The
 *         subarray is {5, 9, 8, 2}
 * 
 *         Input: arr[] = {2, 5, 8, 1, 1, 3}; k = 3 Output: 80 The subarray is
 *         {2, 5, 8}
 * 
 *         Method 1 (Simple : O(n*k))
 * 
 *         A Naive approach would be to consider all the subarrays of size k one
 *         by one. Such a approach would require two loops hence the complexity
 *         would be O(n*k). Method 2 (Efficient : O(n))
 * 
 *         We can solve it in O(n) by using the fact that product of a subarray
 *         of size k can be computed in O(1) time if we have product of previous
 *         subarray available with us.
 *		   curr_product = (prev_product / arr[i-1])* arr[i + k -1]
 * 
 *         prev_product : Product of subarray of size k beginning with arr[i-1]
 * 
 *         curr_product : Product of subarray of size k beginning with arr[i] In
 *         this way we can compute the maximum k size subarray product in only
 *         one traversal. Below is C++ implementation of the idea.
 */
public class LargestProductOfSubArray {

	// This function returns maximum product of a subarray
	// of size k in given arrar, arr[0..n-1]. This function
	// assumes that k is smaller than or equal to n.
	static int findMaxProduct(int arr[], int n, int k) {
		// Initialize the MaxProduct to 1, as all elements
		// in the array are positive
		int MaxProduct = 1;
		for (int i = 0; i < k; i++)
			MaxProduct *= arr[i];

		int prev_product = MaxProduct;

		// Consider every product beginning with arr[i]
		// where i varies from 1 to n-k-1
		for (int i = 1; i <= n - k; i++) {
			int curr_product = (prev_product / arr[i - 1]) * arr[i + k - 1];
			MaxProduct = Math.max(MaxProduct, curr_product);
			prev_product = curr_product;
		}

		// Return the maximum product found
		return MaxProduct;
	}

	// Driver code
	public static void main(String args[]) {
		int arr1[] = { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 };
		int k = 6;
		int n = arr1.length;
		System.out.println(findMaxProduct(arr1, n, k));
		k = 4;
		System.out.println(findMaxProduct(arr1, n, k));

		int arr2[] = { 2, 5, 8, 1, 1, 3 };
		k = 3;
		System.out.println(findMaxProduct(arr2, arr2.length, k));
	}
}
