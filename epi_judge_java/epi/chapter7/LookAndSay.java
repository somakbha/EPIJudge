package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")
  //I have updated the TC in the book at the page: 118
  public static String lookAndSay(int n) {
    // TODO - you fill in here.
	String lookAndSay="1";
	for(int i=1; i<n; i++)
	{
		lookAndSay=buildLookAndSay(lookAndSay);
		
	}
    return lookAndSay;
  }
  
  static String buildLookAndSay(String str)
  {
	  int start=0;
	  int end=0;
	  StringBuilder res= new StringBuilder();
	  while(end<str.length())
	  {
		  if(str.charAt(start)==str.charAt(end))
		  {
			  end++;
		  }
		  else
		  {
			  res.append(end-start).append(str.charAt(start));
			  start=end;
		  }
		  
	  }
	  //special case for the last sequence of characters when end goes to str.length()
	  res.append(end-start).append(str.charAt(start));
	  start=end;
	  
	  return res.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
