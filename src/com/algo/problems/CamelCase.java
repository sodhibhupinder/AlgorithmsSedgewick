package com.algo.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CamelCase {

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        String s = in.next();
	        char[] strArr = s.toCharArray();
	        int count=1;
	        for(char c:strArr)
	        {
	        	if(c>='A' && c<='Z')
	        	{
	        	 count++;
	        	}        	
	        		
	        }
	        System.out.println(count);
	    }
}
