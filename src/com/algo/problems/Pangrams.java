package com.algo.problems;

import java.util.Scanner;

public class Pangrams {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String pangramStr = in.nextLine();
			char[] pangramCharArr = pangramStr.toCharArray();
			for (int i = 0; i < pangramCharArr.length; i++) {

			}
			int[] alphabetCharCountArr = new int[26];
			for (int i = 0; i < alphabetCharCountArr.length; i++) {
				alphabetCharCountArr[i] = 0;
			}
			for (char nStr : pangramCharArr) {
				int lowerindex = nStr - 65;
				int upperindex = nStr - 97;
				if (lowerindex >= 0 && lowerindex <= 25)
				{
					alphabetCharCountArr[lowerindex] += 1;
				}
				if (upperindex >= 0 && upperindex <= 25)
				{
					alphabetCharCountArr[upperindex] += 1;
				}
			}
			boolean isPangram=true;
			for (int i = 0; i < alphabetCharCountArr.length; i++) {
				if(alphabetCharCountArr[i] == 0)
				isPangram=false;
			}
			if(!isPangram)
			System.out.println("not pangram");
			else
			System.out.println("pangram");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
