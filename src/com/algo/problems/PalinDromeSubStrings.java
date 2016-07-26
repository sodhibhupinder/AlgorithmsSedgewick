package com.algo.problems;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PalinDromeSubStrings {

	public static void isPalindrome(String s) {

		Set<String> set = new HashSet<String>();

		for (int j = 0; j < s.length(); j++) {
			set.add(s.substring(j, j + 1));
			int start = j - 1;
			int end = j + 1;
			while (start >= 0 && end <= s.length() - 1) {

				if (s.charAt(start) == s.charAt(j)) {

					set.add(s.substring(start, j + 1));

				}

				if (s.charAt(start) == s.charAt(end)) {

					set.add(s.substring(start, end + 1));
				}

				else {

					break;

				}

				start--;

				end++;
			}

		}

		System.out.println("Total no. of palindromes: " + set.size());

		System.out.println(set);

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		isPalindrome(s);

	}

}
