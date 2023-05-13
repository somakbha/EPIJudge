package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

	public static boolean isPalindrome(String s) {
		// TODO - you fill in here.
		int start = 0;
		int end = s.length() - 1;

		while (start < end) {
			if (!Character.isLetterOrDigit(s.charAt(start))) {
				start++;
				continue;
			} else if (!Character.isLetterOrDigit(s.charAt(end))) {
				end--;
				continue;
			} else {
				if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
					return false;
				} else {
					start++;
					end--;
				}
			}
		}

		return true;
	}

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
