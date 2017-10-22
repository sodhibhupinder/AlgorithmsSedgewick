package com.algo.stack.problems;

import java.util.Scanner;


/**
 * @author baddie
 *
 *Step I. get the 3 array and reverse them, create a new array out of an existing array with each element is sum of all the previous elements. eg: [3,2,1,1,1] -> [1,1,1,2,3] -> [1,2,3,5,8]
So the 3 new array formed would be [1,2,3,5,8] [2,5,9] [1,5,6,7]
Step II. Again reverse the array [8,5,3,2,1] [9,5,2] [7,6,5,1]
Step III. Take the smallest array i.e. [9,5,2] traverse the smallest array and search element in the other 2 array - if the element is existing in other 2 array, STOP there and return the number.
Eg. Here I start with elem - 9 : Which is not existing in other 2 array. Next I start with elem - 5 : it is existing in other 2 array.
Wolla! 5 is my NUMBER!
 */
public class EqualStack {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	    int n1 = in.nextInt();
	    int n2 = in.nextInt();
	    int n3 = in.nextInt();
	    int[] numberOfCylinders = {n1, n2, n3};
	    int[][] stack = {new int[n1], new int[n2], new int[n3]};
	    int[] height = {0, 0, 0}; 
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < numberOfCylinders[i]; j++) {
	            int cylinderHeight = in.nextInt();
	            stack[i][j] = cylinderHeight;
	            height[i] += cylinderHeight;
	        }   
	    }
	    int[] index = {0, 0, 0};
	    int targetHeight = Math.min(Math.min(height[0], height[1]), height[2]);
	    while (height[0] != height[1] || height[1] != height[2]) {
	        for (int i = 0; i < 3; i++) {
	            if (height[i] > targetHeight) {
	                height[i] -= stack[i][index[i]++]; 
	                targetHeight = Math.min(targetHeight, height[i]);
	            }
	        }
	    }
	    System.out.println(targetHeight);
	    in.close();
    }
	
	
}

