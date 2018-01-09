
import java.io.*;
import java.util.*;
import java.util.Random;


class JukeBox {
	Song[] songs;
	Song currentSong;
	int totalMoney;
	int songIndex;

	public JukeBox(int maxSongs) {
		songs = new Song[maxSongs];
		currentSong = songs[0];
		totalMoney = 0;
		songIndex = 0;

		// add one song to juke box
		Song s1 = new Song("Hey Jude", 1.00, false, songIndex);
		songs[songIndex] = s1;
	}

	public void playSong(Song s) {
		currentSong.turnOff(); // turn off current song first
		int sIndex = s.getSongID(); // turn on new song
		songs[sIndex].turnOn();
		totalMoney += s.getCost(); // add money to total
	}

	public void addSong(String name, double cost) {
		Song newSong = new Song(name, cost, false, songIndex);
		songs[songIndex] = newSong;
		songIndex++;
	}
		
}


class Song {
	String title;
	Double cost;
	boolean isPlaying;
	int songID;

	public Song(String t, double c, boolean ip, int sid) {
		title = t;
		cost = c;
		isPlaying = ip;
		songID = sid;
	}

	public int getIndex() {
		return songID;
	}

	public void turnOff() {
		isPlaying = false;
	}

	public void turnOn() {
		isPlaying = true;
	}

	public double getCost() {
		return cost;
	}

	public int getSongID() {
		return songID;
	}

	public void displaySong() {
		System.out.print("title: " + title + ", " + "cost: " + cost);
		System.out.println();
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

