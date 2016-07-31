package com.algo.problems;

import java.util.Arrays;
import java.util.Scanner;

/*Challenge 
Given a list of unsorted integers, , can you find the pair of elements that have the smallest absolute difference between them? If there are multiple pairs, find them all.

Input Format 
The first line of input contains a single integer, , representing the length of array . 
In the second line, there are  space-separated integers, , representing the elements of array .

Output Format 
Output the pairs of elements with the smallest difference. If there are multiple pairs, output all of them in ascending order, all on the same line (consecutively) with just a single space between each pair of numbers. If there's a number which lies in two pair, print it two times (see the sample case #3 for explanation).
Sample Input #1

10
-20 -3916237 -357920 -3620601 7374819 -7330761 30 6246457 -6461594 266854 
Sample Output #1

-20 30
Explanation 
(30) - (-20) = 50, which is the smallest difference.

Sample Input #2

12
-20 -3916237 -357920 -3620601 7374819 -7330761 30 6246457 -6461594 266854 -520 -470 
Sample Output #2

-520 -470 -20 30
Explanation 
(-470) - (-520) = 30 - (-20) = 50, which is the smallest difference.

Sample Input #3

4
5 4 3 2
Sample Output #3

2 3 3 4 4 5
Explanation 
Here, the minimum difference will be 1. So valid pairs are (2, 3), (3, 4), and (4, 5). So we have to print 2 once, 3 and 4 twice each, and 5 once.
 * */
public class ClosestNumbers {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			int arraySize = scan.nextInt();
			int[] pairArray = new int[arraySize];
			int difference = Integer.MAX_VALUE;

			// populate the array
			for (int i = 0; i < arraySize; i++) {
				pairArray[i] = scan.nextInt();
			}

			// sort the array
			Arrays.sort(pairArray);

			// find the lowest difference
			for (int i = 0; i < arraySize; i++) {
				if (i == (arraySize - 1))
					break;
				if (Math.abs(pairArray[i] - pairArray[i + 1]) < difference)
					difference = Math.abs(pairArray[i] - pairArray[i + 1]);
			}

			// print the pairs with the difference calculated above
			for (int i = 0; i < arraySize; i++) {
				if (i == (arraySize - 1))
					break;
				if (Math.abs(pairArray[i] - pairArray[i + 1]) == difference)
					System.out.print(pairArray[i] + " " + pairArray[i + 1] + " ");
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
