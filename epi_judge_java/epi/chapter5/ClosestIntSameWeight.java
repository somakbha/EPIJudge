package epi.chapter5;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
	@EpiTest(testDataFile = "closest_int_same_weight.tsv")
	public static long closestIntSameBitCount(long x) {
		// TODO - you fill in here.
		//always assign L or l against long data type variable https://stackoverflow.com/questions/18756602/assigning-a-value-to-long-data-type-in-java-defaulting-to-int
		long bitMask = 3L;
		long shift = 0;
		long origX = x;

		//we have to basically found a pair 10 or 01 in the number. 
		//Once we get that we reverse those two bits
		//variable shift helps us to track bit position where exactly the pattern 10 or 01 found.
		//EPI implementation is little different from mine
		// time Complexity is O(n) as we have to traverse entire number
		while (x != 0) {
			if (((x & bitMask) == 2) || ((x & bitMask) == 1)) {
				return origX ^ (bitMask << shift);
			}

			x = x >>> 1;
			shift++;
		}
		throw new IllegalArgumentException("all bits are set to 0 or 1");
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "ClosestIntSameWeight.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
