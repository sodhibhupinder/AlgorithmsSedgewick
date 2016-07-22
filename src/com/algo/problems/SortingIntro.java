package com.algo.problems;
import java.util.Scanner;
public class SortingIntro {
	


	    public static void main(String[] args) {
	       
	    	Scanner scan = new Scanner(System.in);
	    	int V = scan.nextInt();
	    	int size = scan.nextInt();
	    	int[] arr = new int[size];
	    	for(int i =0;i<size;i++)
	    	{
	    		arr[i]=scan.nextInt();
	    	}
	    	
	    	for(int i =0;i<size;i++)
	    	{
	    		if(arr[i]==V)
	    		{
	    		System.out.println(i);
	    		break;
	    		}
	    	}
	    	
	    	
	    }
}
