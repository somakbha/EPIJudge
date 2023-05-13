package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // TODO - you fill in here.
	long result=0L;
	long shift=0;
	while(y!=0)
	{
		if ((y&1L)==1)
			result=addBitbyBit(result, x<<shift);
			//result+=(x<<shift); //we are not allowed to add + operator in this problem
		shift++;
		y=y>>>1;
	}
    return result;
  }
  
	/*
	 * Here we have used the logic logic bit differently as described in the video
	 * "Add Two Numbers Without The _+_ Sign (Bit Shifting Basics).mp4". Also the
	 * same approach is also described in leetcode Approach 2: Bit Manipulation
	 * (https://leetcode.com/problems/add-binary/solution/)
	 */  
  public static long addBitbyBit(long n1, long n2)
	{
		while (n2 != 0) {
			// find out the positions where carry would be generated, it would be places
			// where both the bit is 1

			long carry = n1 & n2;

			// now do the actual addition with the help of XOR operator and store it in n1

			n1 = n1 ^ n2;

			// shift the carry to the right 1 place to add it back to the actual number

			n2 = carry << 1;

		}
		return n1;

	}

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
