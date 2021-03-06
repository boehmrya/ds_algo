

import java.io.*;
import java.util.*;
import java.lang.Math;


class Link {
   public int iData;              // data item
   public double dData;           // data item
   public Link next;              // next link in list

   public Link(int id, double dd) {
      iData = id;                 // initialize data
      dData = dd;                 // ('next' is automatically
   }    

   public void displayLink() {
      System.out.print("{" + iData + ", " + dData + "} ");
  }
}  // end class Link



class LinkList
   {
   private Link first;            // ref to first link on list

// -------------------------------------------------------------
   public LinkList()              // constructor
      {
      first = null;               // no links on list yet
      }
// -------------------------------------------------------------
   public boolean isEmpty()       // true if list is empty
      {
      return (first==null);
      }
// -------------------------------------------------------------
                                  // insert at start of list
   public void insertFirst(int id, double dd)
      {                           // make new link
      Link newLink = new Link(id, dd);
      newLink.next = first;       // newLink --> old first
      first = newLink;            // first --> newLink
      }
// -------------------------------------------------------------
   public Link deleteFirst()      // delete first item
      {                           // (assumes list not empty)
      Link temp = first;          // save reference to link
      first = first.next;         // delete it: first-->old next
      return temp;                // return deleted link
      }
// -------------------------------------------------------------
   public void displayList()
      {
      System.out.print("List (first-->last): ");
      Link current = first;       // start at beginning of list
      while(current != null)      // until end of list,
         {
         current.displayLink();   // print data
         current = current.next;  // move to next link
         }
      System.out.println("");
      }
// -------------------------------------------------------------
}  // end class LinkList





class Node {
	public int iData; // key 
	public double dData; // value
	public Node leftChild;
	public Node rightChild;

	public void displayNode() {
		System.out.print('{');
		System.out.print(iData);
		System.out.print(',');
		System.out.print(dData);
		System.out.print('}');
	}

} // end node class


class Tree {
	// first node of tree
	private Node root;

	// constructor
	public Tree() {
		root = null;
	}
	// end constructor

	// getter for root node
	public Node getRoot() {
		return root;
	}

	// get the height of a subtree
	// initialize iHeight to 0
	public int height(Node localRoot, int maxHeight) {
		// find the starting node
		int lHeight, rHeight;

		if (localRoot != null) {
			maxHeight++;

			// keep going down and counting
			lHeight = height(localRoot.leftChild, maxHeight);
			rHeight = height(localRoot.rightChild, maxHeight);

			// keep the maximum height of the subtree
			if (rHeight > lHeight) {
				maxHeight = rHeight;
			}
			else {
				maxHeight = lHeight;
			}
		}
		
		return maxHeight;
	}


	// get the depth of a node
	// returns -1 if not in tree
	public int depth(int key) {
		Node current = root;
		int d = 0;
		while (current.iData != key) {
			if (key < current.iData) {
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			if (current == null) {
				return -1;
			}
			d++;
		}
		return d;
	}


	// creates an array of linked lists which include the nodes
	// at each depth in the tree
	public LinkList[] depthLists() {
		int d;
		int h = height(getRoot(), 0);
		LinkList[] lists = new LinkList[h];
		// traverse and build lists
		inOrderDepth(root, lists);
		return lists;
	}


	public void inOrderDepth(Node localRoot, LinkList[] lists) {
		if (localRoot != null) {
			inOrderDepth(localRoot.leftChild, lists);
			int d = depth(localRoot.iData);
			if (lists[d] == null) {
				lists[d] = new LinkList();
			}
			lists[d].insertFirst(localRoot.iData, 0.0);
			inOrderDepth(localRoot.rightChild, lists);
		}
	}


	// check if  a subtree is balanced
	// helper for isTreeBalanced().
	public boolean isSubtreeBalanced(Node localRoot) {
		int lHeight = 0;
		int rHeight = 0;

		// get left subtree height
		if (localRoot != null) {
			lHeight = height(localRoot.leftChild, 0);
			rHeight = height(localRoot.rightChild, 0);
		}

		// return difference
		return (Math.abs(lHeight - rHeight) < 2);
	}

	// check if a while tree is balanced
	// uses isSubtreeBalanced().
	public boolean isTreeBalanced(Node localRoot, boolean isBalanced) {
		boolean lBalanced, rBalanced;
		if (localRoot != null) {
			// check if subtrees are balanced, return if not
			lBalanced = isSubtreeBalanced(localRoot.leftChild);
			rBalanced = isSubtreeBalanced(localRoot.rightChild);

			// if either side is inbalanced
			if (lBalanced == false || rBalanced == false) {
				isBalanced = false;
			}
			
			isTreeBalanced(localRoot.leftChild, isBalanced);
			isTreeBalanced(localRoot.rightChild, isBalanced);
		}
		return isBalanced;
	}

	// find node with given key
	public Node find(int key) {
		Node current = root;
		while (current.iData != key) {
			if (key < current.iData) {
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	} 
	// end find


	public void insert(int id, double dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;

		if (root == null) { // if tree was empty
			root = newNode;
		}
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (id < current.iData) { // go left?
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} // end if go left
				else { // or go right?
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	} 


	// delete node with a given key
	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while (current.iData != key) { // search for node
			parent = current;
			if (key < current.iData) {
				isLeftChild = true;
				current = current.leftChild;
			}
			else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				return false;
			}
		} // end whiile
		// found node to delete

		// if no children, simply delete it
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) { // if root
				root = null;	// tree is empty
			}
			else if (isLeftChild) {
				parent.leftChild = null;
			}
			else {
				parent.rightChild = null;
			}
		}

		// if no right child, replace with left subtree
		else if (current.rightChild == null) {
			if (current == root) {
				root = current.leftChild;
			}
			else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			}
			else {
				parent.rightChild = current.leftChild;
			}
		}

