package com.algo.problems;

import java.util.Random;
import java.util.Scanner;

public class MaxSubArray {
	
	public static void main(String[] args) {
	       
    	try(Scanner scan = new Scanner(System.in))
    	{
    	int T = scan.nextInt();
    	for(int t=0;t<T;t++)
    	{
    	int size = scan.nextInt();
    	int[] arr = new int[size];
    	for(int i =0;i<size;i++)
    	{
    		arr[i]=scan.nextInt();
    	}
    	int SumContigous = maxSubArrayContigous(arr);
    	int SumNonContigous = maxSubArrayNonContigous(arr);
    	System.out.println(SumContigous+" "+ SumNonContigous);
    	}
    	}catch (Exception e) {
    	throw e;
    	}
    }

	private static int maxSubArrayNonContigous(int[] arr) {
		int max_ending_here = arr[0];
		int max_so_far = arr[0];
		for (int x = 1; x < arr.length; x++) {
			if(arr[x]>0)
			{
			max_ending_here = Math.max(arr[x], max_ending_here + arr[x]);
			max_so_far = Math.max(max_so_far, max_ending_here);
			}
			else
			{
				max_so_far = Math.max(max_so_far, arr[x]);
			}
		}
		return max_so_far;
	}

	private static int maxSubArrayContigous(int[] arr) {
		int max_ending_here = arr[0];
		int max_so_far = arr[0];
		for (int x = 1; x < arr.length; x++) {
			max_ending_here = Math.max(arr[x], max_ending_here + arr[x]);
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		return max_so_far;
	}
	
}
