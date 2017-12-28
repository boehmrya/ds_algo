



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

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
	    String testWord1 = "helloWorld";
	    String testWord2 = "heloWrd";
	    System.out.println("Test Word 1: " + uniqueChars(testWord1));
	    System.out.println("Test Word 2: " + uniqueChars(testWord2));
	}

}
