package com.algo.problems;

import java.util.HashSet;
import java.util.Scanner;

public class LongestPalindrome {
	public static HashSet<String> h = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        for(int i=0;i<=s.length()/2;i++)
            h.add(s.charAt(i)+"");
        longestPalindrome(s.substring(0, (s.length()/2)+(s.length()%2)));
        System.out.println(h.size()+s.length()/2);
        System.out.print(h);
    }

    public static void longestPalindrome(String s){
        //System.out.println(s);
        if(s.length()==0 || s.length()==1)
            return;
        if(checkPalindrome(s)){
            h.add(s);
        }
        longestPalindrome(s.substring(0, s.length()-1));
        longestPalindrome(s.substring(1, s.length()));

    }
    public static boolean checkPalindrome(String s){
        //System.out.println(s);
        int i=0;int j=s.length()-1;
        while(i<=j){
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;j--;
        }
        return true;
    }
}
