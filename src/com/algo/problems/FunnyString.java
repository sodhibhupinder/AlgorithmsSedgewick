package com.algo.problems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*Suppose you have a String, , of length  that is indexed from  to . You also have some String, , that is the reverse of String .  is funny if the condition  is true for every character from  to .

Note: For some String ,  denotes the ASCII value of the  -indexed character in . The absolute value of an integer, , is written as .

Input Format

The first line contains an integer,  (the number of test cases). 
Each line  of the  subsequent lines contain a string, .

Constraints

Output Format

For each String  (where ), print whether it is  or  on a new line.

Sample Input

2
acxz
bcxz
Sample Output

Funny
Not Funny
Explanation

Test Case 0:  
 
 
 
As each comparison is equal, we print Funny .

Test Case 1:  
, but  
At this point, we stop evaluating  (as ) and print Not Funny.
 * */
public class FunnyString {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int i = 0; i < t; i++) {
			isFunny(scan.next());
		}
	}

	private static void isFunny(String next) {
		char[] charArr = next.toCharArray();
		int size = charArr.length;
		int count=0;
		for(int i=1,j=size-1;i<size && j>0;i++,j--)
		{
			if(Math.abs(charArr[i]-charArr[i-1])==Math.abs(charArr[j]-charArr[j-1]))
			{
				count++;
			}
			else
			{
				i=size;				
			}
		}
		if(count==size-1)
		{
			System.out.println("Funny");
		}
		else
		{
			System.out.println("Not Funny");
		}
		
	}

	

}
