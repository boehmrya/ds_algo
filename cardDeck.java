

import java.io.*;
import java.util.*;
import java.util.Random;


class Deck {
	private Card[] cards;
		
	public Deck() {
		String suit;
		int rank;

		for(int i = 0; i < 52; i++) {
			// determine rank
			rank = getRank(i);
			suit = getSuit(i);

			Card thisCard = new Card(suit, rank);
			cards[i] = thisCard;
		}
	}


	// converts index to a rank
	public String getSuit(int index) {
		String suit;

		if (index < 13) {
			suit = "diamonds";
		}
		else if (index > 13 && index < 26) {
			suit = "hearts";
		}
		else if (index > 26 && index < 39) {
			suit = "clubs";
		}
		else {
			suit = "spades";
		}
		return suit;
	}


	// converts index to a rank
	public int getRank(int index) {
		return (index % 13);
	}


	// get a card based on suit and rank
	public Card getCard(String suit, int rank) {
		int base;
		int index;
		if (suit == "diamonds") {
			base = 0;
		}
		else if (suit == "hearts") {
			base = 13;
		}
		else if (suit == "clubs") {
			base = 26;
		}
		else {
			base = 39;
		}
		index = base + rank;
		return cards[index];
	}

	// shuffle cards
	public void shuffle() {
		
	}
}


class Card {
	private String suit;
	private int rank; // ace is low
	private boolean isDealt;

	// ace, two, three, four, five, six, seven, eight, nine, jack, queen, king
	public Card(String s, int r) {
		suit = s;
		rank = r;
		isDealt = false;
	}

	public boolean getDealt() {
		return isDealt;
	}

	public void setDealt(boolean status) {
		isDealt = status;
	}

	public void displayCard() {
		System.out.print("Suit: " + suit + ", " + "Rank: " + rank);
	}
}


class Dealer {
	private String name;
	private Deck cardDeck;

	public Dealer(String n, Deck d) {
		name = n;
		cardDeck = d;
	}

	// deal a card to the player
	public Card dealCard() {
		Random rand = new Random();
		int  n = rand.nextInt(52);
		String suit = cardDeck.getSuit(n);
		int rank = cardDeck.getRank(n);
		Card newCard = cardDeck.getCard(suit, rank);

		// keep trying until you find a card that hasn't been dealt
		while (newCard.getDealt() == true) {
			n = rand.nextInt(52);
			suit = cardDeck.getSuit(n);
			rank = cardDeck.getRank(n);
			newCard = cardDeck.getCard(suit, rank);
		}
		newCard.setDealt(true); // set card to dealt
		return newCard;
	}
}


class Player {
	private String name;
	private int age;

	public Player(String n, int a) {
		name = n;
		age = a;
	}

	// 1 for hit me, 0 for fold
	public int makeMove(int move) {
		if (move != 0 || move != 1) {
			System.out.println("Error: only 0 or 1 accepted");
		}
		return move;
	}
}



class cardDesk {

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