		// if no left child, replace with right subtree
		else if (current.leftChild == null) {
			if (current == root) {
				root = current.rightChild;
			}
			else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			}
			else {
				parent.rightChild = current.rightChild;
			}
		}

		// two children, so replace with inorder successor
		else {
			// get successor of node to delete (current)
			Node successor = getSuccessor(current);

			// connect parent of current to successor instead
			if (current == root) {
				root = successor;
			}
			else if (isLeftChild) {
				parent.leftChild = successor;
			}
			else {
				parent.rightChild = successor;
			}

			// connect successor to current's left child
			successor.leftChild = current.leftChild;
		} // end else two children

		return true;
	} // end delete()


	// returns node with next highest value after delNode
	// goes to right child, then right child's left descendents
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;

		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}


	public void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}


	public void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}


	public void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}

	public void orderedInsertion(int[] ordArray) {
		int mid = ordArray.length / 2;
		int i = mid;
		int j = mid + 1;
		while ((i >= 0) || (j < ordArray.length)) {
			if (i >= 0) {
				insert(ordArray[i], 0.0);
				i--;
			}
			if (j < ordArray.length) {
				insert(ordArray[j], 0.0);
				j++;
			}
		}
	}


	public boolean isBinSearchTree(Node localRoot, boolean status) {
		if (localRoot != null) {
			if (localRoot.leftChild != null) {
				if (localRoot.leftChild.iData > localRoot.iData) {
					status = false;
				}
			}
			if (localRoot.rightChild != null) {
				if (localRoot.rightChild.iData < localRoot.iData) {
					status = false;
				}
			}
			isBinSearchTree(localRoot.leftChild, status);
			isBinSearchTree(localRoot.rightChild, status);
		}
		return status;
	}


	// find successor node with given key, and return it's successor
	public Node findSuccessor(int key) {
		Node successor = root;
		Node current = root;
		while (current.iData != key) {
			successor = current;
			if (key < current.iData) {
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			if (current == null) {
				return null;
			}
		}
		return successor;
	} 
	// end find successor


	// find first common ancestor of two nodes
	public Node findCommonAncestor(int key1, int key2) {
		Node current1 = root;
		Node current2 = root;
		Node prev = root; // track the point of divergence
		while (current1.iData != key1 && current2.iData != key2) {

			if (current1.equals(current2)) {
				prev = current1;
			}

			// go down tree to find node 1
			if (key1 < current1.iData) {
				current1 = current1.leftChild;
			}
			else if (key1 >= current1.iData) {
				current1 = current1.rightChild;
			}

			// go down tree to find node 2
			if (key2 < current2.iData) {
				current2 = current2.leftChild;
			}
			else if (key2 >= current2.iData) {
				current2 = current2.rightChild;
			}

			// if either node doesn't exist
			if (current1 == null || current2 == null) { 
				return null;
			}
		}
		return prev;
	} 
	// end find common ancestor

}


class treeApp {
	public static void main(String[] args) throws IOException {
		int value;
		Tree theTree = new Tree();

		theTree.insert(50, 1.5);
		theTree.insert(25, 1.2);
		theTree.insert(75, 1.7);
		theTree.insert(12, 1.5);
		theTree.insert(37, 1.2);
		theTree.insert(43, 1.7);
		theTree.insert(30, 1.5);
		theTree.insert(53, 1.2);
		theTree.insert(87, 1.7);
		theTree.insert(93, 1.5);
		theTree.insert(97, 1.5);

		//System.out.println("Height: " + theTree.height(theTree.getRoot(), 0));
		//System.out.println("Tree Balanced: " + theTree.isTreeBalanced(theTree.getRoot(), true));

		//int[] intArray = new int[10];
		Tree theTree2 = new Tree();
		int[] intArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		theTree2.orderedInsertion(intArray);
		//theTree2.inOrder(theTree2.getRoot());

		System.out.println(theTree.findCommonAncestor(53, 93).iData);


	} // end main()

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

} // end class treeApp


