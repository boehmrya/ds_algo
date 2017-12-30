

import java.lang.StringBuffer;
import java.util.Arrays;
import java.util.LinkedList;



class SetOfStacks<T> {

	private int limit;
	private int count;
	private LinkedList<Stack<T>> stackList;

	// constructor
	public SetOfStacks(int limit) {
		this.limit = limit;
		this.count = 0;
		this.stackList = new LinkedList<Stack<T>>();
	}

	T pop() {
		if (!stackList.isEmpty()) { // set of stacks is empty
			int i = count / limit;
			T item = stackList.get(i).pop();
			count--;
			return item;
		}
		return null;
	}

	void push(T item) {
		if (stackList.isEmpty()) {
			Stack<T> newStack = new Stack<T>();
			newStack.push(item);
			stackList.add(newStack);
			count++;
		}
		else {
			int i = count / limit;
			stackList.get(i).push(item);
		}
	}

	void printStacks() {
		int i = 0;
		while (i < stackList.size()) {
			stackList.get(i).printStack();
			i++;
		}
	}
}

/**
 *
 * Stack class
 */
class Stack<T> {

	private class Node {
		private T data;
		private Node next;

		public Node (T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node top;
	private T min;

	T pop() {
		if (top != null) {
			T item = top.data;
			top = top.next;
			return item;
		}
		return null;
	}

	void push(T item) {
		// update min value
		if (top == null) { // if empty
			this.min = item;
		}
		else {
			if (item.equals(min)) {
				min = item;
			}
		}
		Node k = new Node(item, top);
		k.next = top;
		top = k;
	}

	T peek() {
		return top.data;
	}

	T getMin() {
		return min;
	}

	boolean isEmpty() {
		return (top == null);
	}

	void printStack() {
		Stack<T> tempStack = new Stack<T>();
		while (!this.isEmpty()) { // use temp buffer
			T item = this.pop();
			System.out.println(item);
			tempStack.push(item);
		}
		while(!tempStack.isEmpty()) { // replace original stack
			T item = tempStack.pop();
			this.push(item);
		}
		System.out.println();
	}
      
}


/*
* Queue class
*/
class Queue<T> {

	private class Node {
		private T data;
		private Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node first;
	private Node last;

	void enqueue(T item) {
		if (first == null) {
			last = new Node(item, null);
			first = last;
		}
		else {
			last.next = new Node(item, null);
			last = last.next;
		}
	}

	T dequeue() {
		if (first != null) {
			T item  = first.data;
			first = first.next;
			if (first == null) {
				last = null;
			}
			return item;
		}
		return null;
	}

	T peek() {
		return first.data;
	}

	boolean isEmpty() {
		return (first == null);
	}

	void printQueue() {
		Queue<T> tempQueue = new Queue<T>();
		while (!this.isEmpty()) { // use temp buffer
			T item = this.dequeue();
			System.out.println(item);
			tempQueue.enqueue(item);
		}
		while(!tempQueue.isEmpty()) { // replace original stack
			T item = tempQueue.dequeue();
			this.enqueue(item);
		}
		System.out.println();
	}

}



/*
* Singly linked list class
*/
class SingleLList<Integer> {
    
    private class Node {
        private int data;
        private Node next;

        public Node(int data, Node next)  {
            this.data  = data;
            this.next = next;
        }
    } //End of Node inner class


    private Node head;

    public SingleLList( ) {
        head = null;
    }

    /**
     * Adds a node at the start of the list with the specified data.
     * The added node will be the first node in the list.
    */
    public void addToStart(int data) {
        head = new Node(data, head);
    }

    /*
    * returns the length of a linked list
    */
    public int listLength() {
    	Node position = head;
    	int length = 1;
    	while (position.next != null) {
    		position = position.next;
    		length++;
    	}
    	return length;
    }

    /**
     * Removes the head node and returns true if the list contains at
     * least one node. Returns false if the list is empty.
    */
    public boolean deleteHeadNode() {
        if (head != null) {
            head = head.next;
            return true;
        }
        else
            return false;
    }

    /*
    * returns the value of the first node
    */
    public int peek() {
    	return head.data;
    }
 
