package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    // TODO - you fill in here.
	 int number= convertToBaseb1(numAsString, b1);
	 String result=convertFromBaseb2(number, b2);
    return result;
  }
  
  //TC Calculation
  //convertToBaseb1 runs n times where n is the length of the input string s and creates a number x in base 10
  //convertFromBaseb2 runs p number of times where p is the number of digits of x in base b2. The formula of number of digits
  // for a given number in any base b is log(b)number+1 ==> log(b2)x+1
  //now x is derived from a string in base b1. In any given base b1 maximum number it can store in n digits is upper bounded by b1^n
  // hence total TC: n+log(b2)b1^n=>n(1+log(b2)b1)
  
  private static String convertFromBaseb2(int num, int b2)
  {
	  boolean isNeg=(num<0)?true:false;
	  StringBuilder result=new StringBuilder();
	  do
	  {
		  int digit=Math.abs(num%b2);
		  num=num/b2;
		  //handling the cases, when the number is greater or equal to 10 like in base 16 system
		  char c=(digit<10)?(char)(digit+'0'):(char)((digit-10)+'A');
		  result.append(c);
		  
	  }while(num!=0);
	  
	  if(isNeg)
		  result.append('-');
	  return result.reverse().toString();
  }
  private static int convertToBaseb1(String numStr, int b2)
  {
	  int res=0;
	  
	  for(int i=(numStr.charAt(0)=='-'||numStr.charAt(0)=='+')?1:0; i<numStr.length(); i++)
	  {
		  //handling the cases, when the number is greater or equal to 10 like in base 16 system
		  int n=(Character.isDigit(numStr.charAt(i)))?(numStr.charAt(i)-'0'):(numStr.charAt(i)-'A')+10;
		  res=(res*b2)+n;
	  }
	  
	  return (numStr.charAt(0)=='-')?-res:res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
