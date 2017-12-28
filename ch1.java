

import java.lang.StringBuffer;
import java.util.Arrays;

/**
 * @author Ryan Boehm
 */
public class ch1 {

	/*
	* determines if a string has unique characters
	*/
	public static boolean uniqueChars( String word ) {
		for (int i = 0; i < word.length(); i++) {
            char c1 = word.charAt(i);
            for (int j = (i + 1); j < word.length(); j++) {
            	char c2 = word.charAt(j);
               	if (c1 == c2) {
               		return false;
               	}
       		}    
       	}
       	return true;
	}


	/*
	* determines if string 2 is a permutation of string 1
	*/
	public static boolean strPermutation(String str1, String str2) {
		int i, j;
		char c1, c2;

		// false if not the same length
		if (str1.length() != str2.length()) {
			return false;
		}

		StringBuffer newStr2 = new StringBuffer(str2);
		i = 0;

		while (i < str1.length()) {
			j = 0;
			c1 = str1.charAt(i);
			while (j < newStr2.length()) {
				c2 = newStr2.charAt(j);
				if (c1 == c2) {
					newStr2.deleteCharAt(j);
				}
				j++;
			}
			i++;
		}
		if (newStr2.length() == 0) {
			return true;
		} 
		return false;
	}


	/*
	* returns the last non white space char in sentence
	*/
	public static int lastLetter(char sentence[]) {
		int i = (sentence.length - 1);
		while (sentence[i] == ' ' || sentence[i] == '\t' || sentence[i] == '\n') {
			i--;
		}
		return i;
	}


	/*
	* replace each space in character array with a %20
	*/
	public static char[] replaceSpaces(char sentence[]) {
		for (int i = 0; i < lastLetter(sentence); i++) {
			if (sentence[i] == ' ') {
				// rotate remaining characters back two slots
				for (int j = lastLetter(sentence); j > i; j--) {
					sentence[j + 2] = sentence[j];
				}
				// replace chars
				sentence[i] = '%';
				sentence[i + 1] = '2';
				sentence[i + 2] = '0';	
			}
		}
		return sentence;
	}


	/*
	* compresses a string
	*/
	public static String stringComp(String str) {
		StringBuffer compStr = new StringBuffer();
		boolean newWord = true;
		int count = 0;
		char c1, c2, numChar;
		String num;

		for (int i = 0; i < str.length(); i++) {
			c1 = str.charAt(i);
			if (i > 0) {
				c2 = str.charAt(i - 1);
				// increase count if current char is same as previous
				if (c1 == c2) {
					count++;
				}
				else {
					// add char and number of times on character transition
					num = Integer.toString(count);
					numChar = num.charAt(0);
					compStr.append(numChar);
					compStr.append(c1);
					count = 1;
				}
			}
			else {
				compStr.append(c1);
				count++;
			}
		}
		// add count for final character
		num = Integer.toString(count);
		numChar = num.charAt(0);
		compStr.append(numChar);

		if (compStr.length() == str.length()) {
			return str;
		}
		return compStr.toString();
	}


	/*
	* rotates and m x n matrix 90 degrees
	*/
	public static int[][] matrixRotate( int[][] multi ) {
		int i, j;
		int h = multi.length;
		int w = multi[0].length;
		// normall multi[h][w] - change to multi[w][h] to rotate
		int[][] multiRotate = new int[w][h];
		i = 0;
		while (i < h) {
			j = 0;
			while (j < w) {
				multiRotate[j][i] = multi[i][j];
				j++;
			}
			i++;
		}
		return multiRotate;
	}


	public static void printGrid( int[][] multi ) {
		int h = multi.length;
		int w = multi[0].length;
   		for (int i = 0; i < h; i++) {
      		for (int j = 0; j < w; j++) {
         		System.out.print(multi[i][j]);
      		}
      		System.out.println();
   		}
	}


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/*
	    String testWord1 = "helloWorld";
	    String testWord2 = "heloWrd";
	    System.out.println("Test Word 1: " + uniqueChars(testWord1));
	    System.out.println("Test Word 2: " + uniqueChars(testWord2));
	    */

	    /*
	    String testWord1 = "hello";
	    String testWord2 = "olelh";
	    String testWord3 = "olelll";
	    System.out.println("Test 1: " + strPermutation(testWord1, testWord2));
	    System.out.println("Test 2: " + strPermutation(testWord1, testWord3));
	    */

	    /*
	    char sentence[] = new char[100];
	    String sentence1 = "Mr John Smith       ";
	    sentence = sentence1.toCharArray();
	    sentence = replaceSpaces(sentence);
	    System.out.println(sentence);
	    */

	    /*
	    String word = "aabcccccaaad";
	    System.out.println(stringComp(word));
	    */

	    int[][] multi2 = new int[][]{
		  { 4, 2, 1 },
		  { 5, 9, 3 },
		  { 6, 3, 1 },
		  { 0, 7, 4 },
		  { 1, 2, 7 }
		};
		printGrid(matrixRotate(multi2));

	}

}