    /**
     * Prints out the keys and values for every item in the linked list.
     */
    public void outputList( ) {
        Node position = head;
        while (position != null) {
            System.out.println( "Data: " + position.data );
            position = position.next;
        }
        System.out.println(); // line break
    }

    /*
    * Checks if linked list is empty.
    */
    public boolean isEmpty( ) {
        return (head == null);
    }

    /*
    * Clears the entire linked list.
    */
    public void clear( ) {
        head = null;
    }

    /*
    * removes duplicate nodes
    */
    public void removeDups() {
    	Node current = head;
    	Node position;
    	int count, scount, tcount, currentData;
    	
    	count = 1;
    	while (current != null) {
    		currentData = current.data;
    		position = current.next;
    		scount = count;

    		while (position != null) {
    			if (position.data == currentData) {

    				// change previous node's pointer
    				position = current.next;
    				tcount = count;
    				while (tcount < (scount - 1)) {
    					position = position.next;
    					tcount++;
    				}
    				position.next = position.next.next;
    				
    				// go back to one node after current
    				position = current.next;
    				scount = count;
    			}
    			else { // otherwise, keep going
    				position = position.next;
    				scount++;
    			}
    		}
    		count++;
    		current = current.next;
    	}	
    }


    /*
    * find kth to last element of linked list, print it.
    */
    public void kthToLast( int k) {
    	Node position = head;
    	int count, n;

    	// get the length of the list
    	count = 0;
    	while (position != null) {
    		position = position.next;
    		count++;
    	}

    	// calculate kth-to-last index and go back through to find node
    	n = count - k;
    	count = 0;
    	position = head;
    	while(count < n) {
    		position = position.next;
    		count++;
    	}
    	System.out.println("kth to last element: " + position.data);
    }

    /*
	* remove the middle node in a singly linked list.
	*/
	public void removeMiddle() {
		Node position = head;
		int count, n;

		// find length of list
		count = 0;
		while (position != null) {
			position = position.next;
			count++;
		}

		// calculate middle element
		// traverse the list to the previous node
		n = count / 2;
		count = 0;
		position = head;
		while (count < (n - 1)) {
			position = position.next;
			count++;
		}

		// connect previous node to node after middle element
		position.next = position.next.next;
	}

	/*
	* partition list so that all values less than k come before it,
	* and all values greater than k come after it.
	*/
	public void partition(int k) {
		// set up first two pointers to iterate through list
		Node secondPosition = head;
		Node firstPosition = secondPosition.next;
		boolean hasPartition = false;

		// use first pointer to determine if list has partition
		// if so, remove partition node and place it at the front of the list
		while (firstPosition != null) {
			if (firstPosition.data == k) {
				hasPartition = true;
				// remove the node
				secondPosition.next = firstPosition.next;
				// add partition to beginning of list
				head = new Node(k, head);
			}
			secondPosition = secondPosition.next;
			firstPosition = secondPosition.next;
		}

		if (hasPartition == true) {
			// reset pointers
			secondPosition = head;
			firstPosition = secondPosition.next;

			// have first and second position pointers go through list.
			// if first position value is less than the partition, remove, and add to the front of the list
			while(firstPosition != null) {
				if (firstPosition.data < k) {
					// add partition to beginning of list
					head = new Node(firstPosition.data, head);
					// remove the node
					secondPosition.next = firstPosition.next;
				}
				secondPosition = secondPosition.next;
				firstPosition = secondPosition.next;
			}
		}
		else {
			System.out.println("list doesn't have partition value");
		}
	}


	/*
	* get sum of linked list.  Assumes that every node has a single digit.
	* the first node starts at the 1 digit, then 10, 100, 1000, etc.
	*/
	public int digitSumBack() {
		Node position = head;
		int count = 1;
		int total = 0;

		while (position != null) {
			if (position != head) {
				count = count * 10;
			}
			total = total + (position.data * count);
			position = position.next;
		}
		return total;
	}


