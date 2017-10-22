package com.algo.problems.queue;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author baddie
 *
 */
public class CastleOnTheGrid {
	static HashMap<Point, Integer> stepMap = new HashMap<>();
	static Queue<Point> queue = new ArrayDeque<>();
	static char[][] grid;
	static int step = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		grid = new char[n][n];
		for (int i = 0; i < n; i++)
			grid[i] = scan.next().toCharArray();

		Point startPoint = new Point(scan.nextInt(), scan.nextInt()),
				endPoint = new Point(scan.nextInt(), scan.nextInt());

		stepMap.put(startPoint, step);
		queue.add(startPoint);
		while (!queue.isEmpty() && !stepMap.containsKey(endPoint)) {
			Point current = queue.poll();
			step = stepMap.get(current) + 1;
			int x = current.x;
			int y = current.y;
			for (int i = x + 1; i < n && addPoint(i, y); i++)
				;
			for (int i = x - 1; i > -1 && addPoint(i, y); i--)
				;
			for (int i = y + 1; i < n && addPoint(x, i); i++)
				;
			for (int i = y - 1; i > -1 && addPoint(x, i); i--)
				;
		}
		System.out.println(stepMap.get(endPoint));
		scan.close();
	}

	public static boolean addPoint(int x, int y) {
		if (grid[x][y] != '.')
			return false;
		Point newOne = new Point(x, y);
		if (!stepMap.containsKey(newOne)) {
			stepMap.put(newOne, step);
			queue.add(newOne);
		}
		return true;
	}
}
