package com.algo.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciModified {
	private static List<BigInteger> dict = new ArrayList<BigInteger>();
	public static void main(String[] args) {
	       
    	try(Scanner scan = new Scanner(System.in))
    	{
    	int t1 = scan.nextInt();
    	int t2 = scan.nextInt();
    	int n = scan.nextInt();
    	dict.add(BigInteger.valueOf(t1));
    	dict.add(BigInteger.valueOf(t2));
    	printFebonachiRecursion(n);
    	System.out.println(dict.get(n-1));
    	}catch (Exception e) {
    	throw e;
    	}
    }

	public static void printFebonachiRecursion(int num) {
		if (num <= 1) {
			//System.out.printf("Term %d: %d%n", 0, dict.get(0));
			//System.out.printf("Term %d: %d%n", num, dict.get(num));
			return;
		} else {
			printFebonachiRecursion(num - 1);
			dict.add(dict.get(num - 1).pow(2).add(dict.get(num - 2)));
			//System.out.printf("Term %d: %d%n", num, dict.get(num));
		}
	}
}