	/*
	* get sum of linked list.  Assumes that every node has a single digit.
	* the first node starts at the largest digit, then goes down: 100, 10, 1, etc.
	*/
	public int digitSumFor() {
		Node position = head;
		int length = 0;
		int count = 1;
		int total = 0;

		// get the length of the list
		while (position != null) {
			length++;
			position = position.next;
		}

		// calculate the first digit place (1, 10, 100, etc.)
		while (length > 1) {
			count *= 10;
			length--;
		}

		// get the sum
		position = head;
		while (position != null) {
			total = total + (position.data * count);
			count = count /= 10;
			position = position.next;
		}
		return total;
	}

	/*
	* converts a singly-linked list into a circular-linked list by
	*/
	public void makeCircular() {
		Node position = head;
		while (position.next != null) {
			position = position.next;
		}
		// point the last
		position.next = head;
	}

	/*
	* find the beginning of the loop in a circular linked list
	*/
	public boolean isCircular() {
		Node position = head;
		int headCount = 0;
		while (position.next != null && headCount < 2) {
			if ( position == head || position.next == head) {
				headCount++;
			}
			position = position.next;
		}
		if (headCount == 2) {
			return true;
		}
		return false;
	}

	/*
	* reverses a linked list
	*/	
	public void reverse() {
		Node secondPosition = head;
		Node firstPosition = secondPosition.next;
		int length = 1;
		int count = 1;

		// get the length of the list
		while (firstPosition.next != null) {
			firstPosition = firstPosition.next;
			length++;
		}
		// reset first position
		firstPosition = secondPosition.next;

		while (count < length) {
			while (firstPosition.next != null) {
				secondPosition = secondPosition.next;
				firstPosition = secondPosition.next;
			}
			// make the last item the first
			firstPosition.next = head;
			head = firstPosition;
			secondPosition.next = null;
			count++;
		}
	}

	/*
	* copies one linked list into another
	*/
	public void copy(SingleLList other) {

		Node position = other.head;
		this.addToStart(position.data);

		while (position.next != null) {
			position = position.next;
			this.addToStart(position.data);
		}
		this.reverse();
	}



}



/**
 * @author Ryan Boehm
 */
public class practice {

	/*
	* determines if a string has unique characters
	*/
	public static boolean uniqueChars( String word ) {
		for (int i = 0; i < word.length(); i++) {
            char c1 = word.charAt(i);
            for (int j = (i + 1); j < word.length(); j++) {
            	char c2 = word.charAt(j);
               	if (c1 == c2) {
               		return false;
               	}
       		}    
       	}
       	return true;
	}


	/*
	* determines if string 2 is a permutation of string 1
	*/
	public static boolean strPermutation(String str1, String str2) {
		int i, j;
		char c1, c2;

		// false if not the same length
		if (str1.length() != str2.length()) {
			return false;
		}

		StringBuffer newStr2 = new StringBuffer(str2);
		i = 0;

		while (i < str1.length()) {
			j = 0;
			c1 = str1.charAt(i);
			while (j < newStr2.length()) {
				c2 = newStr2.charAt(j);
				if (c1 == c2) {
					newStr2.deleteCharAt(j);
				}
				j++;
			}
			i++;
		}
		if (newStr2.length() == 0) {
			return true;
		} 
		return false;
	}


	/*
	* returns the last non white space char in sentence
	*/
	public static int lastLetter(char sentence[]) {
		int i = (sentence.length - 1);
		while (sentence[i] == ' ' || sentence[i] == '\t' || sentence[i] == '\n') {
			i--;
		}
		return i;
	}


	/*
	* replace each space in character array with a %20
	*/
	public static char[] replaceSpaces(char sentence[]) {
		for (int i = 0; i < lastLetter(sentence); i++) {
			if (sentence[i] == ' ') {
				// rotate remaining characters back two slots
				for (int j = lastLetter(sentence); j > i; j--) {
					sentence[j + 2] = sentence[j];
				}
				// replace chars
				sentence[i] = '%';
				sentence[i + 1] = '2';
				sentence[i + 2] = '0';	
			}
		}
		return sentence;
	}


