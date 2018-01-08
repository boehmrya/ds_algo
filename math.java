
import java.util.*;
import java.lang.Math;

class Line {
	private int yIntercept;
	private int slope;

	public Line(int y, int s) {
		yIntercept = y;
		slope = s;
	}

	public int getIntercept() {
		return yIntercept;
	}

	public int getSlope() {
		return slope;
	}
}


class MathPractice {

	public static boolean linesCross(Line line1, Line line2, int test) {
		int x = 0;
		int slope1 = line1.getSlope();
		int slope2 = line2.getSlope();
		int intercept1 = line1.getIntercept();
		int intercept2 = line2.getIntercept();

		// starting points for line one and two
		int y1 = intercept1;
		int y2 = intercept2;

		// test = how many x values to test up to
		while (x < test) {
			if (y1 == y2) {
				return true;
			}
			y1 += slope1;
			y2 += slope2;
			x++;
		}
		return false;
	}


	public static int multiply(int num1, int num2) {
		int total = 0;
		while(num2 > 0) {
			total += num1;
			num2--;
		}
		return total;
	}


	public static int subtract(int num1, int num2) {
		int total = num1 + -(num2);
		return total;
	}


	public static int divide(int num1, int num2) {
		int count = 0;
		boolean diffSign = false;
		// determine if they have different signs
		if ((num2 < 0 && num1 > 0) || (num2 > 0 && num1 < 0)) {
			diffSign = true;
		}

		// convert everything to positive
		if (num2 < 0) {
			num2 = (-num2);
		}
		if (num1 < 0) {
			num1 = (-num1);
		}

		// count subtractions to 0
		while(num1 > 0) {
			num1 += (-num2);
			count++;
		}

		// change sign if necessary
		if (diffSign == true) {
			count = (-count);
		}
		return count;
	}


	public static void main(String[] args) {
		System.out.println(divide(-10, 5));


		
	}
}

