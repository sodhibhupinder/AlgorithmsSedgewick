package com.algo.problems;

import java.util.Scanner;

/* Java implementation QuickSort */
public class QuickSort2 {
   int A[];
    
   /* In this function last element is chosen as pivot,
      then elements are arranged such that,all elements
      smaller than pivot are arranged to left of pivot
      and all greater elements to right of pivot */
    
int partition(int start, int end)
{
    int pivot = A[end]; // choosing pivot element
    int pIndex = start; // Index of first element
    
    for (int i=start; i<=end-1; i++)
    {
        /* If current element is smaller than or
        equal to pivot then exchange it with element
        at pIndex and increment the pIndex*/
        if (A[i] <= pivot)
        {
            // swap A[pIndex] and A[i]
            swap(pIndex,i); 
            pIndex=pIndex+1;
        }
    }
    // swap A[pIndex] and A[end] (or pivot)
    swap(pIndex,end);
    return pIndex;
}

/* The main function that implements QuickSort
arr[] --> array to be sorted,
start  --> Starting index,
end  --> Ending index */
void sort(int arr[], int start, int end)
{
   this.A=arr;
    if (start < end)
    {
        /* pi is partitioning index, A[pi] is 
          now at right place */
        int p = partition(start, end);
        printArray(A);
        // Recursively sort elements left of pivot
        // and elements right of pivot
        sort(A, start, p-1);
        sort(A, p+1, end);
    }
}
void printArray(int[] ar) {
	for (int i = 0; i < ar.length; i++) {
		System.out.print(ar[i] + " ");
	}
	System.out.println("");
}
//function two swap two numbers.
//we will pass index of array to swap
private void swap(int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}

//Driver program to test above functions
public static void main(String args[])
{
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int[] ar = new int[n];
	for (int i = 0; i < n; i++) {
		ar[i] = in.nextInt();
	}
	
  //print unsorted array using Arrays.toString()
   // System.out.print("Unsorted array: ");
    //System.out.println(Arrays.toString(arr));
     
    QuickSort2 ob = new QuickSort2();
    ob.sort(ar, 0, n-1);

   // System.out.print("Sorted array: ");
  //print sorted array
    //System.out.println(Arrays.toString(arr)); 
}
}