	/*
	* compresses a string
	*/
	public static String stringComp(String str) {
		StringBuffer compStr = new StringBuffer();
		boolean newWord = true;
		int count = 0;
		char c1, c2, numChar;
		String num;

		for (int i = 0; i < str.length(); i++) {
			c1 = str.charAt(i);
			if (i > 0) {
				c2 = str.charAt(i - 1);
				// increase count if current char is same as previous
				if (c1 == c2) {
					count++;
				}
				else {
					// add char and number of times on character transition
					num = Integer.toString(count);
					numChar = num.charAt(0);
					compStr.append(numChar);
					compStr.append(c1);
					count = 1;
				}
			}
			else {
				compStr.append(c1);
				count++;
			}
		}
		// add count for final character
		num = Integer.toString(count);
		numChar = num.charAt(0);
		compStr.append(numChar);

		if (compStr.length() == str.length()) {
			return str;
		}
		return compStr.toString();
	}


	/*
	* rotates and m x n matrix 90 degrees
	*/
	public static int[][] matrixRotate( int[][] multi ) {
		int i, j;
		int h = multi.length;
		int w = multi[0].length;
		// normall multi[h][w] - change to multi[w][h] to rotate
		int[][] multiRotate = new int[w][h];
		i = 0;
		while (i < h) {
			j = 0;
			while (j < w) {
				multiRotate[j][i] = multi[i][j];
				j++;
			}
			i++;
		}
		return multiRotate;
	}


	/*
	* rotates and m x n matrix 90 degrees
	*/
	public static int[][] rowColZero( int[][] multi ) {
		int i, j, k;
		int rows = multi.length;
		int cols = multi[0].length;
		LinkedList<Integer> zeroRows = new LinkedList();
		LinkedList<Integer> zeroCols = new LinkedList();

		i = 0;
		while (i < rows) {
			j = 0;
			while (j < cols) {
				if (multi[i][j] == 0 ) {
					if ((zeroRows.isEmpty() && zeroCols.isEmpty()) || (!zeroRows.contains(i) && !zeroCols.contains(j))) {
						// track which rows and cols have already been zero'd
						zeroRows.add(i);
						zeroCols.add(j);

						k = 0;
						// hold col # constant, set each row value to zero
						while (k < rows) {
							multi[k][j] = 0;
							k++;
						}
						k = 0;
						// hold row # constant, set each col value to zero
						while (k < cols) {
							multi[i][k] = 0;
							k++;
						}
					}
				}
				j++;
			}
			i++;
		}
		return multi;
	}


	/*
	* prints a two-dimensional grid.
	*/
	public static void printGrid( int[][] multi ) {
		int h = multi.length;
		int w = multi[0].length;
   		for (int i = 0; i < h; i++) {
      		for (int j = 0; j < w; j++) {
         		System.out.print(multi[i][j]);
      		}
      		System.out.println();
   		}
	}


	/*
	* determine if str2 is a rotation of str1
	*/
	public static boolean isRotation( String str1, String str2 ) {
		int i, j, temp, total;
		char firstChar = str1.charAt(0);

		// return false if strings aren't the same length
		if (str1.length() != str2.length()) {
			return false;
		}

		// otherwise check rotation
		i = 0;
		while (i < str2.length()) {
			if (str2.charAt(i) == firstChar) {
				j = 0;
				temp = i;
				total = 1;
				while ((i < str2.length()) && (str2.charAt(i) == str1.charAt(j))) {
					i++;
					j++;
					total++;
				}
				if (i == str2.length()) {
					i = 0;
					while ((j < str1.length()) && (str2.charAt(i) == str1.charAt(j))) {
						i++;
						j++;
						total++;
					}
					if (total == (str1.length() + 1)) {
						return true;
					}
					else {
						total = 0;
						i = temp;
					}
				}
				else {
					i = temp;
				}
			}
			i++;
		}
		return false;
	}


	/*
	* sums two singly-linked lists representing digits (arranged in a backward manner)
	*/
	public static int listSumBack(SingleLList num1, SingleLList num2) {
		return (num1.digitSumBack() + num2.digitSumBack());
	}

