package com.algo.advanced.problems;

import java.util.Scanner;


public class CrazyHelix2 {


	static boolean found;




	static class LazyRMQTreap {

		static class NodePair {
			LazyRMQTreap l, r;
		}
		LazyRMQTreap l, r;
		int size = 1;
		int val;
		boolean rev;
		double p;
		int min;
		int sum;
		int addingVal;
		
		public LazyRMQTreap(int val) {
			this.val = val;
			p = Math.random();
			update();
		}

		private LazyRMQTreap update() {
			assert addingVal == 0;
			size = nodeSize(l) + nodeSize(r) + 1;
			min = Math.min(l == null ? Integer.MAX_VALUE : l.min + l.addingVal,
					r == null ? Integer.MAX_VALUE : r.min + r.addingVal);
			min = Math.min(min, val);
			sum = val + (l == null ? 0 : l.sum + l.addingVal * l.size) + (r == null ? 0 : r.sum + r.addingVal * r.size);
			return this;
		}

		private static int nodeSize(LazyRMQTreap node) {
			return node != null ? node.size : 0;
		}

		private void processLazy() {
			if (rev) {
				LazyRMQTreap temp = l;
				l = r;
				r = temp;
				if (l != null)
					l.rev ^= true;
				if (r != null)
					r.rev ^= true;
				rev = false;
			}
			if (addingVal > 0) {
				if (l != null)
					l.addingVal += addingVal;
				if (r != null)
					r.addingVal += addingVal;
				val += addingVal;
				addingVal = 0;
				update();
			}
		}
		int[] findElement(int element) {
			int[] array = new int[size];
			int[] arrayIndexes = new int[size];
			findElement(array,arrayIndexes, 0,element);
			return arrayIndexes;
		}
 
		private void findElement(int[] array,int[] arrayIndexes, int off,int element) {
			if(!found) {
			processLazy();
			if (l != null && !found) {
				l.findElement(array,arrayIndexes, off,element);
				off += l.size;
			}
			if(element==val && !found)
			{
			array[off++] = val;
			arrayIndexes[val-1]= off;
			found=true;
			return;
			}
			else {
				off++;
			}
			if (r != null && !found) {
				r.findElement(array,arrayIndexes, off,element);
			}
			}
		}
		
		static LazyRMQTreap merge(LazyRMQTreap l, LazyRMQTreap r) {
			if (l == null || r == null)
				return l != null ? l : r;
			if (l.p > r.p) {
				l.processLazy();
				l.r = merge(l.r, r);
				return l.update();
			} else {
				r.processLazy();
				r.l = merge(l, r.l);
				return r.update();
			}
		}

		static NodePair split(LazyRMQTreap node, int k) {
			NodePair ret = new NodePair();
			if (node == null)
				return ret;
			node.processLazy();

			if (k <= nodeSize(node.l)) {
				ret = split(node.l, k);
				node.l = ret.r;
				ret.r = node.update();
			} else {
				ret = split(node.r, k - nodeSize(node.l) - 1);
				node.r = ret.l;
				ret.l = node.update();
			}
			return ret;
		}

		int get(int i) {
			processLazy();
			if (i < nodeSize(l))
				return l.get(i);
			if (i == nodeSize(l))
				return val;
			return r.get(i - nodeSize(l) - 1);
		}
		LazyRMQTreap insert(int i, int val) {
			NodePair p = split(this, i);
			return merge(p.l, merge(new LazyRMQTreap(val), p.r));
		}

		LazyRMQTreap delete(int i) {
			NodePair p = split(this, i);
			return merge(p.l, split(p.r, 1).r);
		}

		LazyRMQTreap reverseRange(int l, int r) {
			NodePair ab = split(this, l);
			NodePair bc = split(ab.r, r - l);
			bc.l.rev ^= true;
			return merge(ab.l, merge(bc.l, bc.r));
		}

		LazyRMQTreap rotateLeft(int i) {
			NodePair p = split(this, i);
			return merge(p.r, p.l);
		}

		LazyRMQTreap rotateLeftRange(int l, int r, int i) {
			NodePair ab = split(this, l);
			NodePair bc = split(ab.r, r - l);
			bc.l = bc.l.rotateLeft(i);
			return merge(ab.l, merge(bc.l, bc.r));
		}

		int minRange(int l, int r) {
			NodePair ab = split(this, l);
			NodePair bc = split(ab.r, r - l);
			int ret = bc.l.min;
			merge(ab.l, merge(bc.l, bc.r)); 
			return ret;
		}

		int sumRange(int l, int r) {
			NodePair ab = split(this, l);
			NodePair bc = split(ab.r, r - l);
			int ret = bc.l.sum;
			merge(ab.l, merge(bc.l, bc.r));
			return ret;
		}

		void addRange(int l, int r, int val) {
			NodePair ab = split(this, l);
			NodePair bc = split(ab.r, r - l);
			bc.l.addingVal += val;
			merge(ab.l, merge(bc.l, bc.r));
		}
		 @Override
		    public String toString() {
		        StringBuilder sb = new StringBuilder();
		        this.toString(this, sb);
		        return sb.toString().trim();
		    }

		    void toString(LazyRMQTreap t, StringBuilder sb) {
		        if (t == null)
		            sb.append(' ');
		        else {
		            this.toString(t.l, sb);
		            sb.append(t.val);
		            this.toString(t.r, sb);
		        }
		    }

	}
	
	

	
	public static void main(String[] args) {
		try (final Scanner scanner = new Scanner(System.in)) {
			int itemCount = scanner.nextInt();
			int commandCount = scanner.nextInt();
			int[] a = new int[itemCount];
			a[0] = 1;
			LazyRMQTreap root = new LazyRMQTreap(1);
			for (int i = 1; i < itemCount; i++) {
				a[i] = i + 1;
				root = LazyRMQTreap.merge(root, new LazyRMQTreap(i + 1));
			}
			int[] arrayOfIndexes = new int[itemCount];
			 StringBuilder sb = new StringBuilder();
			for (int i = 0; i < commandCount; i++) {
				int opCode = scanner.nextInt();
				if (opCode == 1) {
					int fromIndex = scanner.nextInt();
					int toIndex = scanner.nextInt();
					root.reverseRange(fromIndex - 1, toIndex);
				} else if (opCode == 2) {
					int element = scanner.nextInt();
					arrayOfIndexes = root.findElement(element);
					//System.out.println("element " + element + " is at position " + (arrayOfIndexes[element - 1]));
					sb.append("element " + element + " is at position " + (arrayOfIndexes[element - 1])+"\n");
					found=false;
				} else if (opCode == 3) {
					int position = scanner.nextInt();
					//System.out.println("element at position " + position + " is " + root.get(position - 1));
					sb.append("element at position " + position + " is " + root.get(position - 1)+"\n");
				}

			}
            scanner.close();
			System.out.println(sb.toString());
		}
	}
}