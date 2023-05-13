package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // TODO - you fill in here.
	  if(x<0)
		  return false;
	  
	return verifyPalindrome(x);
   
  }
  
  static boolean verifyPalindrome(int n)
  {
	  int totDigit=(int)Math.floor(Math.log10(n))+1;
	  while(n!=0)
	  {
		  int msdMask=(int)Math.pow(10, totDigit-1); //msdMask to extract the most significant digit
		  int msd=n/msdMask; //get the most significant digit
		  int lsd=n%10; //get the least significant digit
		  
		  int quotient=n%msdMask; // removes the most significant digit
		  n=quotient/10; // removes the least significant digit
		  totDigit=totDigit-2; // the above two operations reduces 2 digits from the number
		  		  
		  if(lsd!=msd)
			  return false;
	  }
	  return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
