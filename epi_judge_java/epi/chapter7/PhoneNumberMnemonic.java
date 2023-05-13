package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {
  
  public static String MAPPING[]= {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
	public static List<String> phoneMnemonic(String phoneNumber) {
		// TODO - you fill in here.

		List<String> res = new ArrayList<String>();
		char partialRes[]= new char[phoneNumber.length()];

		buildMnemonic(phoneNumber, 0, res, partialRes);
		return res;
	}
	
	//This approach I have used after reading EPI solution. In my approach I have tried to calculate start and end index for every number, but that process can lead to error
	//as from 7 and 9 represents 4 letters while other represents 3 letters. As a result, the start and end index will vary accordingly. The better approach is create a MAPPING array
	//for every number and keep the letters against each of the number there.
   //TC:O(n*4^n)==> at every level we loop maximum of 4 times for digit 7 and 9. The depth of the recursion is number of string in the letter phoneNumber. here 4*4*4...n=4^n
   //at every recursion when we reach at the bottom i.e base condition we create a new string of length n. hence total TC:O(n*4^n).
	static void buildMnemonic(String phoneNumber, int idx, List<String> res, char[] partialRes)
	{
		if (idx == phoneNumber.length()) {
			res.add(new String(partialRes));
			return;
		}

		int digit = phoneNumber.charAt(idx) - '0';
		String letters = MAPPING[digit];

		for (int k = 0; k < letters.length(); k++) {
			char ch = letters.charAt(k);
			partialRes[idx]=ch;
			buildMnemonic(phoneNumber, idx + 1, res, partialRes);

		}

	}
  
	
  static void buildMnemonic_1stAppaorach(String phoneNumber, int idx, List<String> res, String tmpStr)
  {
	  if(idx==phoneNumber.length())
	  {
		  res.add(tmpStr.toString());
		  return;
	  }
	  
	  int digit=phoneNumber.charAt(idx)-'0';
	  
	  if(digit==0)
	  {
		  buildMnemonic_1stAppaorach(phoneNumber, idx+1, res, tmpStr+'0');
	  }
	  else if(digit==1)
	  {
		  buildMnemonic_1stAppaorach(phoneNumber, idx+1, res, tmpStr+'1');
	  }
	  else
	  {
	  int start=(digit==8||digit==9)?(digit-2)*3+1:(digit-2)*3;
	  int end=(digit==7||digit==9)?start+3:start+2;
	  
	  for(int k=start; k<=end; k++)
	  {
		  char ch=(char)('A'+k);
		  buildMnemonic_1stAppaorach(phoneNumber, idx+1, res, tmpStr+ch);
	  }
	  
	  }
  }
  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
