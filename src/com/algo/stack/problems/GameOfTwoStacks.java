package com.algo.stack.problems;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class GameOfTwoStacks {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        long count;
        for (int a0 = 0; a0 < g; a0++) {
            count = 0;
            Deque<Integer> stack1 = new LinkedList<>();
            Deque<Integer> stack2 = new LinkedList<>();
            int n = in.nextInt();
            int m = in.nextInt();
            int x = in.nextInt();

            int[] a = new int[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                stack1.push(a[n - 1 - i]);
            }

            int[] b = new int[m];
            for (int b_i = 0; b_i < m; b_i++) {
                b[b_i] = in.nextInt();
            }
            for (int i = 0; i < m; i++) {
                stack2.push(b[m - 1 - i]);
            }
   
            if (stack1.peek() > x && stack2.peek() > x) {
                System.out.println(count);
                continue;
            }

            long sum = 0;
            int i = 0;
            int j = 0;

            try {
             while (!stack1.isEmpty() && sum + stack1.peek()<=x){
                    sum = sum + stack1.pop();
                    i++;
                }
                count = i;

                while (j < m && i >= 0) {

                    sum = sum + stack2.pop();
                    j++;
                    while (sum>x && i>0){
                        sum = sum - a[i-1];
                        i--;
                    }
                    if(sum<=x && i+j>count) {
                        count = i + j;
                    }
                }
                System.out.println(count);
            }catch (Exception e){
                System.out.println(count);
            }
        }
        in.close();
    }
}
