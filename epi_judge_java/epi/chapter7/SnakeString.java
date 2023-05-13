package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SnakeString {
  @EpiTest(testDataFile = "snake_string.tsv")

  //after reading the EPI solution, I rewrite the code where it will scan the string for 3 times
  //upper row will start with index 1 and iterate with an increase of 4
  //middle row will start with index 0 and iterate with an increase of 2
//lower row will start with index 3 and iterate with an increase of 4
  
	public static String snakeString(String s) {
		// TODO - you fill in here.
		StringBuilder result = new StringBuilder();

		for (int i = 1; i < s.length(); i += 4) {

			result.append(s.charAt(i));
		}
		for (int i = 0; i < s.length(); i += 2) {

			result.append(s.charAt(i));
		}
		for (int i = 3; i < s.length(); i += 4) {

			result.append(s.charAt(i));
		}

		return result.toString();
	}
  
  public static String snakeString_MyApproach(String s) {
    // TODO - you fill in here.
    StringBuilder upper= new StringBuilder();
    StringBuilder middle= new StringBuilder();
    StringBuilder lower= new StringBuilder();
    boolean isUpper=true;
    
    //every even index goes to middle row
    //every odd index alternatively goes to upper or lower rows. It start with first odd index that is 1 to be at upper row, hence isUpper=true
    //we toggle the value of isUpper after writing of every odd index using the line isUpper=!isUpper;
    for(int i=0; i<s.length(); i++)
    {
    	char ch=s.charAt(i);
    	if(i%2==0)
    	{
    		middle.append(ch);
    	}
    	else
    	{
    		if(isUpper)
    		{
    			upper.append(ch);
    		}
    		else
    		{
    			lower.append(ch);
    		}
    		isUpper=!isUpper;
    	}
    }
    
    return upper.toString()+middle.toString()+lower.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SnakeString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
