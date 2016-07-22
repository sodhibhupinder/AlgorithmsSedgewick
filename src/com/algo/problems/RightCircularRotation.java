package com.algo.problems;

import java.util.Scanner;

public class RightCircularRotation {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			int k = in.nextInt();
			int q = in.nextInt();
			StringBuilder sb = new StringBuilder(2 * q);
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				int num = in.nextInt();
				arr[i] = num;
			}
			rotate(arr, k);
			for (int i = 0; i < q; i++) {
				int num = in.nextInt();
				sb.append(arr[num] + "\n");
			}
			System.out.println(sb.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void rotate(int[] arr, int order) {
		if (arr == null || arr.length == 0 || order < 0) {
			throw new IllegalArgumentException("Illegal argument!");
		}

		if (order > arr.length) {
			order = order % arr.length;
		}

		// length of first part
		int a = arr.length - order;

		reverse(arr, 0, a - 1);
		reverse(arr, a, arr.length - 1);
		reverse(arr, 0, arr.length - 1);

	}

	public static void reverse(int[] arr, int left, int right) {
		if (arr == null || arr.length == 1)
			return;

		while (left < right) {
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}

}
