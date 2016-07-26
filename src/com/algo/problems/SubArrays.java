package com.algo.problems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SubArrays {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		subArray(s.toCharArray(), s.length());
		System.out.println(combinations(s.toCharArray()));
		DistinctSubString(s);
		System.out.println(getAllUniqueSubset(s));
	}

	static void subArray(char arr[], int n) {
		// Pick starting point
		for (int i = 0; i < n; i++) {
			// Pick ending point
			for (int j = i; j < n; j++) {
				// Print subarray between current starting
				// and ending points
				for (int k = i; k <= j; k++)
					System.out.print(arr[k] + " ");
				System.out.println();
			}
		}
	}

	public static List<List<Character>> combinations(char[] ds) {
		List<List<Character>> c = new ArrayList<List<Character>>();
		List<Character> l;
		for (int i = 0; i < ds.length; i++) {
			int k = c.size();
			for (int j = 0; j < k; j++) {
				l = new ArrayList<Character>(c.get(j));
				l.add(new Character(ds[i]));
				c.add(l);
			}
			l = new ArrayList<Character>();
			l.add(new Character(ds[i]));
			c.add(l);
		}
		return c;
	}

	public static void DistinctSubString(String string) throws IOException {
		int length = string.length();
		String[] arrayString = new String[length];
		for (int i = 0; i < length; ++i) {
			arrayString[i] = string.substring(length - 1 - i, length);
		}

		Arrays.sort(arrayString);
		for (int i = 0; i < length; ++i)
			System.out.println(arrayString[i]);

		long num_substring = arrayString[0].length();

		for (int i = 0; i < length - 1; ++i) {
			int j = 0;
			for (; j < arrayString[i].length(); ++j) {
				if (!((arrayString[i].substring(0, j + 1)).equals((arrayString)[i + 1].substring(0, j + 1)))) {
					break;
				}
			}
			num_substring += arrayString[i + 1].length() - j;
		}
		System.out.println("unique substrings = " + num_substring);
	}
	
	public static ArrayList<String> getAllUniqueSubset(String str) {
        ArrayList<String> set = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length() - i; j++) {
                String elem = str.substring(j, j + (i+1));
                if (!set.contains(elem)) {
                    set.add(elem);
                }
            }
        }
        return set;
    }
}
