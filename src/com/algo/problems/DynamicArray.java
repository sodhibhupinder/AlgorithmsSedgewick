package com.algo.problems;

import java.util.LinkedList;
import java.util.Scanner;

public class DynamicArray {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			LinkedList[] seqList = new LinkedList[N];
			for(int i =0;i<N;i++)
			{
				seqList[i] = new LinkedList<>();
			}
			int lastAns=0;
			int Q = in.nextInt();
			for (int i = 0; i < Q; i++) {
				int queryType = in.nextInt();
				if(queryType==1)
				processQueryType1(in,seqList,lastAns,N);
				if(queryType==2)
				lastAns = processQueryType2(in,seqList,lastAns,N);
			}
			
		} catch (Exception ex) {
				ex.printStackTrace();
		}
	}

	private static void processQueryType1(Scanner in, LinkedList[] seqList, int lastAns,int N) {
		int x = in.nextInt();
		int y = in.nextInt();
		seqList[(x^lastAns)%N].add(y);
	}

	private static int processQueryType2(Scanner in, LinkedList[] seqList, int lastAns,int N) {
		int x = in.nextInt();
		int y = in.nextInt();
		LinkedList seq=seqList[(x^lastAns)%N];
		int lastAnsindex = y%seq.size();
		lastAns = (int) seq.get(lastAnsindex);
		System.out.println(lastAns);
		return lastAns;
	}
}
