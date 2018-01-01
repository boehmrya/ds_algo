
class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;

	public StackX() {
		st = new int[SIZE];
		top = -1;
	}

	public void push(int j) { // push item on stack
		st[++top] = j;
	}

	public int pop() { // take item off stack
		return st[top--];
	}

	public int peek() { // peek at top of stack
		return st[top];
	}

	public boolean isEmpty() { // true if nothing in stack
		return (top == -1);
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
	private StackX theStack;


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

		theStack = new StackX();
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


	public void dfs() { // depth-first search
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theStack.push(0);

		while( !theStack.isEmpty() ) {
			// get the unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex( theStack.peek() );
			if (v == -1) {
				theStack.pop();
			}
			else {
				vertexList[v].wasVisited = true;
				displayVertex(v);
				theStack.push(v);
			}
		} // end while

		// stack is empty, so we're done
		for (int j = 0; j < nVerts; j++) {
			vertexList[j].wasVisited = false;
		}
	} // end dfs


	public int getAdjUnvisitedVertex(int v) {
		for (int j = 0; j < nVerts; j++) {
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
				return j;
			}
		}
		return -1;
	}

}


class DFSApp {
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
		theGraph.dfs(); // run depth-first search
		System.out.println();
	} // end main
} // end class DFSApp














