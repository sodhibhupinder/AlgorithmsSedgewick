package com.algo.sort.problems;
import java.util.Scanner;

public class InsertionSort2 {

	public static void insertIntoSorted(int[] ar) {
		int size = ar.length;
		for(int i = 1; i < size; i++){ 

		    //start from 1 because first element is sorted
		    int insert = ar[i];

		    for(int j = i; j > 0; j--){  
		        //for loop to move backwards and perform insert 
		        if(ar[j] < ar[j - 1]){
		            ar[j] = ar[j -1];
		            ar[j - 1] = insert;
		        }
		    }

		    printArray(ar);
		}
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int[] ar = new int[s];
		for (int i = 0; i < s; i++) {
			ar[i] = in.nextInt();
		}
		insertIntoSorted(ar);
	}

	private static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
		}
		System.out.println("");
	}
}
