package com.algo.advanced.problems;

import java.util.Random;

public class TreapRangeMinQuery {
	
	static class Treap {

	    class Node {

	        int  val;
	        Node left, right;
	        int  p;
	        int  size;

	        Node(int val, int p) {
	            this.val = val;
	            this.p = p;
	            this.size = 1;
	        }
	    }

	    public static final Random rand = new Random();

	    Node root;

	    Node splitLeft, splitRight;

	    public Treap() {
	        this(null);
	    }

	    Treap(Node root) {
	        this.root = root;
	    }

	    Treap erase(int key) {
	        this.root = this.erase(this.root, key);
	        return this;
	    }

	    Node erase(Node node, int key) {
	        this.split(node, key + 1);
	        // Node left=splitLeft;
	        Node right = this.splitRight;
	        this.split(this.splitLeft, key);
	        return this.merge(this.splitLeft, right);
	    }

	    Treap insert(int key, int value) {
	        int priority = rand.nextInt();
	        this.root = this.insert(this.root, key, value, priority);
	        return this;
	    }

	    Node insert(Node node, int key, int value, int priority) {
	        this.split(node, key);
	        return this.merge(this.merge(this.splitLeft, new Node(value, priority)), this.splitRight);
	    }

	    Node merge(Node left, Node right) {
	        if (left == null)
	            return right;
	        if (right == null)
	            return left;

	        if (left.p > right.p) {
	            left.right = this.merge(left.right, right);
	            return this.update(left);
	        } else {
	            right.left = this.merge(left, right.left);
	            return this.update(right);
	        }
	    }

	    Treap merge(Treap treap) {
	        this.root = this.merge(this.root, treap.root);
	        return this;
	    }

	    int size() {
	        return this.size(this.root);
	    }

	    int size(Node n) {
	        return n == null ? 0 : n.size;
	    }

	    Treap split(int key) {
	        this.split(this.root, key);
	        this.root = this.splitRight;
	        Node root2 = this.splitLeft;
	        return new Treap(root2);
	    }

	    void split(Node node, int key) {
	        if (node == null)
	            this.splitLeft = this.splitRight = null;
	        else if (key <= this.size(node.left)) {
	            this.split(node.left, key);
	            node.left = this.splitRight;
	            this.splitRight = this.update(node);
	        } else {
	            this.split(node.right, key - this.size(node.left) - 1);
	            node.right = this.splitLeft;
	            this.splitLeft = this.update(node);
	        }
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        this.toString(this.root, sb);
	        return sb.toString().trim();
	    }

	    void toString(Node t, StringBuilder sb) {
	        if (t == null)
	            sb.append(' ');
	        else {
	            this.toString(t.left, sb);
	            sb.append(t.val);
	            this.toString(t.right, sb);
	        }
	    }

	    Node update(Node n) {
	        n.size = this.size(n.left) + this.size(n.right) + 1;
	        return n;
	    }
	}
	
}