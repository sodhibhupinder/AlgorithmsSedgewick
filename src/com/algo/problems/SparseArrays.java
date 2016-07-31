package com.algo.problems;

import java.util.Scanner;

public class SparseArrays {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			String[] NStrArray = new String[N]; 
			for (int i = 0; i < N; i++) {
				String str = in.next();
				NStrArray[i]=str;
			}
			int Q = in.nextInt();
			String[] qStrArray = new String[Q]; 
			for (int i = 0; i < Q; i++) {
				String str = in.next();
				qStrArray[i]=str;
			}
			for(String qStr:qStrArray)
			{
				int count=0;
				for(String nStr:NStrArray)
				{
					if(qStr.equalsIgnoreCase(nStr)) count++;
				}
				System.out.println(count);
			}
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
}
