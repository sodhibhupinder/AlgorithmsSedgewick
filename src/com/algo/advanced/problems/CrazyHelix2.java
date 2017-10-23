package com.algo.advanced.problems;

import java.util.Random;
import java.util.Scanner;

import com.algo.advanced.problems.CrazyHelix.Treap;


public class CrazyHelix2 {

	private static final int INF = 100000001;
	static Random rand = new Random();


	static class Treap {
		Treap l, r;
		int value;
		int count;
		int heapValue;
		boolean reversed;

		public Treap(int v) {
			value = v;
			heapValue = rand.nextInt();
			count = 1;
		}

		public void print(String prefix) {
			if (l != null) {
				l.print(prefix + " ");
			}
			System.out.println(prefix + "v=" + value + "/r=" + reversed);
			if (r != null) {
				r.print(prefix + " ");
			}
		}

	}

	static Treap root;

	public static Treap update(Treap a) {
		if (a == null) {
			return null;
		}
		pushDown(a);
		a.count = 1 + count(a.l) + count(a.r);
		return a;
	}

	public static void pushDown(Treap a) {
		if (a.reversed) {
			Treap tmp = a.l;
			a.l = a.r;
			a.r = tmp;
			reverse(a.l);
			reverse(a.r);
			a.reversed = false;
		}
	}

	public static int count(Treap a) {
		return (a == null) ? 0 : a.count;
	}

	public static void reverse(Treap a) {
		if (a != null) {
			a.reversed ^= true;
		}
	}

	/**
	 * Grabs k-th Treap from given Treap.
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
		int l = count(a.l);
		if (k == l) {
			return a;
		} else if (k < l) {
			return grab(a.l, k);
		} else {
			return grab(a.r, k - l - 1);
		}
	}

	/**
	 * Merges two trees.
	 *
	 * @param a
	 * @param b
	 * @return merged root Treap
	 */
	public static Treap merge(Treap a, Treap b) {
		if (a == null || b == null) {
			return a == null ? b : a;
		}
		update(a);
		update(b);
		if (a.heapValue < b.heapValue) {
			a.r = merge(a.r, b);
			return update(a);
		} else {
			b.l = merge(a, b.l);
			return update(b);
		}
	}

	/**
	 * Splits tree at point k. [0, k), [k, n)
	 *
	 * @param a
	 * @param k
	 * @return
	 */
	public static Treap[] split(Treap a, int k) {
		if (a == null) {
			return new Treap[] { null, null };
		}
		update(a);
		if (k <= count(a.l)) {
			Treap[] s = split(a.l, k);
			a.l = s[1];
			return new Treap[] { s[0], update(a) };
		} else {
			Treap[] s = split(a.r, k - count(a.l) - 1);
			a.r = s[0];
			return new Treap[] { update(a), s[1] };
		}
	}

	/**
	 * Inserts Treap v at point k.
	 *
	 * @param a
	 * @param k
	 * @return new tree
	 */
	public static Treap insert(Treap a, int k, Treap v) {
		Treap[] x = split(a, k);
		return merge(x[0], merge(v, x[1]));
	}
	
	public static int detect(Treap x, int v) {
		// Base Cases: root is null or key is present at root
	    if (x == null || x.value == v)
	       return 0;
	     
	    // Key is greater than root's key
	    if (x.value< v)
	       return detect(x.r, v);
	  
	    // Key is smaller than root's key
	    return detect(x.l, v);
    }
	
	/** Function for inorder traversal **/
    public static void inorder()
    {
        inorder(root);
        System.out.println("");
    }
    private static void inorder(Treap r)
    {
        if (r != null)
        {
            inorder(r.l);
            System.out.print(r.value +" ");
            inorder(r.r);
        }
    }
		/**
	 * Erases Treap at point k.
	 *
	 * @param a
	 * @param k
	 * @return new tree
	 */
	public static Treap erase(Treap a, int k) {
		Treap[] al = split(a, k);
		Treap[] ar = split(al[1], 1);
		return merge(al[0], ar[1]);
	}

	/**
	 * Reverses value at point [l, r)
	 *
	 * @param l
	 * @param r
	 */
	public static void reverse(int l, int r) {
		Treap[] right = split(root, r);
		Treap[] left = split(right[0], l);
		reverse(left[1]);
		root = merge(left[0], merge(left[1], right[1]));
	}

	/**
	 * Returns size of the tree.
	 *
	 * @return size of the tree
	 */
	public int size() {
		return count(root);
	}

	/**
	 * Adds new Treap value=v to the end of tree.
	 *
	 * @param v
	 */
	public static void push(int v) {
		root = insert(root, INF, new Treap(v));
	}

	/**
	 * Adds new Treap value=v to tree at k-th position.
	 *
	 * @param v
	 */
	public static void push(int v, int k) {
		root = insert(root, k, new Treap(v));
	}

	/**
	 * Removes k-th Treap from tree.
	 *
	 * @param k
	 */
	public static void remove(int k) {
		root = erase(root, k);
	}

	/**
	 * Get k-th Treap value
	 *
	 * @param k
	 * @return
	 */
	public static int get(int k) {
		Treap n = grab(root, k);
		if (n == null) {
			return -INF;
		}
		return n.value;
	}
	static void traverseInternal(StringBuffer buffer, Treap treap) {
		if (treap == null) {
			return;
		}

		traverseInternal(buffer, treap.l);
		buffer.append(" " + treap.value);
		traverseInternal(buffer, treap.r);
	}

	static String traverse(Treap treap) {
		StringBuffer buffer = new StringBuffer();
		traverseInternal(buffer, treap);
		return buffer.toString();
	}
	public static void main(String... args) {
		try (final Scanner scanner = new Scanner(System.in)) {
			int itemCount = scanner.nextInt();
			int commandCount = scanner.nextInt();
			// Treap treap = null;
			int a[] = new int[itemCount];
			for (int i = 0; i < itemCount; i++) {
				a[i] = i + 1;
			}
			for (int i = 0; i < a.length; i++) {
				push(a[i]);
			}

			for (int i = 0; i < commandCount; i++) {
				int opCode = scanner.nextInt();

				if (opCode == 1) {
					int fromIndex = scanner.nextInt();
					int toIndex = scanner.nextInt();
					reverse(fromIndex - 1, toIndex);
					System.out.println(traverse(root));
					inorder();
				} else if (opCode == 2) {
					int element = scanner.nextInt();
					System.out.println("element " + element + " is at position " + (detect(root,element)+1));
				} else if (opCode == 3) {
					int position = scanner.nextInt();
					System.out.println("element at position " + position + " is " + get(position - 1));
				}

			}

			// System.out.println(Math.abs(getLeft(treap) - getRight(treap)));
			// System.out.println(traverse(treap).substring(1));
		}
	}
}