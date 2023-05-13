package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
	
	public static short parity_v1(long x) {
		// TODO - you fill in here.

		short res = 0;
		while (x != 0) {
			if ((x & 1) == 1)
				// as res data type is short so we store the modulo value of 2 for res as we
				// only
				// need to know if number of ones are even or odd for parity. if number of ones
				// are odd then we should return 1 and if even we should return 0.
				res = (short) ((res + 1) % 2);
			x = x >>> 1;
		}

		return res;
	}

	//O(number of bits). here the only from parity_v1 is the way we calculate modulo 2
	public static short parity_v2(long x) {
		// TODO - you fill in here.

		short res = 0;

		// The XOR of two one-bit numbers is their sum modulo 2. Hence we can rewrite
		// the logic as follows also
		while (x != 0) {
			res ^= (x & 1); // XOR truth table 0 XOR 0=0; 1 XOR 0=1; 0 XOR 1=1; 1 XOR 1=0; so when we first
							// encounter 1 it is 0 XOR 1, so res =1, add second encounter it is 1 XOR 1=0;
			x = x >>> 1; // This also matches with number of 1s odd, value of res is 1, if number of 1s
							// are even, value of res is 0
		}
		return res;
	}
	
	/*
	 * O(number of bits) but if in best and average cases if the number has fewer
	 * bits set, the loop will run only for that many times. Let k be the number of
	 * bits set to 1 in a particular word. (For example, for 10001010, k = 3.) Then
	 * time complexity of the algorithm above is O(k).
	 */
	public static short parity_v3(long x) {
		// TODO - you fill in here.

		short res = 0;

		while (x != 0) {
			long y = (x & ~(x - 1)); // detailed logic put in notes also EPI page 38. The variable y is1at exactly the lowest bit of x that is1; all other bits in y are 0.
			x = x ^ y; //removes the lowest bit from x
			res ^= 1;
		}
		return res;
	}

  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // TODO - you fill in here.
	  
	  	//return parity_v1(x);
	  	//return parity_v2(x);
	  	return parity_v3(x);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
