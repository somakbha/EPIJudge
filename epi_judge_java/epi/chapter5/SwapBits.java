package epi.chapter5;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SwapBits {
	@EpiTest(testDataFile = "swap_bits.tsv")
	public static long swapBits(long x, int i, int j) {
		// TODO - you fill in here.

		// right shift the number to ) th position and & with 1 to see if the bits at
		// ith and jth are different. if they are equal no need to swap. if they differ
		// flip the bit values because in binary only possible values are 0 and 1
		long iValue = (x >>> i) & 1;
		long jValue = (x >>> j) & 1;

		// XORing with 1 to flip a particular bit. Since our input data is Long
		// data type, it is important that that we should make the bit mask also of
		// Long,otherwise it will be int only default.
		if (iValue != jValue) {
			x = x ^ (1L << i);
			x = x ^ (1L << j);
		}

		return x;
	}

	public static void main(String[] args) {

		System.exit(GenericTest.runFromAnnotations(args, "SwapBits.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());

	}
}
