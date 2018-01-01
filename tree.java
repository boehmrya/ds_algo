

import java.io.*;
import java.util.*;


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


	// traversal wrapper
	public void traverse(int traverseType) {
		switch(traverseType) {
			case 1: System.out.print("\nPreorder traversal: ");
					preOrder(root);
					break;
			case 2: System.out.print("\nInorder traversal: ");
					inOrder(root);
					break;
			case 3: System.out.print("\nPostorder traversal: ");
					postOrder(root);
					break;
		}
		System.out.println();
	}


	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}


	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			preOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.rightChild);
		}
	}


	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}

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

		while (true) {
			System.out.print("Enter first letter of insert, find, delete, or traverse: ");
			int choice = getChar();
			switch(choice) {
				case 'i':
					System.out.print("Enter value to insert: ");
					value = getInt();
					theTree.insert(value, value + 0.9);
					break;
				case 'f':
					System.out.print("Enter value to find: ");
					value = getInt();
					Node found = theTree.find(value);
					if (found != null) {
						System.out.print("Found: ");
						found.displayNode();
						System.out.print("\n");
					}
					else {
						System.out.print("Could not find: ");
						System.out.print(value + '\n');
					}
					break;
				case 'd':
					System.out.print("Enter value to delete: ");
					value = getInt();
					boolean didDelete = theTree.delete(value);
					if (didDelete) {
						System.out.print("Deleted " + value + '\n');
					}
					else {
						System.out.print("Could not delete");
						System.out.print(value + '\n');
					}
					break;
				case 't':
					System.out.print("Enter type 1, 2, or 3: ");
					value = getInt();
					theTree.traverse(value);
					break;
				default:
					System.out.print("Invalid entry\n");
			}
		}
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


