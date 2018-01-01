


class Queue {
	private final int SIZE = 20;
	private int[] queArray;
	private int front;
	private int rear;

	public Queue() {
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}

	public void insert(int j) { // put item at rear of queue
		if (rear == SIZE - 1) {
			rear = -1;
		}
		queArray[++rear] = j;
	}

	public int remove() { // take item from front of queue
		int temp = queArray[front++];
		if (front == SIZE) {
			front = 0;
		}
		return temp;
	}

	public boolean isEmpty() { // true if queue is empty
		return ( rear + 1 == front || (front + SIZE - 1 == rear) );
	}

}
// end class StackX


class Vertex {
	public char label; // name of vertext (i.e. 'A')
	public boolean wasVisited;

	public Vertex(char lab) {
		label = lab;
		wasVisited = false;
	}
}
// end class Vertex


class Graph {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][];		// adjacency matrix
	private int nVerts;			// current number of vertices
	private Queue theQueue;


	public Graph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;

		// set adjacency matrix to all zeros
		for (int j = 0; j < MAX_VERTS; j++) {
			for (int k = 0; k < MAX_VERTS; k++) {
				adjMat[j][k] = 0;
			}
		}

		theQueue = new Queue();
	} // end constructor


	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}


	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}


	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}


	public void bfs() { // depth-first search
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theQueue.insert(0);
		int v2;

		while( !theQueue.isEmpty() ) {
			int v1 = theQueue.remove();
			
			// until it has no unvisited neighbors
			while ( (v2 = getAdjUnvisitedVertex(v1)) != -1) {
				vertexList[v2].wasVisited = true; // mark it
				displayVertex(v2);					// display it
				theQueue.insert(v2);				// insert it
			} // end while
		} // end while

		// queue is empty, so we're done
		for (int j = 0; j < nVerts; j++) {
			vertexList[j].wasVisited = false;
		}
	} // end bfs


	public int getAdjUnvisitedVertex(int v) {
		for (int j = 0; j < nVerts; j++) {
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
				return j;
			}
		}
		return -1;
	}

}


class BFSApp {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A');
		theGraph.addVertex('B');
		theGraph.addVertex('C');
		theGraph.addVertex('D');
		theGraph.addVertex('E');

		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(3, 4); // DE

		System.out.print("Visits: ");
		theGraph.bfs(); // run breadth-first search
		System.out.println();
	} // end main
} // end class BFSApp














