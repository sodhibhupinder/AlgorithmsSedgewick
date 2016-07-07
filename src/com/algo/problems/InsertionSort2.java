package com.algo.problems;
import java.util.Scanner;

public class InsertionSort2 {
	public static void insertIntoSorted(int[] ar) {
		
		for(int i=1;i<ar.length;i++){
	        int value=ar[i];
	        int hole=i;
	        while(hole<ar.length && ar[hole]>value){
	            ar[hole+1]=ar[hole];
	            hole++;
	            printArray(ar);
	        }
	        ar[hole]=value;
	    }        
	    printArray(ar);
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
