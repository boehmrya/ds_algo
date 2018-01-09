
import java.io.*;
import java.util.*;
import java.util.Random;


class Puzzle {
	Piece[][] grid;
	Piece[] allPieces

	public Puzzle(int numPieces) {
		int w = numPieces / 2;
		int h = numPieces / 2;
		grid = new Piece[w][h];
		allPieces = new Piece[numPieces];

		// create all pieces
	}
}


class Piece {
	int id;
	int type; // middle, edge, corner
	boolean rightOut;
	boolean topOut;
	boolean leftOut;
	boolean bottomOut;

	int[] colors; // list of rgb pixels

	boolean fitsWith(Piece other) {
		// if other piece has compatible openings

		// check if pixel values match on sides that could connect

		// if both conditions true, you have a match
	}
}


class Player {
	String name;
	Puzzle board;

	// select piece
	public Piece selectPiece() {
	}

	// play a piece
	public void playPiece() {	
	}

	// quit game
	public void quitGame() {
	}


}

class Game {
	// create puzzle
	// create player
	// play game method (go until board is full or player quits game)
}




class Main {

	public static void main(String[] args) {
		
	}

	// IO functions
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
}

