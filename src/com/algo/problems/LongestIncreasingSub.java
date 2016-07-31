package com.algo.problems;

import java.io.IOException;
import java.util.Scanner;

public class LongestIncreasingSub {
	private static int count;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		for (int i = 0; i < p; i++) {
			int m = in.nextInt();
			int n = in.nextInt();
			//computeSequence(m, n);
			char[] charArray = buildCharArray(n);
			printAll(charArray,m,"",n);
			System.out.println(count % 1000000007);
			count=0;
		}
	}

	private static char[] buildCharArray(int n) {
		char[] charArray = new char[n];
		for(int i=1;i<=n;i++)
		{
			charArray[i-1]=Integer.toString(i).charAt(0);
		}
		return charArray;
	}

//	private static void computeSequence(int m, int n) {
//		int[] sequence = new int[m];
//		generateSequence(m, n, sequence);
//		permute(sequence, 0, n);
//	}
//
//	private static void generateSequence(int m, int n, int[] sequence) {
//		for (int i = 1; i <= m; i++) {
//			if (i <= n)
//				sequence[i - 1] = i;
//		}
//		for (int i = m-1,j=0; i >= n; i--,j++) {
//			sequence[i] = sequence[j];
//		}
//	}
//
//	
//	
//	static void combinationUtil(int arr[], int data[], int start, int end, int index, int r) {
//		// Current combination is ready to be printed, print it
//		if (index == r) {
//			return;
//		}
//
//		// replace index with all possible elements. The condition
//		// "end-i+1 >= r-index" makes sure that including one element
//		// at index will make a combination with remaining elements
//		// at remaining positions
//		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
//			data[index] = arr[i];
//			combinationUtil(arr, data, i + 1, end, index + 1, r);
//		}
//	}
//	static void permute(int[] a, int k,int n) 
//    {
//        if (k == n) 
//        {
//        	System.out.println(Arrays.toString(a));
//        	if(findSubsequence(a)==n)
//        		count++;
//        } 
//        else 
//        {
//            for (int i = k; i < a.length; i++) 
//            {
//                int temp = a[k];
//                a[k] = a[i];
//                a[i] = temp;
// 
//                permute(a, k + 1,n);
// 
//                temp = a[k];
//                a[k] = a[i];
//                a[i] = temp;
//            }
//        }
//    }
	
	public static int findSubsequence(int[] arrA) {
		int[] LIS = new int[arrA.length];
		for (int i = 0; i < arrA.length; i++) {
			int max = -1;
			for (int j = 0; j < i; j++) {
				// check if previous elements > current element
				if (arrA[i] > arrA[j]) {
					// update the max from the previous entries
					if (max == -1 || max < LIS[j] + 1) {
						max = 1 + LIS[j];
					}
				}
			}
			if (max == -1) {
				// means none of the previous element has smaller than arrA[i]
				max = 1;
			}
			LIS[i] = max;
		}
		// find the max in the LIS[]
		int result = -1;
		int index = -1;
		for (int i = 0; i < LIS.length; i++) {
			if (result < LIS[i]) {
				result = LIS[i];
				index = i;
			}
		}
		// Print the result
		// Start moving backwards from the end and
		String path =  arrA[index] + " ";
		int res = result-1;
		for (int i = index-1; i >= 0; i--) {
			if(LIS[i]==res){
				path =  arrA[i] + " " + path;
				res--;
			}			
		}
		return result;	
	}
//	public static boolean permuteLexically(int[] data) {
//	    int k = data.length - 2;
//	    while (data[k] >= data[k + 1]) {
//	        k--;
//	        if (k < 0) {
//	            return false;
//	        }
//	    }
//	    int l = data.length - 1;
//	    while (data[k] >= data[l]) {
//	        l--;
//	    }
//	    swap(data, k, l);
//	    int length = data.length - (k + 1);
//	    for (int i = 0; i < length / 2; i++) {
//	        swap(data, k + 1 + i, data.length - i - 1);
//	    }
//	    return true;
//	}

//	private static void swap(int[] A, int i, int j) {
//		// function two swap two numbers.
//		// we will pass index of array to swap
//			int temp = A[i];
//			A[i] = A[j];
//			A[j] = temp;
//	}
	
	static void printAll(char[] c, int n, String start, int n2){
		  if(start.length() >= n){
			  if(findSubsequence(convertToIntArray(start))==n2)
	        		count++;
		  }else{
		    for(char x : c){ // not a valid syntax in Java
		      printAll(c, n, start+x,n2);
		    }
		  }
		}

	private static int[] convertToIntArray(String raw) {
		int[] num = new int[raw.length()];
		for (int i = 0; i < raw.length(); i++){
	        num[i] = raw.charAt(i) - '0';
	    }
		return num;
	}
}
