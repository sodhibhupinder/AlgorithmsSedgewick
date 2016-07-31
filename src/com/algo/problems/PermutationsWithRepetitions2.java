package com.algo.problems;

public class PermutationsWithRepetitions2 {

	public static void main (String[] args) {
		//code
		String str = "12";
		char[] input = str.toCharArray();
		char[] output = input.clone();
		printPermute(output, input, 0);
		
	}
	
	private static void printPermute(char[] output,char[] input, int index){
	    
	    if(index == input.length){
	        System.out.println(output);
	        return;
	    }
	    
	    for(int i=0; i<input.length; i++){
	        output[index] = input[i];
	        printPermute(output, input, index+1);
	    }
	    
	}
}
