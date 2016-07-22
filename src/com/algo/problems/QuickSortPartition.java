package com.algo.problems;

import java.util.Scanner;

public class QuickSortPartition {

	static void partition(int[] ar) {
		int[] leftArray = new int[ar.length];
		int[] rightArray= new int[ar.length];
		int[] equalArray= new int[ar.length];
		int leftArrayIndex=0;
		int rightArrayIndex=0;
		int equalArrayIndex=0;
		for(int i=0;i<ar.length;i++)
		{
			if(ar[i]<ar[0])
			{
				leftArray[leftArrayIndex++]=ar[i];
			}
			if(ar[i]==ar[0])
			{
				equalArray[equalArrayIndex++]=ar[i];
			}
			if(ar[i]>ar[0])
			{
				rightArray[rightArrayIndex++]=ar[i];
			}
			
		}
		printArray(leftArray,leftArrayIndex);
		printArray(equalArray,equalArrayIndex);
		printArray(rightArray,rightArrayIndex);

	}

	static void printArray(int[] ar,int index) {
		for (int n=0;n<index;n++) {
			System.out.print(ar[n] + " ");
		}
		//System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		partition(ar);
	}
}

