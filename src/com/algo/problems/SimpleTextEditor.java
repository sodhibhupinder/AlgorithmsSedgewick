package com.algo.problems;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author baddie
 *In this challenge, you must implement a simple text editor. Initially, your editor contains an empty string, . You must perform  operations of the following  types:

append - Append string  to the end of .
delete - Delete the last  characters of .
print - Print the  character of .
undo - Undo the last (not previously undone) operation of type  or , reverting  to the state it was in prior to that operation.
Input Format

The first line contains an integer, , denoting the number of operations. 
Each line  of the  subsequent lines (where ) defines an operation to be performed. Each operation starts with a single integer,  (where ), denoting a type of operation as defined in the Problem Statement above. If the operation requires an argument,  is followed by its space-separated argument. For example, if  and , line  will be 1 abcd.

Constraints

The sum of the lengths of all  in the input .
All input characters are lowercase English letters.
It is guaranteed that the sequence of operations given as input is possible to perform.
Output Format

Each operation of type  must print the  character on a new line.

Sample Input

8
1 abc
3 3
2 3
1 xy
3 2
4 
4 
3 1
Sample Output

c
y
a
Explanation

Initially,  is empty. The following sequence of  operations are described below:

. We append  to , so .
Print the  character on a new line. Currently, the  character is c.
Delete the last  characters in  (), so .
Append  to , so .
Print the  character on a new line. Currently, the  character is y.
Undo the last update to , making  empty again (i.e., ).
Undo the next to last update to  (the deletion of the last  characters), making .
Print the  character on a new line. Currently, the  character is a.
 */
public class SimpleTextEditor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		try(Scanner scanner = new Scanner(System.in)){
		int n = scanner.nextInt();
		Deque<String> st = new LinkedList<>();
		String s = "";
		for (int i = 0; i < n; i++) {
			int op = scanner.nextInt();
			if (op == 1) {
				// String str = Integer.toString(scanner.nextInt());
				String str = scanner.next();
				scanner.nextLine();
				// System.out.println(str);
				s += str;
				st.push(str);
				st.push("append");
				// System.out.println(s);
			} else if (op == 2) {
				int k = scanner.nextInt();
				String str = s.substring(s.length() - k);
				st.push(str);
				st.push("delete");
				s = s.substring(0, s.length() - k);
				// System.out.println(k);
				// System.out.println(s);
			} else if (op == 3) {
				int k = scanner.nextInt();
				System.out.println(s.substring(k - 1, k));
			} else {
				String q = st.pop();
				if (q.equals("append")) {
					String str = st.pop();
					int l = str.length();
					s = s.substring(0, s.length() - l);
				} else if (q.equals("delete")) {
					String str = st.pop();
					s += str;
					// System.out.println(s);
				}
			}
		}
		}catch (Exception e) {
			throw e;
		}
	}

}
