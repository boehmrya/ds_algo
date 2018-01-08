

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

	// number of bits to flip num1 to num2
	public static int numFlips(int num1, int num2) {
		StringBuilder num1String = new StringBuilder();
		StringBuilder num2String = new StringBuilder();
		int num1Len = 0;
		int num2Len = 0;
		int diff = 0;
		int numFlips = 0;

		// get bit strings of num1 and num2
		num1String = binString(num1);
		num2String = binString(num2);
		num1Len = num1String.length();
		num2Len = num2String.length();

		// build out strings if they have different lengths
		if (num1Len < num2Len) {
			diff = num2Len - num1Len;
			num1String.reverse();
			int i = 0;
			while (i < diff) {
				num1String.append("0");
				i++;
			}
			num1String.reverse();
		}
		else if (num2Len < num1Len) {
			diff = num1Len - num2Len;
			int i = 0;
			while (i < diff) {
				num2String.append("0");
				i++;
			}
			num2String.reverse();
		}

		// check number of flips necessary by comparing strings
		int i = 0;
		while(i < num1String.length()) {
			char c1 = num1String.charAt(i);
			char c2 = num2String.charAt(i);
			if (c1 != c2) {
				numFlips++;
			}
			i++;
		}
		return numFlips;
	}

	

	public static void main(String[] args) {
		int num1 = 23;
		int num2 = 15;
		System.out.println(numFlips(num1, num2));

		
	}
}