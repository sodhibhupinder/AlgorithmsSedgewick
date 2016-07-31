package com.algo.problems;

import java.util.Scanner;

public class NextPermuation {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
		 String str = in.next();	
			char[] array = str.toCharArray();
			if(nextPermutation(array))
			{
		    System.out.println(new String(array));
			}
			else
			{
				System.out.println("no answer");
			}
		}

	}
	static boolean nextPermutation(char[] array) {
	    // Find longest non-increasing suffix
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    // Now i is the head index of the suffix
	    
	    // Are we at the last permutation already?
	    if (i <= 0)
	        return false;
	    
	    // Let array[i - 1] be the pivot
	    // Find rightmost element that exceeds the pivot
	    int j = array.length - 1;
	    while (array[j] <= array[i - 1])
	        j--;
	    // Now the value array[j] will become the new pivot
	    // Assertion: j >= i
	    
	    // Swap the pivot with j
	    int temp = (int)(array[i - 1]);
	    array[i - 1] = array[j];
	    array[j] = (char) temp;
	    
	    // Reverse the suffix
	    j = array.length - 1;
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = (char) temp;
	        i++;
	        j--;
	    }
	    // Successfully computed the next permutation
	    return true;
	}
}