	/*
	* sums two singly-linked lists representing digits (arranged in a forward manner)
	*/
	public static int listSumFor(SingleLList num1, SingleLList num2) {
		return (num1.digitSumFor() + num2.digitSumFor());
	}

	/*
	* tests if two singly-linked lists are equal
	*/
	public static boolean listsEqual(SingleLList list1, SingleLList list2) {
		// if the lists are different lengths, they aren't equal
		if (list1.listLength() != list2.listLength()) {
			return false;
		}
		// otherwise, compare each element's value
		while (!list1.isEmpty() && !list2.isEmpty()) {
			// compare head nodes
			if (list1.peek() != list2.peek()) {
				return false;
			}
			// remove head nodes
			list1.deleteHeadNode();
			list2.deleteHeadNode();
		}
		return true;
	}

	/*
	* tests if a linked list is a palindrome
	*/
	public static boolean isPalindrome(SingleLList list1) {
		SingleLList list2 = new SingleLList();
		list2.copy(list1);
		list1.reverse();
		return (listsEqual(list2, list1));
	}


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/*
	    String testWord1 = "helloWorld";
	    String testWord2 = "heloWrd";
	    System.out.println("Test Word 1: " + uniqueChars(testWord1));
	    System.out.println("Test Word 2: " + uniqueChars(testWord2));
	    */

	    /*
	    String testWord1 = "hello";
	    String testWord2 = "olelh";
	    String testWord3 = "olelll";
	    System.out.println("Test 1: " + strPermutation(testWord1, testWord2));
	    System.out.println("Test 2: " + strPermutation(testWord1, testWord3));
	    */

	    /*
	    char sentence[] = new char[100];
	    String sentence1 = "Mr John Smith       ";
	    sentence = sentence1.toCharArray();
	    sentence = replaceSpaces(sentence);
	    System.out.println(sentence);
	    */

	    /*
	    String word = "aabcccccaaad";
	    System.out.println(stringComp(word));
	    */

	    /*
	    int[][] multi2 = new int[][]{
		  { 4, 2, 1, 4 },
		  { 5, 9, 3, 0 },
		  { 6, 3, 2, 9 },
		  { 0, 7, 4, 4 },
		  { 1, 2, 7, 6 }
		};
		//printGrid(matrixRotate(multi2));
		//printGrid(rowColZero(multi2));
		*/

		/*
		String str1 = "erbottlewat";
		String str2 = "aterbottlew";
		System.out.println(isRotation(str1, str2));
		*/

		/*
		// initialize a list with 10 data points
		SingleLList testList = new SingleLList();
		testList.addToStart(5);
		testList.addToStart(10);
		testList.addToStart(6);
		testList.addToStart(9);
		testList.addToStart(3);
		testList.addToStart(4);
		testList.addToStart(5);
		testList.addToStart(6);
		testList.addToStart(5);
		testList.addToStart(3);
		testList.addToStart(4);
		//testList.outputList();

		SingleLList testList2 = new SingleLList();
		testList2.addToStart(6);
		testList2.addToStart(1);
		testList2.addToStart(7);

		SingleLList testList3 = new SingleLList();
		testList3.addToStart(2);
		testList3.addToStart(9);
		testList3.addToStart(5);

		SingleLList testList4 = new SingleLList();
		testList4.addToStart(7);
		testList4.addToStart(1);
		testList4.addToStart(6);

		SingleLList testList5 = new SingleLList();
		testList5.addToStart(7);
		testList5.addToStart(1);
		testList5.addToStart(7);
		*/


		Stack<Integer> testStack = new Stack<Integer>();
		testStack.push(2);
		testStack.push(5);
		testStack.push(7);
		//testStack.printStack();


		Queue<Integer> testQueue = new Queue<Integer>();
		testQueue.enqueue(5);
		testQueue.enqueue(10);
		testQueue.enqueue(7);
		//testQueue.printQueue();


		SetOfStacks<Integer> testSetStacks = new SetOfStacks<Integer>(2);
		testSetStacks.push(6);
		testSetStacks.push(2);
		testSetStacks.push(9);
		testSetStacks.push(4);
		testSetStacks.printStacks();


	}

}
