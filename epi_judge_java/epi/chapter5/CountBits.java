package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")

	/*
	 * Bitwise Left Shift Operator | << |The left shift operator moves all bits by a given
	 * number of bits to the left. 
	 * 
	 * Bitwise Right Shift Operator | >> |The right shift operator
	 * moves all bits by a given number of bits to the right. 
	 * 
	 * Bitwise Zero Fill Right Shift Operator |
	 * >>> |It is the same as the signed right shift, but the vacant leftmost
	 * position is filled with 0 instead of the sign bit.
	 */
  public static short countBits(int x) {
    // TODO - you fill in here.
	  short cnt=0;
	  	  
	  while(x!=0)
	  {
		  if((x&1)==1)
			  cnt++;
		  x>>>=1;
	  }
    return cnt;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
