package com.algo.problems;

import java.util.Scanner;

public class ShortPalindrome {

	private static int count;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		computeTuples(s);
	}

	private static void computeTuples(String s) {
		char characters[] = s.toCharArray();
		//List<List<Character>> allArrays = combinations(characters);
		// A temporary array to store all combination one by one
        char data[]=new char[4];
        int r = 4;
        int n = characters.length;
        // Print all combination using temprary array 'data[]'
        combinationUtil(characters, data, 0, n-1, 0, r);
		System.out.println(count % 1000000007);
	}

	static boolean isPalindrome(char[] data) {
		int length = data.length;
		int i, begin, end, middle;

		begin = 0;
		end = length - 1;
		middle = (begin + end) / 2;

		for (i = begin; i <= middle; i++) {
			if (data[begin]==data[end]) {
				begin++;
				end--;
			} else {
				break;
			}
		}
		if (i == middle + 1) {
			return true;
		} else {
			return false;
		}
	}

//	public static List<List<Character>> combinations(char[] ds) {
//		List<List<Character>> c = new ArrayList<List<Character>>();
//		List<Character> l;
//		for (int i = 0; i < ds.length; i++) {
//			int k = c.size();
//			for (int j = 0; j < k; j++) {
//				l = new ArrayList<Character>(c.get(j));
//				l.add(new Character(ds[i]));
//				if (l.size() <= 4) {
//					c.add(l);
//				}
//			}
//			l = new ArrayList<Character>();
//			l.add(new Character(ds[i]));
//			c.add(l);
//		}
//		return c;
//	}

	static void combinationUtil(char arr[], char data[], int start, int end, int index, int r) {
		// Current combination is ready to be printed, print it
		if (index == r) {
			if(isPalindrome(data)) count++;
			return;
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data[index] = arr[i];
			combinationUtil(arr, data, i + 1, end, index + 1, r);
		}
	}

}
