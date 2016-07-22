package com.algo.problems;
import java.util.Arrays;
import java.util.Scanner;

//Print Number of Insert Sort Shifts - Quick Sort Swaps
public class QuickSort5 {

	int A[];
	//Variable to Count the Number of Insert Sort Shifts 
	private static int N;
	// Count the number of Quick Sort Swaps
	private static int Q;

	/*
	 * In this function last element is chosen as pivot, then elements are
	 * arranged such that,all elements smaller than pivot are arranged to left
	 * of pivot and all greater elements to right of pivot
	 */

	int partition(int start, int end) {
		int pivot = A[end]; // choosing pivot element
		int pIndex = start; // Index of first element

		for (int i = start; i <= end - 1; i++) {
			/*
			 * If current element is smaller than or equal to pivot then
			 * exchange it with element at pIndex and increment the pIndex
			 */
			if (A[i] <= pivot) {
				// swap A[pIndex] and A[i]
				swap(pIndex, i);
				pIndex = pIndex + 1;
			}
		}
		// swap A[pIndex] and A[end] (or pivot)
		swap(pIndex, end);
		return pIndex;
	}

	/*
	 * The main function that implements QuickSort arr[] --> array to be sorted,
	 * start --> Starting index, end --> Ending index
	 */
	void sort(int arr[], int start, int end) {
		this.A = arr;
		if (start < end) {
			/*
			 * pi is partitioning index, A[pi] is now at right place
			 */
			int p = partition(start, end);
			//printArray(A);
			// Recursively sort elements left of pivot
			// and elements right of pivot
			sort(A, start, p - 1);
			sort(A, p + 1, end);
		}
	}

	// function two swap two numbers.
	// we will pass index of array to swap
	private void swap(int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		Q++;
	}
	
	public static void insertIntoSorted(int[] ar) {
		int size = ar.length;
		for (int i = 1; i < size; i++) {

			// start from 1 because first element is sorted
			int insert = ar[i];

			for (int j = i; j > 0; j--) {
				// for loop to move backwards and perform insert
				if (ar[j] < ar[j - 1]) {
					ar[j] = ar[j - 1];
					ar[j - 1] = insert;
					N++;
				}
			}

			// printArray(ar);
		}
		//System.out.println(N);
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int[] ar = new int[s];
		for (int i = 0; i < s; i++) {
			ar[i] = in.nextInt();
		}
		int[] copyOfar= new int[s];
		copyOfar=Arrays.copyOf(ar, ar.length);
		insertIntoSorted(ar);
		QuickSort5 ob = new QuickSort5();
		ob.sort(copyOfar, 0, s - 1);
		System.out.println(N-Q);
	}

	private static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
		}
		System.out.println("");
	}
}
