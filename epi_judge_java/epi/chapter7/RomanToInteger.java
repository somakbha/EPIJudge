package epi.chapter7;
import java.util.HashMap;
import java.util.HashSet;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

    //my initial approach was to scan from left to right which is coded in my first approach.
   //But after reading EPI solution, it is better to scan to string from right to left.
  //The code is bit easy to handle the index part
	public static int romanToInteger(String s) {
		// TODO - you fill in here.
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

		/*
		 * I being 1, V being 5, X being 10, L being 50, C being 100, D being 500, and M
		 * being 1000
		 */
		hm.put('I', 1);
		hm.put('V', 5);
		hm.put('X', 10);
		hm.put('L', 50);
		hm.put('C', 100);
		hm.put('D', 500);
		hm.put('M', 1000);

		int result = hm.get(s.charAt(s.length() - 1));

		for (int idx = s.length() - 2; idx >= 0; idx--) {
			if (hm.get(s.charAt(idx)) < hm.get(s.charAt(idx + 1))) {
				result -= hm.get(s.charAt(idx));

			} else {
				result += hm.get(s.charAt(idx));

			}
		}
		return result;
	}
	public static int romanToInteger_1stApproach(String s) {
		// TODO - you fill in here.
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

		/*
		 * I being 1, V being 5, X being 10, L being 50, C being 100, D being 500, and M
		 * being 1000
		 */
		hm.put('I', 1);
		hm.put('V', 5);
		hm.put('X', 10);
		hm.put('L', 50);
		hm.put('C', 100);
		hm.put('D', 500);
		hm.put('M', 1000);

		int idx = 0;
		int result = 0;

		while (idx < s.length()) {
			if ((idx + 1) < s.length() && hm.get(s.charAt(idx)) < hm.get(s.charAt(idx + 1))) {
				result += hm.get(s.charAt(idx + 1)) - hm.get(s.charAt(idx));
				idx+=2;
			} else {
				result += hm.get(s.charAt(idx));
				idx+=1;
			}
		}
		return result;
	}

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
