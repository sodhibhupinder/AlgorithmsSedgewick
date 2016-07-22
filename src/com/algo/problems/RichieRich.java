package com.algo.problems;

import java.util.Scanner;
/*Sandy likes palindromes. A palindrome is a word, phrase, number, or other sequence of characters which reads the same backward as it does forward. For example, madam is a palindrome.

On her  birthday, Sandy's uncle, Richie Rich, offered her an -digit check which she refused because the number was not a palindrome. Richie then challenged Sandy to make the number palindromic by changing no more than digits. Sandy can only change  digit at a time, and cannot add digits to (or remove digits from) the number.

Given  and an -digit number, help Sandy determine the largest possible number she can make by changing digits.

Note: Treat the integers as numeric strings. Leading zeros are permitted and can't be ignored (So 0011 is not a palindrome, 0110 is a valid palindrome). A digit can be modified more than once.

Input Format

The first line contains two space-separated integers,  (the number of digits in the number) and  (the maximum number of digits that can be altered), respectively. 
The second line contains an -digit string of numbers that Sandy must attempt to make palindromic.

Constraints

Each character  in the number is an integer where .
Output Format

Print a single line with the largest number that can be made by changing no more than  digits; if this is not possible, print -1.

Sample Input 0

4 1
3943
Sample Output 0

3993
Sample Input 1

6 3
092282
Sample Output 1

992299
Sample Input 2

4 1
0011
Sample Output 2

-1
Explanation

Sample 0

There are two ways to make  a palindrome by changing exactly  digits:

, so we print .
 * */
public class RichieRich {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int l = scan.nextInt();
		String numberStr = scan.next();
		char[] originalArr = numberStr.toCharArray();
		char[] arr = numberStr.toCharArray();
		int size= arr.length;
		int number = 0;
		int k=0;
		while(arr[k]=='0'){
		k++;
		}
		char value = arr[k]; 
		for(int j=0;j<k;j++)
		{
			arr[j]=value;
		}
		if(!isPalinDromeNumber(new String(arr)))
		{
		number = nextPalindrome(new String(arr));
		computeResults(n, l, originalArr, number);
		}
		else
		computeResults(n, l, originalArr, number);
	}

	private static void computeResults(int n, int l, char[] originalArr, int number) {
		String str=Integer.toString(number);
		char[] strArr = str.toCharArray();
		int count=0;
		for(int i=0;i<n;i++)
		{
			
			if(strArr[i]!=originalArr[i])
				count++;
		}
		if (isPalinDromeNumber(Integer.toString(number)) && count==l)
			System.out.println(number);
		else
			System.out.println("-1");
	}

	/**
	 * @param next
	 * @return
	 */
	private static boolean isPalinDromeNumber(String next) {
		StringBuilder sbr = new StringBuilder(next);
	    if ( next.equals(sbr.reverse().toString()) ) {
	       return true;
	    }
		return false;		
	}

	/**
	 * Mirror the number such that lower half digits same as higher half of digits
	 * For example, the mirror of 65432 is 65456
	 * 
	 * @param n
	 * @return
	 */
	static int mirror(String num)
	{
	    char[] arr = num.toCharArray();
	    int mid = arr.length/2;
	    int i= (arr.length%2==0)?mid:mid+1;
	    // copy higher half of digits to lower half
	    while(i<arr.length) {
	        arr[i]=arr[mid-1];
	        mid--;
	        i++;
	    }
	    num=String.valueOf(arr);
	    return Integer.valueOf(num);
	}
	 
	static String incrementFromMiddle(String num)
	{
	    char[] arr = num.toCharArray();
	    int midpoint = arr.length/2;
	    int currPoint=arr.length%2==0?midpoint-1:midpoint;
	    boolean found = false;
	      
	    while (currPoint >= 0 && !found) {
	        char c = arr[currPoint];
	        char inc;
	        if (c == '9') {
	          inc = '0';
	        }
	        else {
	          inc = (char) (c + 1);
	          found = true;
	        }
	        arr[currPoint] = inc;
	        if (!found) {
	          currPoint--;
	        }
	      }
	      
	      if (!found) {
	        // we have fallen off the start of the string
	        // example 999 has become 009. Add a one on to give: 1009
	        final char[] newarr = new char[arr.length + 1];
	        newarr[0] = '1';
	        System.arraycopy(arr, 0, newarr, 1, arr.length);
	        num = new String(newarr);
	        return num;
	      }
	      else {
	          num = new String(arr);
	          return num;            
	      }
	}
	// Give next palindrome from a given number which might not be palindrome
	static int nextPalindrome(String n)
	{
	    int mirror = mirror(n);
	    if(mirror <= Integer.parseInt(n)) {
	        mirror = mirror(incrementFromMiddle(n));        
	    }
	    return mirror;
	}
}
