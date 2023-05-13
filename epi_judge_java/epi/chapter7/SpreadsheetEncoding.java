package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

 // TC:O(n) SC:O(1)
  public static int ssDecodeColID(final String col) {
    // TODO - you fill in here.
	  
	int number=0;
	for(int i=0; i<col.length(); i++)
	{
		char letter=col.charAt(i);
		int digit=(letter-'A')+1;
		number=number*26+digit;
	}
    return number;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
