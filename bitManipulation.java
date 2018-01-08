

import java.lang.StringBuilder;


class bitManipulation {

	// calculates the number of one bits in a binary string
	public static int numBits(int num) {
		int count = 0;
		while(num > 0) {
			if ((num & 1) == 1) {
				count++;
			}
			num = num >> 1;
		}
		return count;
	}

	// converts int to binary string representation
	public static StringBuilder binString(int num) {
		StringBuilder number = new StringBuilder();
		while(num > 0) {
			if ((num & 1) == 1) {
				number.append("1");
			}
			else {
				number.append("0");
			}
			num = num >> 1;
		}
		number.reverse();
		return number;
	}

	public static void printNext(int num) {
		StringBuilder nextSmallest = new StringBuilder();
		StringBuilder nextLargest = new StringBuilder();
		StringBuilder number = new StringBuilder();
		int smaller;
		int larger;
		int count;

		// convert number to string builder representation in binary
		count = numBits(num);
		number = binString(num);

		// next smallest number binary string
		smaller = num - 1;
		while(numBits(smaller) != count) {
			smaller--;
		}
		nextSmallest = binString(smaller);
		
		// next largest number binary string
		larger = num + 1;
		while(numBits(larger) != count) {
			larger++;
		}
		nextLargest = binString(larger);

		// print values
		System.out.println("Number: " + number);
		System.out.println("Next Smallest: " + nextSmallest);
		System.out.println("Next largest: " + nextLargest);
	}

	

	public static void main(String[] args) {
		int num = 27;
		printNext(num);
		
	}
}