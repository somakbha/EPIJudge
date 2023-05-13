package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromic {
	  @EpiTest(testDataFile = "is_string_palindromic.tsv")

	  public static boolean isPalindromic(String s) {
	    // TODO - you fill in here.
		if(s==null)
			return true;
		
		for(int i=0; i<s.length()/2; i++)
		{
			if (s.charAt(i)!=s.charAt(s.length()-1-i))
			{
				return false;
			}
		}
	    return true;
	  }

  public static void main(String[] args) {
	  
   System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
