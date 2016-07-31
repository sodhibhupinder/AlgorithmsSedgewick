package com.algo.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BuildPalindrome2 {

	public static void main(String[] args) throws IOException {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int q = Integer.valueOf(in.readLine());
		for (int i = 0; i < q; i++) {
			String s =  in.readLine();
			String s1 = in.readLine();
			Set<String> lis1 = getAllUniqueSubset(s);
			Set<String> lis2;
			if (!s.equals(s1)) {
			 lis2 = getAllUniqueSubset(s1);
			} else {
			lis2 = lis1;
			 }
			buildPalindrome2(lis1, lis2);
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
	
	private static void buildPalindrome2(Set<String> lis1, Set<String> lis2) {
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
		findPrefix(lis1, lis2, queue);
		findPrefix(lis2, lis1, queue);
		if (!queue.isEmpty())
			System.out.println(queue.peek());
		else
			System.out.println(-1);
	}

	private static void findPrefix(Set<String> lis1, Set<String> lis2, Queue<String> queue) {
		Trie dict = new Trie();
		for (String str : lis1) {
			dict.insert(str);
		}
		for (String str2 : lis2) {
			String st = dict.getMatchingPrefix(reverse(str2));
			if (!st.isEmpty() && isPalindrome(st+str2))
					queue.add(st+str2);
			String st1 = dict.getMatchingPrefix(str2);
			if (!st1.isEmpty() && isPalindrome(st1+str2))
					queue.add(st1+str2);
		}
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
	
	static class TrieNode {
	    public TrieNode(char ch)  {
	        value = ch;
	        children = new HashMap<>();
	        bIsEnd = false;
	    }
	    public HashMap<Character,TrieNode> getChildren() {   return children;  }
	    public char getValue()                           {   return value;     }
	    public void setIsEnd(boolean val)                {   bIsEnd = val;     }
	    public boolean isEnd()                           {   return bIsEnd;    }
	 
	    private char value;
	    private HashMap<Character,TrieNode> children;
	    private boolean bIsEnd;
	}
	 
	// Implements the actual Trie
	static class Trie {
	    // Constructor
	    public Trie()   {     root = new TrieNode((char)0);       }    
	 
	    // Method to insert a new word to Trie
	    public void insert(String word)  {
	 
	        // Find length of the given word
	        int length = word.length();
	        TrieNode crawl = root;
	 
	        // Traverse through all characters of given word
	        for( int level = 0; level < length; level++)
	        {
	            HashMap<Character,TrieNode> child = crawl.getChildren();
	            char ch = word.charAt(level);
	 
	            // If there is already a child for current character of given word
	            if( child.containsKey(ch))
	                crawl = child.get(ch);
	            else   // Else create a child
	            {
	                TrieNode temp = new TrieNode(ch);
	                child.put( ch, temp );
	                crawl = temp;
	            }
	        }
	 
	        // Set bIsEnd true for last character
	        crawl.setIsEnd(true);
	    }
	 
	    // The main method that finds out the longest string 'input'
	    public String getMatchingPrefix(String input)  {
	        String result = ""; // Initialize resultant string
	        int length = input.length();  // Find length of the input string       
	 
	        // Initialize reference to traverse through Trie
	        TrieNode crawl = root;   
	 
	        // Iterate through all characters of input string 'str' and traverse
	        // down the Trie
	        int level, prevMatch = 0;
	        for( level = 0 ; level < length; level++ )
	        {
	            // Find current character of str
	            char ch = input.charAt(level);    
	 
	            // HashMap of current Trie node to traverse down
	            HashMap<Character,TrieNode> child = crawl.getChildren();                        
	 
	            // See if there is a Trie edge for the current character
	            if( child.containsKey(ch) )
	            {
	               result += ch;          //Update result
	               crawl = child.get(ch); //Update crawl to move down in Trie
	 
	               // If this is end of a word, then update prevMatch
	               if( crawl.isEnd() )
	                    prevMatch = level + 1;
	            }
	            else  break;
	        }
	 
	        // If the last processed character did not match end of a word,
	        // return the previously matching prefix
	        if( !crawl.isEnd() )
	                return result.substring(0, prevMatch);        
	 
	        else return result;
	    }
	 
	    private TrieNode root;
	}
	
	static String reverse(String str) {
	    char[] c = str.toCharArray();
	    char[] r = new char[c.length];
	    int    end = c.length - 1;

	    for (int n = 0; n <= end; n++) {
	        r[n] = c[end - n];
	    }

	    return new String(r);
	}
	
}

