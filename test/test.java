


import java.util.*;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

	// convert string time to number of seconds
	public static int stringToTime(String S) {
		// break each portion into strings
		String[] time = S.split(":");
		String hours = time[0];
		String minutes = time[1];
		String seconds = time[2];

		// convert each portion to integers
		int hoursInt = Integer.parseInt(hours) * 60 * 60;
		int minutesInt = Integer.parseInt(minutes) * 60;
		int secondsInt = Integer.parseInt(seconds);

		// add each integer portion and return
		int total = hoursInt + minutesInt + secondsInt;
		return total;
	}


	// convert integer number of seconds to a string with format HH:MM:SS
	public static String intToTime(int i) {
		int result = i;
		
		// get number of hours
		int hourCount = 0;
		while (result >= 3600) {
			//System.out.println(result);
			result = result - 3600;
			hourCount++;
		}
		String hourString = Integer.toString(hourCount);
		if (hourCount < 10 || hourCount == 0) {
			hourString = "0" + hourString;
		}

		// get number of minutes
		int minCount = 0;
		while (result >= 60) {
			//System.out.println(result);
			result = result - 60;
			minCount++;
		}
		String minString = Integer.toString(minCount);
		if (minCount < 10 || minCount == 0) {
			minString = "0" + minString;
		}


		// get number of seconds
		String secString = Integer.toString(result);
		if (result < 10 || result == 0) {
			secString = "0" + secString;
		}

		String timeString = hourString + ":" + minString + ":" + secString;
		return timeString;
	}


	// takes a string time with format HH:MM:SS and counts distinct digits
	public static int distDigits(String s) {
		LinkedList uniqueChars = new LinkedList();
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c != ':' && !uniqueChars.contains(c)) {
				count++;
				uniqueChars.add(c);
			}
		}
		return count;
	}


    public int solution(String S, String T) {
    	// convert to ints
    	// assume that s is initially less than t
    	int sSecs = stringToTime(S);
    	int tSecs = stringToTime(T);
    	String sTime = intToTime(sSecs);
    	int count = 0;

    	// count number of interesting times
        while (sSecs <= tSecs) {
        	sTime = intToTime(sSecs);
			if (distDigits(sTime) <= 2) {
				count++;
			}
			sSecs = stringToTime(sTime);
			sSecs++;        	
        }
        return count;
    }
  

    public static void main(String[] args) {
		String s1 = "15:15:00";
		String s2 = "15:15:12";
		String s3 = "00:00:00";
		String s4 = "23:59:59";
		int s1i = stringToTime(s1);
		//System.out.println(s1i);
		//System.out.println(intToTime(s1i));

		int s2i = stringToTime(s2);
		//System.out.println(s2i);
		//System.out.println(intToTime(s2i));

		int s3i = stringToTime(s3);
		//System.out.println(s3i);
		//System.out.println(intToTime(s3i));

		int s4i = stringToTime(s4);
		//System.out.println(s4i);
		//System.out.println(intToTime(s4i));

		Solution sol = new Solution();
		System.out.println(sol.solution(s3, s3));

		
	} 
}













