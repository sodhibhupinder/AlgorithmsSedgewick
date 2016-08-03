package com.algo.sort.problems;

import java.util.Scanner;

public class CountingSort4 {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			int[] arr = new int[100];
			int[] originalArr = new int[T];
			String[] originalStrArr = new String[T];
			StringBuilder strBuilder = new StringBuilder(12*T);
			for (int i = 0; i < T; i++) {
				int num = in.nextInt();
				originalArr[i]=num;
				String str = in.next();
				originalStrArr[i]=str;
				arr[num]+=1; 
			}
			for (int i=0;i<100;i++) {
					for(int k=0;k<T;k++)
					{
						if(i==originalArr[k] && k>=T/2)
						{
						strBuilder.append(originalStrArr[k]+" ");
						}
						else if(i==originalArr[k] && k<T/2)
						{
						strBuilder.append("- ");
						}
					}
			}
			System.out.println(strBuilder);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
