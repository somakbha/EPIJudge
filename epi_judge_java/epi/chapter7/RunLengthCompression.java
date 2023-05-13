package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class RunLengthCompression {


	public static String decoding(String s) {
		// TODO - you fill in here.
		StringBuilder rleDec = new StringBuilder();
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {

			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				cnt = cnt * 10 + (ch - '0');

			} else {
				//along with print the characters cnt times, the cnt will also reset to zero after the loop completes
				while (cnt > 0) {
					rleDec.append(ch);
					cnt--;
				}
			
			}

		}
		return rleDec.toString();
	}

	public static String encoding(String s) {
		// TODO - you fill in here.
		StringBuilder rleEnc = new StringBuilder();
		for (int i = 0; i < s.length(); ) {
			int j = i + 1;

			while (j < s.length() && s.charAt(j) == s.charAt(i)) {
				j++;
			}
			rleEnc.append((j - i)).append(s.charAt(i));
			i = j;
		}
		return rleEnc.toString();
	}
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
