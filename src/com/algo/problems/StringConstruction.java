package com.algo.problems;

import java.util.Scanner;

public class StringConstruction {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int a0 = 0; a0 < n; a0++) {
			String s = in.next();
			computeCharge1(s);
		}
	}

	private static void computeCharge(String s) {
		char[] strArr = s.toCharArray();
		String p = "";
		int cost = 0;
		for (char c : strArr) {
			if (p.indexOf(c) != -1)
				p += c;
			else {
				p += c;
				cost++;
			}
		}
		System.out.println(cost);
	}
	private static void computeCharge1(String s) {
	char characters[] = s.toCharArray();
    int countOfUniqueChars = s.length();
    for (int i = 0; i < characters.length; i++) {
        if (i != s.indexOf(characters[i])) {
            countOfUniqueChars--;
        }
    }
    System.out.println(countOfUniqueChars);
	}
}
