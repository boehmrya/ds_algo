




class Solution2 {
    public int solution(int[] A) {
        int i = 0;
        int numHills = 0;
        int numValleys = 0;
        int total = 0;
        boolean onHill = true;
        boolean onValley = true;
        int numChanges = 0;

        while (i < (A.length - 2)) {
        	// special case for the first hill/valley
        	if (i == 0) {
        		if (A[i] < A[i + 1]) {
        			onValley = false;
        			numValleys++;
                    numChanges++;
        		}
        		else if (A[i] > A[i + 1]) {
        			onHill = false;
        			numHills++;
                    numChanges++;
        		}
        		else {
        			onHill = true;
        			onValley = true;
        		}
        	}
        	else { // next case for interior
        		if (onHill == true) {
        			if (A[i] < A[i + 1]) {
        				onHill = true;
        			}
	        		else if (A[i] > A[i + 1]) {
	        			onHill = false;
	        			numHills++;
	        			onValley = true;
                        numChanges++;
	        		}
        			else {
        				onHill = true;
        				onValley = false;
        			}
        		}
        		if (onValley == true) {
        			if (A[i] < A[i + 1]) {
        				onValley = false;
	        			numValleys++;
	        			onHill = true;
                        numChanges++;
        			}
	        		else if (A[i] > A[i + 1]) {
	        			onValley = true;
	        		}
        			else {
        				onValley = true;
        				onHill = false;
        			}
        		}
        	}
        	i++;
        }
        // add one to the very end
        if (numChanges == 0) {
            return 1;
        }
        else {
            total = numHills + numValleys + 1;
            return total;
        } 
    }


    public static void main(String[] args) {
		int[] arr1 = {2,2,3,4,3,3,2,2,1,1,2,5};
		int[] arr2 = {2,2,1,1,3,4};
		int[] arr3 = {2,2,2};
        int[] arr4 = {2,1,0};
        int[] arr5 = {0,1,2};
		Solution2 sol = new Solution2();
		System.out.println(sol.solution(arr3));

		
	}
}