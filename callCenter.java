
import java.io.*;
import java.util.*;
import java.util.Random;


class CallCenter {
	protected Respondent[] respondents;
	protected Manager[] managers;
	protected Director[] directors;
	protected int rIndex;
	protected int mIndex;
	protected int dIndex;
	
	public CallCenter(int numR, int numM, int numD) {
		// create arrays for different employees
		respondents = new Respondent[numR];
		managers = new Manager[numM];
		directors = new Director[numD];
		rIndex = 0;
		mIndex = 0;
		dIndex = 0;

		// create initial employees
		Respondent r1 = new Respondent(false);
		Manager m1 = new Manager(false);
		Director d1 = new Director(false);

		// add to arrays
		respondents[rIndex] = r1;
		rIndex++;
		managers[mIndex] = m1;
		mIndex++;
		directors[dIndex] = d1;
		dIndex++;
	}

	public void dispatchCall() {
		// check if respondents are busy
		for (int i = 0; i < respondents.length; i++) {
			if (respondents[i] != null) {
				if (respondents[i].getBusyStatus() == false) {
					respondents[i].setBusyStatus(true);
					return;
				}
			}
		}

		// check if managers are busy
		for (int i = 0; i < managers.length; i++) {
			if (managers[i] != null) {
				if (managers[i].getBusyStatus() == false) {
					managers[i].setBusyStatus(true);
					return;
				}
			}
		}

		// check if directors are busy
		for (int i = 0; i < directors.length; i++) {
			if (directors[i] != null) {
				if (directors[i].getBusyStatus() == false) {
					directors[i].setBusyStatus(true);
					return;
				}
			}
		}
	}

	public void addEmployee(int type) {
		if (type == 0) { // if respondent
			Respondent r = new Respondent(false);
			respondents[rIndex] = r;
			rIndex++;	
		}
		else if (type == 1) { // if respondent
			Manager m = new Manager(false);
			managers[mIndex] = m;
			mIndex++;	
		}
		else if (type == 2) { // if respondent
			Director d = new Director(false);
			directors[dIndex] = d;
			dIndex++;	
		}
		else {
			System.out.println("Wrong code for employee creation");
		}
	}

}


class Employee {
	protected boolean isBusy;

	public Employee(boolean ib) {
		isBusy = ib;
	}

	public boolean getBusyStatus() {
		return isBusy;
	}

	public void setBusyStatus(boolean ib) {
		isBusy = ib;
	}
}


class Respondent extends Employee {

	public Respondent(boolean ib) {
		super(ib);
	}
}


class Manager extends Employee {

	public Manager(boolean ib) {
		super(ib);
	}
}


class Director extends Employee {

	public Director(boolean ib) {
		super(ib);
	}
}



class Center {

	public static void main(String[] args) {
		CallCenter c1 = new CallCenter(10, 10, 10);
		c1.dispatchCall();
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