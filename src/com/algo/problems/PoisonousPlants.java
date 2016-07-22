package com.algo.problems;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author baddie
 * There are  plants in a garden. Each of these plants has been added with some amount of pesticide. After each day, if any plant has more pesticide than the plant at its left, being weaker than the left one, it dies. You are given the initial values of the pesticide in each plant. Print the number of days after which no plant dies, i.e. the time after which there are no plants with more pesticide content than the plant to their left.

Input Format

The input consists of an integer . The next line consists of  integers describing the array  where  denotes the amount of pesticide in plant .

Constraints 
 

Output Format

Output a single value equal to the number of days after which no plants die.

Sample Input

7
6 5 8 4 7 10 9
Sample Output

2
Explanation

Initially all plants are alive. 
Plants = {(6,1), (5,2), (8,3), (4,4), (7,5), (10,6), (9,7)} 
Plants[k] = (i,j) => jth plant has pesticide amount = i. 
After the 1st day, 4 plants remain as plants 3, 5, and 6 die. 
Plants = {(6,1), (5,2), (4,4), (9,7)} 
After the 2nd day, 3 plants survive as plant 7 dies. Plants = {(6,1), (5,2), (4,4)} 
After the 3rd day, 3 plants survive and no more plants die. 
Plants = {(6,1), (5,2), (4,4)} 
After the 2nd day the plants stop dying.
 *
 */
public class PoisonousPlants {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[] ar = new int[sc.nextInt()];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = sc.nextInt();
		}
		int min = ar[0];
		int max = 0;
		int[] days = new int[ar.length];
		Stack<Integer> st = new Stack<Integer>();
		st.push(0);
		int n = ar.length;
		for (int i = 1; i < n; i++) {

			if (ar[i] > ar[i - 1])
				days[i] = 1;

			min = min < ar[i] ? min : ar[i];

			while (!st.empty() && ar[st.peek()] >= ar[i]) {
				if (ar[i] > min)
					days[i] = days[i] > days[st.peek()] + 1 ? days[i] : days[st.peek()] + 1;
				st.pop();
			}
			max = max > days[i] ? max : days[i];
			st.push(i);
		}
		System.out.println(max);
	}

}
