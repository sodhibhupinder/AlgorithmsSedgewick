package com.algo.advances.problems;

import java.util.Random;
import java.util.Scanner;


public class CrazyHelix {

	static Random random = new Random();
	private static final int INF = 100000001;
	static class Treap {
		int count;
		int priority;
		int value;
		boolean reversed;
		Treap left;
		Treap right;

		public Treap(int value, int priority) {
			this.count = 1;
			this.value = value;
			this.priority = priority;
		}
	}

	static int count(Treap treap) {
		return treap == null ? 0 : treap.count;
	}
	static void reverse(Treap a) {
        if (a != null) {
            a.reversed ^= true;
        }
    }
	/**
     * Reverses value at point [l, r)
     *
     * @param l
     * @param r
     */
	static Treap reverse(Treap treap,int l, int r) {
    	Treap[] right = split(treap, r);
    	Treap[] left = split(right[0], l);
        reverse(left[1]);
        return merge(left[0], merge(left[1], right[1]));
    }
	static void update(Treap treap) {
		treap.count = count(treap.left) + count(treap.right) + 1;
	}

	static Treap[] split(Treap treap, int key) {
		if (treap == null) {
			Treap[] empty = { null, null };
			return empty;
		}

		if (count(treap.left) > key) {
			Treap[] result = split(treap.left, key);
			treap.left = result[1];
			update(treap);
			Treap[] pair = { result[0], treap };
			return pair;
		} else {
			Treap[] result = split(treap.right, key - count(treap.left) - 1);
			treap.right = result[0];
			update(treap);
			Treap[] pair = { treap, result[1] };
			return pair;
		}
	}

	static Treap merge(Treap treapA, Treap treapB) {
		if (treapA == null || treapB == null) {
			return treapA != null ? treapA : treapB;
		} else if (treapA.priority > treapB.priority) {
			treapA.right = merge(treapA.right, treapB);
			update(treapA);
			return treapA;
		} else {
			treapB.left = merge(treapA, treapB.left);
			update(treapB);
			return treapB;
		}
	}

	static Treap insert(Treap treap, int key, int value) {
		Treap newTreap = new Treap(value, random.nextInt());
		Treap[] pair = split(treap, key);
		return merge(merge(pair[0], newTreap), pair[1]);
	}

	static int getLeft(Treap treap) {
		if (treap == null) {
			throw new IllegalArgumentException();
		}
		return treap.left != null ? getLeft(treap.left) : treap.value;
	}

	static int getRight(Treap treap) {
		if (treap == null) {
			throw new IllegalArgumentException();
		}
		return treap.right != null ? getRight(treap.right) : treap.value;
	}

	static void traverseInternal(StringBuffer buffer, Treap treap) {
		if (treap == null) {
			return;
		}

		traverseInternal(buffer, treap.left);
		buffer.append(" " + treap.value);
		traverseInternal(buffer, treap.right);
	}

	static String traverse(Treap treap) {
		StringBuffer buffer = new StringBuffer();
		traverseInternal(buffer, treap);
		return buffer.toString();
	}
	
	/**
     * Get k-th node value
     *
     * @param k
     * @return
     */
    public static int get(Treap treap,int k) {
    	Treap n = grab(treap, k);
        if (n == null) {
            return -INF;
        }
        return n.value;
    }
    /**
     * Grabs k-th node from given node.
     *
     * @param a
     * @param k
     * @return
     */
    public static Treap grab(Treap a, int k) {
        if (a == null) {
            return null;
        }
        update(a);
        int l = count(a.left);
        if (k == l) {
            return a;
        } else if (k < l) {
            return grab(a.left, k);
        } else {
            return grab(a.right, k-l-1);
        }
    }
	public static void main(String... args) {
		try (final Scanner scanner = new Scanner(System.in)) {
			int itemCount = scanner.nextInt();
			int commandCount = scanner.nextInt();
			Treap treap = null;
			for (int i = 0; i < itemCount; i++) {
				treap = insert(treap, i+1, i+1);
			}
			
			for (int i = 0; i < commandCount; i++) {
				int opCode = scanner.nextInt();
			
				if (opCode == 1) {
					int fromIndex = scanner.nextInt();
					int toIndex = scanner.nextInt();
					treap = reverse(treap,fromIndex,toIndex);
					System.out.println(traverse(treap).substring(1));
				} else if(opCode == 2){
					int element = scanner.nextInt();
					System.out.println("element "+element+" is at position "+Math.abs(getLeft(treap) - getRight(treap)));
				}else if(opCode ==3){
					int position = scanner.nextInt();
					System.out.println("element at position "+position+" is "+get(treap,position-1));
				}
				
			}

			//System.out.println(Math.abs(getLeft(treap) - getRight(treap)));
			//System.out.println(traverse(treap).substring(1));
		}
	}

}
