package com.algo.tries.problems;

import java.util.Scanner;

/**
 * @author baddie Given strings. Each string contains only lowercase letters
 *         from (both inclusive). The set of strings is said to be GOOD SET if
 *         no string is prefix of another string else, it is BAD SET. (If two
 *         strings are identical, they are considered prefixes of each other.)
 * 
 *         For example, aab, abcde, aabcd is BAD SET because aab is prefix of
 *         aabcd.
 * 
 *         Print GOOD SET if it satisfies the problem requirement. Else, print
 *         BAD SET and the first string for which the condition fails.
 * 
 *         Input Format First line contains , the number of strings in the set.
 *         Then next lines follow, where line contains string.
 * 
 *         Constraints
 * 
 *         Length of the string
 * 
 *         Output Format Output GOOD SET if the set is valid. Else, output BAD
 *         SET followed by the first string for which the condition fails.
 * 
 *         Sample Input00
 * 
 *         7 aab defgab abcde aabcde cedaaa bbbbbbbbbb jabjjjad Sample Output00
 * 
 *         BAD SET aabcde Sample Input01
 * 
 *         4 aab aac aacghgh aabghgh Sample Output01
 * 
 *         BAD SET aacghgh Explanation aab is prefix of aabcde. So set is BAD
 *         SET and it fails at string aabcde.
 */
public class NoPrefixSet {
	public static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public boolean add(String word) {
			TrieNode node = root;
			int index = 0;
			while (index < word.length()) {
				node.eos = false;
				char c = word.charAt(index);
				int i = c - 'a';
				if (node.children[i] == null) {
					node.children[i] = new TrieNode();
				} else if (node.children[i].eos || index == word.length() - 1) {
					return false;
				}
				node = node.children[i];
				index++;
			}
			return true;
		}
	}

	public static class TrieNode {
		public boolean eos;

		public TrieNode[] children;

		public static int RANGE = 'j' - 'a' + 1;

		public TrieNode() {
			this.eos = true;
			children = new TrieNode[RANGE];
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		scanner.nextLine();

		Trie trie = new Trie();

		for (int i = 0; i < N; i++) {
			String word = scanner.nextLine();
			if (!trie.add(word)) {
				System.out.println("BAD SET");
				System.out.println(word);
				return;
			}
		}

		System.out.println("GOOD SET");
	}
}
