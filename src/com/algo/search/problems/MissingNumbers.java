package com.algo.search.problems;

import java.util.Scanner;

public class MissingNumbers {

	public static void main(String[] args) {

		try (Scanner in = new Scanner(System.in)) {
			int A = in.nextInt();
			int[] arrA = new int[100];
			int pivot = in.nextInt();
			for (int i = 0; i < A; i++) {
				int num = in.nextInt();
				arrA[num%pivot]+=1; 
			}
			int B = in.nextInt();
			int[] arrB = new int[101];
			for (int i=0;i<B;i++) {
				for(int j=arrB[i];j>0;j--)
				System.out.print(i+" ");
			}
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}

}
