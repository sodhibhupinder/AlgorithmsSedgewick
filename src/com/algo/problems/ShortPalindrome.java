package com.algo.problems;

import java.util.Arrays;
import java.util.Collections;
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
      //  combinationUtil(characters, data, 0, n-1, 0, r);
        manacher(s.length(), s);
		//System.out.println(count % 1000000007);
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
	
	static void manacher(int N,String s)
	{
		int i, j, k, rp; // iterators & length of 'palindrome radius'
		int[][] R = new int[2][N + 1]; // table for storing results (2 rows for
										// odd- and even-length palindromes

		// print s first

		// ...then search for palindromes

		s = "@" + s + "#"; // insert 'guards' to iterate easily over s

		for (j = 0; j <= 1; j++) {
			R[j][0] = rp = 0;
			i = 1;
			while (i <= N) {
				while (s.charAt(i - rp - 1) == s.charAt(i + j + rp))
					rp++;
				R[j][i] = rp;
				k = 1;
				while ((R[j][i - k] != rp - k) && (k < rp)) {
					R[j][i + k] = Math.min(R[j][i - k], rp - k);
					k++;
				}
				rp = Math.max(rp - k, 0);
				i += k;
			}
		}
		System.out.println(Arrays.deepToString(R));
		s = s.substring(1, N); // remove 'guards'
		 for(i = 1; i <= N; i++)
		  {
		    for(j = 0; j <= 1; j++)
		      for(rp = R[j][i]; rp > 0; rp--)
		      {
		        for(k = 1; k < i - rp; k++) System.out.println(" ");
		        System.out.println(s.substring(i - rp - 1,2 * rp + j));
		      }
		  }
	}

}
