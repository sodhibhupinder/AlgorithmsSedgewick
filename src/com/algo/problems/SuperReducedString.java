package com.algo.problems;

import java.util.Scanner;
/*
Shil has a string, , consisting of  lowercase English letters. In one operation, he can delete any pair of adjacent letters with same value. For example, string "" would become either "" or "" after  operation.

Shil wants to reduce  as much as possible. To do this, he will repeat the above operation as many times as it can be performed. Help Shil out by finding and printing 's non-reducible form!

Note: If the final string is empty, print .

Input Format

A single string, .

Constraints

Output Format

If the final string is empty, print "Empty String"; otherwise, print the final non-reducible string.

Sample Input 0

aaabccddd
Sample Output 0

abd
Sample Input 1

baab
Sample Output 1

Empty String
Sample Input 2

aa
Sample Output 2

Empty String
 * 
 * */
public class SuperReducedString {

	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)){
		String str = scanner.next();
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(i - 1)) {
				str = str.substring(0, i - 1) + str.substring(i + 1);
				i = 0;
			}
		}
		if (str.length() == 0) {
			System.out.println("Empty String");
		} else {
			System.out.println(str);
		}
		}catch (Exception e) {
			throw e;
		}
	}
}
