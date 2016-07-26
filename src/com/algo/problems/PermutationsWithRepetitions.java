package com.algo.problems;

import java.util.HashSet;
import java.util.Set;

public class PermutationsWithRepetitions {

	public static void main(String[] args) {
		int[] input = { 1, 2 };
		int lengthOfSinglePermutation = 4;

		// we need to check number of unique values in array
		Set<Integer> arrValues = new HashSet<Integer>();
		for (int i : input) {
			arrValues.add(i);
		}
		int noOfUniqueValues = arrValues.size();

		int[] indexes = new int[lengthOfSinglePermutation];
		int totalPermutations = (int) Math.pow(noOfUniqueValues, lengthOfSinglePermutation);

		for (int i = 0; i < totalPermutations; i++) {

			for (int j = 0; j < lengthOfSinglePermutation; j++) {
				System.out.print(input[indexes[j]]);
			}
			System.out.println();

			for (int j = 0; j < lengthOfSinglePermutation; j++) {
				if (indexes[j] >= noOfUniqueValues - 1) {
					indexes[j] = 0;
				} else {
					indexes[j]++;
					break;
				}
			}
		}
	}
}
