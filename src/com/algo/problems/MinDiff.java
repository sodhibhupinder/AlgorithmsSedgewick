package com.algo.problems;

import java.util.Scanner;

public class MinDiff {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int A[] = new int[n];
        for(int A_i=0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        int diff = minDifference(A);
        System.out.println(diff);
    }
    
    public static int minDifference(int[] A) {
    
    if (A.length > 1) {
        int d = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
        	for(int j = 0; j < A.length; j++)
            if (i!=j && A[i]==A[j]) {
                  d =Math.min(d, Math.abs(i - j ));
            }
        }
        if(d!=Integer.MAX_VALUE)
        return d;
        else
        return -1;
    }
    return -1;
}
}
