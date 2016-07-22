package com.algo.problems;

import java.util.Scanner;

/*Shashank likes strings in which consecutive characters are different. For example, he likes ABABA, while he doesn't like ABAA. Given a string containing characters  and  only, he wants to change it into a string he likes. To do this, he is allowed to delete the characters in the string.

Your task is to find the minimum number of required deletions.

Input Format

The first line contains an integer , i.e. the number of test cases. 
The next  lines contain a string each.

Output Format

For each test case, print the minimum number of deletions required.

Constraints

 
 length of string 

Sample Input

5
AAAA
BBBBB
ABABABAB
BABABA
AAABBB
Sample Output

3
4
0
0
4
Explanation

AAAA  A, 3 deletions
BBBBB  B, 4 deletions
ABABABAB  ABABABAB, 0 deletions
BABABA  BABABA, 0 deletions
AAABBB  AB, 4 deletions because to convert it to AB we need to delete 2 A's and 2 B's
 * */

public class AlternatingCharacters {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int t = Integer.parseInt(in.nextLine());
			String[] input = new String[t];
			for (int i = 0; i < t; i++) {
				input[i] = in.nextLine();
			}
			for (int i = 0; i < t; i++) {
				System.out.println(countDeletions(input[i]));
			}
		} catch (Exception e) {
			throw e;
		}

	}
	 public static int countDeletions(String s) {
	        int count = 0, index=0;
	        for(int i=1;i<s.length();i++){
	            if(s.charAt(i) == s.charAt(index)) {
	                count++;
	            } else {
	                index = i;
	            }
	        }
	        return count;
	    }
}
