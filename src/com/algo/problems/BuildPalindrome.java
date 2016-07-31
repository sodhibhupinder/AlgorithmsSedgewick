package com.algo.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BuildPalindrome {

	public static void main(String[] args) throws IOException {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int q = Integer.valueOf(in.readLine());
		for (int i = 0; i < q; i++) {
			String s =  in.readLine();
			String s1 = in.readLine();
//			Set<String> lis1 = getAllUniqueSubset(s);
//			Set<String> lis2;
//			if (!s.equals(s1)) {
//			 lis2 = getAllUniqueSubset(s1);
//			} else {
//			lis2 = lis1;
//			 }
			Set<String> lis1 =getAllUniqueSubset2(s+s1);
			buildPalindrome2(lis1);
		}
	}

	private static void buildPalindrome(Set<String> lis1, Set<String> lis2) {
		Queue<String> queue = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o2.length() > o1.length()) {
					return 1;
				} else if (o2.length() < o1.length()) {
					return -1;
				}
				return o1.compareTo(o2);
			}
		});
		for (String str : lis1) {
			for (String str2 : lis2) {
				String st = str + str2;
				if (isPalindrome(st))
					queue.add(st);
			}
		}
		if (!queue.isEmpty())
			System.out.println(queue.peek());
		else
			System.out.println(-1);
	}
	private static void buildPalindrome2(Set<String> lis1) {
		Queue<String> queue = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o2.length() > o1.length()) {
					return 1;
				} else if (o2.length() < o1.length()) {
					return -1;
				}
				return o1.compareTo(o2);
			}
		});
		for (String str : lis1) {
				if (isPalindrome(str))
					queue.add(str);
		}
		if (!queue.isEmpty())
			System.out.println(queue.peek());
		else
			System.out.println(-1);
	}

	public static Set<String> getAllUniqueSubset(String str) {
		Set<String> set = new HashSet<String>(str.length() * 2);
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length() - i; j++) {
				String elem = str.substring(j, j + (i + 1));
				if (!set.contains(elem))
					set.add(elem);
			}
		}
		return set;
	}
	
	public static Set<String> getAllUniqueSubset3(String str) {
		Set<String> set = new HashSet<String>(str.length() * 2);
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length() - i; j++) {
				String elem = str.substring(j, j + (i + 1));
				if (!set.contains(elem))
					set.add(elem);
			}
		}
		return set;
	}
	
	public static Set<String> getAllUniqueSubset2(String str) {
		Set<String> set = new HashSet<String>(str.length() * 2);
		int length = str.length(); 
		for(int c = 0 ; c < length ; c++ )
	      {
	         for(int i = 1 ; i <= length - c ; i++ )
	         {
	            String sub = str.substring(c, c+i);
	            if (!set.contains(sub))
					set.add(sub);
	         }
	      }
		return set;
	}

	static boolean isPalindrome(String inputString) {
		int length = inputString.length();
		int i, begin, end, middle;

		begin = 0;
		end = length - 1;
		middle = (begin + end) / 2;

		for (i = begin; i <= middle; i++) {
			if (inputString.charAt(begin) == inputString.charAt(end)) {
				begin++;
				end--;
			} else {
				break;
			}
		}
		if (i == middle + 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
