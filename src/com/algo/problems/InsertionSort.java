package com.algo.problems;
import java.util.Scanner;

public class InsertionSort {
	public static void insertIntoSorted(int[] ar) {
		
		for(int i=ar.length-1;i>=0;i--){
	        int value=ar[i];
	        int hole=i;
	        while(hole>=1 && ar[hole-1]>value){
	            ar[hole]=ar[hole-1];
	            hole--;
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
