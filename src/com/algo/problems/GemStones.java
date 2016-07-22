package com.algo.problems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*John has discovered various rocks. Each rock is composed of various elements, and each element is represented by a lower-case Latin letter from 'a' to 'z'. An element can be present multiple times in a rock. An element is called a gem-element if it occurs at least once in each of the rocks.

Given the list of  rocks with their compositions, display the number of gem-elements that exist in those rocks.

Input Format

The first line consists of an integer, , the number of rocks. 
Each of the next  lines contains a rock's composition. Each composition consists of lower-case letters of English alphabet.

Constraints 
 
Each composition consists of only lower-case Latin letters ('a'-'z'). 
 length of each composition 

Output Format

Print the number of gem-elements that are common in these rocks. If there are none, print 0.

Sample Input

3
abcdde
baccd
eeabg
Sample Output

2
Explanation

Only "a" and "b" are the two kinds of gem-elements, since these are the only characters that occur in every rock's composition.
 * */
public class GemStones {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		Set<Character> set = convertToSet(scan.next());
		for (int i = 1; i < t; i++) {
			set.retainAll(convertToSet(scan.next()));
		}
		System.out.print(set.size());
	}

	public static Set<Character> convertToSet(String s) {
		Set<Character> set = new HashSet<Character>(35);
		for (char c : s.toCharArray())
			set.add(Character.valueOf(c));
		return set;
	}

}
