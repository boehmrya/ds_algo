
import java.io.*;
import java.util.*;
import java.util.Random;


class ParkingLot {
	StandardSpace[] standardSpaces;
	CompactSpace[] compactSpaces;
	HandicapSpace[] handicapSpaces;
	int sIndex;
	int cIndex;
	int hIndex;

	public ParkingLot(int sc, int cc, int hc) {
		standardSpaces = new StandardSpace[sc];
		compactSpaces = new CompactSpace[cc];
		handicapSpaces = new HandicapSpace[hc];
		sIndex = 0;
		cIndex = 0;
		hIndex = 0;
	}

	public void parkCar() {
		// create the car

		// put the car into the next index

		// increment the index
	}

}


class ParkingSpace {
	 boolean isFull;
	 int id;

	 public ParkingSpace(boolean isFull, int id) {
	 	isFull = if;
	 	id = id;
	 }

	 public int getID() {
	 	return id;
	 }

	 public boolean isFull() {
	 	return isFull;
	 }

	 public boolean setFull() {
	 	isFull = true;
	 }
}


class StandardSpace {
	super();
}


class CompactSpace {
	super();
}


class HandicapSpace {
	super();
}



class Car {
	int size; // 0, standard, 1, compact
	boolean handicap;

	public Car(int size, boolean handicap) {
		size = size;
		handicap = handicap;
	}
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

