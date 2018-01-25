



class Solution3 {

	// returns the max element in the array
	public static int max(int[] A) {
		int m = A[0];
		int i = 0;
		while (i < A.length) {
			if (A[i] > m) {
				m = A[i];
			}
			i++;
		}
		return m;
	}


	public static boolean canJump(int[] A, int D, int t) {
		// start from back
		int pos = A.length - 1;
		int gap = 1;

		// move toward front
		// expand gap, but set to zero when you find stone at time less than t
		// if gap is greater than D, then we can't jump at this time
		while (pos > -1) {			
			if (gap > D) {
				return false;
			}
			else if ((A[pos] > -1) && (A[pos] <= t)) {
				gap = 1;
			}
			else {
				gap++;
			}
			pos--;
		}
		if (gap > D) {
			return false;
		}
		else {
			return true;
		}
	}


    public int solution(int[] A, int D) {
        int maxTime = max(A);
        int time = 0;

        // go until we can jump across
        // return -1 if we reach max time
        while (time <= maxTime) {
        	if (canJump(A, D, time) == true) {
        		return time;
        	}
        	time++;	
		}
		return -1;
    }

    public static void main(String[] args) {
		int[] a1 = {1,2,3,4,-1,-1,-1};
		int[] a2 = {3,2,1};
		int[] a3 = {1,-1,0,2,3,5};
		int d1 = 1;
		int d2 = 3;
		Solution3 sol = new Solution3();
		System.out.println(sol.solution(a1, d2));

		
	}
}