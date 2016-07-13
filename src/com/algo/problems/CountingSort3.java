package com.algo.problems;

import java.util.Scanner;

public class CountingSort3 {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			int[] arr = new int[100];
			int[] originalArr = new int[T];
			for (int i = 0; i < T; i++) {
				int num = in.nextInt();
				originalArr[i]=num;
				String str = in.next();
				arr[num]+=1; 
			}
			for (int i=0;i<100;i++) {
				for(int j=arr[i];j>0;j--)
				{
					
				}
			}
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
