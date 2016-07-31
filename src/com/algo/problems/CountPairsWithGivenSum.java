package com.algo.problems;

import java.util.HashMap;

class CountPairsWithGivenSum {
	
	static int getCountPairsWithGivenSum(int[] arr, int sum) {
		int n = arr.length;
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		
		int res = 0;
		
		for(int i=0;i<n;i++) {
			if(hm.containsKey(arr[i])) {
				int c = hm.get(arr[i]); 
				hm.put(arr[i],c+1);
			}
			else
				hm.put(arr[i],1);
		}
		
		int twiceCount = 0;
		for(int i=0;i<n;i++) {
			if(hm.get(sum-arr[i]) != null) {
				twiceCount += hm.get(sum-arr[i]); 
				if(sum-arr[i] == arr[i])
					twiceCount--;
			}
		}
		
		return twiceCount/2;
	}
	
	static void printArray(int[] arr) {
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = {10, 12, 10, 15, -1, 7, 6, 
                   5, 4, 2, 1, 1, 1};
		
		printArray(arr);
		
		System.out.println("ans = " + getCountPairsWithGivenSum(arr, 11));
	}
}
