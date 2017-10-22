package com.algo.tree.problems;



import java.util.Random;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/array-and-simple-queries
public class ArrayAndSimpleQueriesWithTreap {
    static Random random = new Random();

    static class Treap {
        int count;
        int priority;
        int value;
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

    static void update(Treap treap) {
        treap.count = count(treap.left) + count(treap.right) + 1;
    }

    static Treap[] split(Treap treap, int key) {
        if (treap == null) {
            Treap[] empty = {null, null};
            return empty;
        }

        if (count(treap.left) > key) {
            Treap[] result = split(treap.left, key);
            treap.left = result[1];
            update(treap);
            Treap[] pair = {result[0], treap};
            return pair;
        } else {
            Treap[] result = split(treap.right, key - count(treap.left) - 1);
            treap.right = result[0];
            update(treap);
            Treap[] pair = {treap, result[1]};
            return pair;
        }
    }

    static Treap merge(Treap treapA, Treap treapB) {
      if (treapA == null || treapB == null) {
          return treapA != null ? treapA : treapB;
      } else if (treapA.priority > treapB.priority)  {
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

    public static void main(String... args) {
        try (final Scanner scanner = new Scanner(System.in)) {
        	int itemCount = scanner.nextInt();
            int commandCount = scanner.nextInt();
            Treap treap = null;
            for (int i = 0; i < itemCount; i++) {
            	int item = scanner.nextInt();
                treap = insert(treap, i, item);
            }

            for (int i = 0; i < commandCount; i++) {
                int opCode = scanner.nextInt();
                int fromIndex = scanner.nextInt();
                int toIndex = scanner.nextInt();

                Treap[] pair = split(treap, fromIndex - 2);
                Treap left = pair[0];
                pair = split(pair[1], toIndex - 1 - count(pair[0]));
                Treap middle = pair[0];
                Treap right = pair[1];

                if (opCode == 1) {
                    treap = merge(merge(middle, left), right);
                } else {
                    treap = merge(merge(left, right), middle);
                }
            }

            System.out.println(Math.abs(getLeft(treap) - getRight(treap)));
            System.out.println(traverse(treap).substring(1));
        }
    }
}
