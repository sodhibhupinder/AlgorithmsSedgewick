package com.algo.problems;

/**
 * Remove duplicates from an array of small primes
Given an array of primes such that the range of primes is small. Remove duplicates from the array.

Examples:

Input :  arr[] = {3, 5, 7, 2, 2, 5, 7, 7};
Output : arr[] = {2, 3, 5, 7}
The output can be printed in any order.


Input :  arr[] = {3, 5, 7, 3, 3, 13, 5, 13, 29, 13};
Output : arr[] = {3, 5, 7, 13, 29}
The output can be printed in any order.
Method 1 (Naive : O(n2))

A simple solution is to run two loops. Pick all elements one by one. For every picked element, check if it already seen or not. If already seen, then ignore it. Else add it to the array.
 * @author baddie
 * Removes duplicates using multiplication of distinct primes in array
 */
public class RemoveDupesOfSmallPrime {
	
	static int removeDups(int[] array)
	{
	   long prod = array[0];
	   int res_ind = 1;
	   for (int i=1; i<array.length; i++)
	   {
	       if (prod % array[i] != 0)
	       {
	    	   array[res_ind++]  = array[i];
	          prod *= array[i];
	       }
	   }
	   return res_ind;
	}
	 
	// Driver code
	public static void main(String args[]) 
	{
	    int[] arr =  new int[]{3, 5, 7, 2, 2, 5, 7, 7};
	    int result = removeDups(arr);
	    for (int i=0; i<result; i++)
	    {
	    	System.out.println(arr[i]);
	    }
	}
}