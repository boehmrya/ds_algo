
import java.io.*;
import java.util.*;
import java.util.Random;


class Library {
	Book[] books;
	Customer[] customers; // current customers viewing the library

	public Library(int capacity) {
	}

	public void addBook(String name, double price, int numPages, String author, int index) {
		// create book
		// add to book array
	}

	// buy a book
	public buyBook() {
	}

	// new customer
	public customerEnters() {
		// create customer
		// add to customer array
	}

	// customer exits
	public customerLeaves() {
		// remove customer from array
	}
}


class Book {
	String name;
	double price;
	int numPages;
	String author;
	int index;

	public Book(name, price, numPages, author, index) {
	}

	public getPrice() {
	}
}


class Customer {
	String name;
	String address;
	Order[] orders;

	public void buyBook(String name) {
	}

	public void createOrder() {	
	}
}


class Order {
	Books[] books;
	int total;
	int amountDue;
	Customer cust;
	String status; // complete, pending, etc.

	public void checkOut(int amountPaid) {
		// subtract amount paid from amount due

		// update status
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

