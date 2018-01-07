// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;
////////////////////////////////////////////////////////////////

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




class Node
   {
   private int iData;             // data item (key)
// -------------------------------------------------------------
   public Node(int key)           // constructor
      { iData = key; }
// -------------------------------------------------------------
   public int getKey()
      { return iData; }
// -------------------------------------------------------------
   public void setKey(int id)
      { iData = id; }
// -------------------------------------------------------------
   }  // end class Node
////////////////////////////////////////////////////////////////

class Heap
   {
   private Node[] heapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of nodes in array
// -------------------------------------------------------------
   public Heap(int mx)            // constructor
      {
      maxSize = mx;
      currentSize = 0;
      heapArray = new Node[maxSize];  // create array
      }
// -------------------------------------------------------------
   public boolean isEmpty()
      { return currentSize==0; }
// -------------------------------------------------------------
   public int getSize() { 
      return currentSize; 
   }
// -------------------------------------------------------------
   public Node peek() { // returns (but does not remove) max item
      return heapArray[0];
   }
// -------------------------------------------------------------
   public Node find(int key) { // assumes no duplicates
      for (int i = 0; i < currentSize; i++) {
         if (heapArray[i].getKey() == key) {
            return heapArray[i];
         }
      }
      return null;
   }  // end insert()
// -------------------------------------------------------------
   public Node getRightChild(int key) {
      for (int i = 0; i < currentSize; i++) {
         if (heapArray[i].getKey() == key) {
            int rightChild = 2*i+1;
            if (rightChild < currentSize) {
               return heapArray[rightChild];
            }
            else {
               return null;
            } 
         }
      }
      return null;
   }
// -------------------------------------------------------------
   public Node getLeftChild(int key) {
      for (int i = 0; i < currentSize; i++) {
         if (heapArray[i].getKey() == key) {
            int leftChild = 2*i+2;
            if (leftChild < currentSize) {
               return heapArray[leftChild];
            }
            else {
               return null;
            } 
         }
      }
      return null;
   }
// -------------------------------------------------------------
   public boolean insert(int key)
      {
      if(currentSize==maxSize)
         return false;
      Node newNode = new Node(key);
      heapArray[currentSize] = newNode;
      trickleUp(currentSize++);
      return true;
      }  // end insert()
// -------------------------------------------------------------
   public void trickleUp(int index)
      {
      int parent = (index-1) / 2;
      Node bottom = heapArray[index];

      while( index > 0 &&
             heapArray[parent].getKey() < bottom.getKey() )
         {
         heapArray[index] = heapArray[parent];  // move it down
         index = parent;
         parent = (parent-1) / 2;
         }  // end while
      heapArray[index] = bottom;
      }  // end trickleUp()
// -------------------------------------------------------------
   public Node remove()           // delete item with max key
      {                           // (assumes non-empty list)
      Node root = heapArray[0];
      heapArray[0] = heapArray[--currentSize];
      trickleDown(0);
      return root;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(int index)
      {
      int largerChild;
      Node top = heapArray[index];       // save root
      while(index < currentSize/2)       // while node has at
         {                               //    least one child,
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         // find larger child
         if(rightChild < currentSize &&  // (rightChild exists?)
                             heapArray[leftChild].getKey() <
                             heapArray[rightChild].getKey())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         // top >= largerChild?
         if( top.getKey() >= heapArray[largerChild].getKey() )
            break;
                                         // shift child up
         heapArray[index] = heapArray[largerChild];
         index = largerChild;            // go down
         }  // end while
      heapArray[index] = top;            // root to index
      }  // end trickleDown()
// -------------------------------------------------------------
   public boolean change(int index, int newValue)
      {
      if(index<0 || index>=currentSize)
         return false;
      int oldValue = heapArray[index].getKey(); // remember old
      heapArray[index].setKey(newValue);  // change to new

      if(oldValue < newValue)             // if raised,
         trickleUp(index);                // trickle it up
      else                                // if lowered,
         trickleDown(index);              // trickle it down
      return true;
      }  // end change()
// -------------------------------------------------------------
   // checks if other heap is a sub-heap of this heap
   // returns true if so, otherwise false
   public boolean isSubHeap(Heap other) {
      // if other heap is larger than this one, it's not a subtree
      if (currentSize < other.getSize()) {
         return false;
      }
      boolean status = false;

      for (int i = 0; i < currentSize; i++) {
         // if current node of heap 1 equals root of heap 2, then
         // stop and compare all children
         if (heapArray[i].getKey() == other.peek().getKey()) { 
            status = true;
            LinkList list1 = new LinkList();
            LinkList list2 = new LinkList();
            inOrder(heapArray[i], list1);
            other.inOrder(other.peek(), list2);

            // compare lists of traversed nodes to see if they are equal
            while (!list1.isEmpty()) {
               if (list1.deleteFirst().iData != list2.deleteFirst().iData) {
                  status = false; 
                  break;
               }
            }
            // if list 2 is not empty, then not a subtree
            if (status == true && !list2.isEmpty()) {
               status = false;
            }
         }
      }
      return status;
   }

   public void inOrder(Node localRoot, LinkList list) {
      if (localRoot != null) {
         inOrder(getLeftChild(localRoot.getKey()), list);
         list.insertFirst(localRoot.getKey(), 0.0);
         inOrder(getRightChild(localRoot.getKey()), list);
      }
   }

// -------------------------------------------------------------
   public void displayHeap()
      {
      System.out.print("heapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(heapArray[m] != null)
            System.out.print( heapArray[m].getKey() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // heap format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each heap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(heapArray[j].getKey());

         if(++j == currentSize)           // done?
            break;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
         }  // end for
      System.out.println("\n"+dots+dots); // dotted bottom line
      }  // end displayHeap()
// -------------------------------------------------------------
   }  // end class Heap
////////////////////////////////////////////////////////////////
class HeapApp

   {
   public static void main(String[] args) throws IOException
      {
      int value, value2;
      Heap theHeap = new Heap(31);  // make a Heap; max size 31
      boolean success;

      theHeap.insert(70);           // insert 10 items
      theHeap.insert(40);
      theHeap.insert(50);

      Heap theHeap2 = new Heap(31);
      theHeap2.insert(70);
      theHeap2.insert(40);
      theHeap2.insert(50);

      System.out.println(theHeap.isSubHeap(theHeap2));


     
      }  // end main()
//-------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//-------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
//-------------------------------------------------------------
  }  // end class HeapApp
////////////////////////////////////////////////////////////////
