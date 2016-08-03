package com.algo.math.problems;

import java.util.Scanner;

/**
 * @author baddie
 *
 */
public class IsFibo {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		long t = in.nextInt();
		for (long i = 0; i < t; i++) {
			long N = in.nextLong();
			boolean isFibo = false;
			for(long j=0;j<1000;j++)
			{
				long result = (long) ((Math.pow((1 + Math.sqrt(5)), j) - Math.pow((1 - Math.sqrt(5)), j))
						/ ((Math.pow(2, j)) * (Math.sqrt(5))));
				if (result == N) {
					System.out.println("IsFibo");
					j = N;
					isFibo = true;
				}
			}
			if(!isFibo)
			{
				System.out.println("IsNotFibo");
			}
			
		}

	}

}
