package com.algo.problems;

import java.util.Scanner;

class Difference {
	public Difference(int[] a) {
		this.elements = a;
	}
	private int[] elements;
	public int maximumDifference;
	public void computeDifference() {
		 for (int i = 0; i < elements.length; i++) {
	        	for(int j = 0; j < elements.length; j++)
	            if (elements[i]!=elements[j]) {
	            	maximumDifference =Math.max(maximumDifference, Math.abs(elements[i] - elements[j] ));
	            }
	        }
	}
}

public class MaxDifference {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();

		Difference difference = new Difference(a);

		difference.computeDifference();

		System.out.print(difference.maximumDifference);
	}
}
