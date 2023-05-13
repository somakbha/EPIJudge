package epi.chapter5;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {
	@EpiTest(testDataFile = "reverse_digits.tsv")
	
	//The time complexity is 0(n), where n is the number of digits in x.
	public static long reverse(int x) {
		// TODO - you fill in here.

		int xRemaining = Math.abs(x);

		long res = 0;
		while (xRemaining != 0) {
			int q = xRemaining / 10;
			int r = xRemaining % 10;
			res = res * 10 + r;
			xRemaining = q;
		}
		return (x < 0) ? -res : res;

	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "ReverseDigits.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
