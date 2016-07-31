package com.algo.problems;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class JesseAndCookies {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		Queue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			pq.add(in.nextInt());
		}
		int count = 0;

		while (pq.peek() < k && pq.size() != 1) {
			int cookie1 = pq.peek();
			pq.poll();
			int cookie2 = pq.peek();
			pq.poll();
			int res_cookie = cookie1 + 2 * cookie2;
			pq.add(res_cookie);
			count++;
		}
		if (pq.peek() >= k)
			System.out.println(count);
		else
			System.out.println(-1);
	}
}

