package com.algo.sort.problems;

import java.util.Scanner;

public class CountingSort1 {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			int[] arr = new int[100];
			for (int i = 0; i < T; i++) {
				int num = in.nextInt();
				arr[num]+=1; 
			}
			for (int i :arr) {
				System.out.print(i+" ");
			}
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
