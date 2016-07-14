package com.algo.problems;

import java.util.Scanner;

public class ExceptionHandling {
	 public static void main(String[] args) {
	        try(Scanner in = new Scanner(System.in)){
	        String S = in.next();
	        Integer number = Integer.parseInt(S);
	        System.out.println(number);
	        }
	        catch (NumberFormatException e) {
	        	System.out.println("Bad String");
			}
	    }

}
