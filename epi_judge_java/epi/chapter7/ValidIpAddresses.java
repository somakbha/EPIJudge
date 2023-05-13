package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class ValidIpAddresses {
  @EpiTest(testDataFile = "valid_ip_addresses.tsv")

  //EPI approach
  //I have recoded after reading the EPI solution. It is better they way index is managed and takes care of not generating empty or nul string
  //TC:O(1) actually for there are atmost (2^32) IP addresses available. hence the code can run maximum O(2^32) times to generate all the possible number hence it is constant time.
  //another way to look at it is, every loop runs 3 times, we have 3 loops all together so it is O(9)==>O(1)==>Constant time complexity
  public static List<String> getValidIpAddress(String s) {
	    // TODO - you fill in here.
		  List<String> res= new ArrayList<String>();
			 
		 for(int pos1=1; pos1<s.length()&& pos1<4; pos1++)
		 {
			 String num1=s.substring(0, pos1);
			 if(!isValidOctet(num1))
				 continue;
			 
			 for(int pos2=1; pos1+pos2<s.length()&& pos2<4; pos2++)
			 {
				 String num2=s.substring(pos1, pos1+pos2);
				 if(!isValidOctet(num2))
					 continue;
				 
				 for(int pos3=1; pos1+pos2+pos3<s.length()&& pos3<4; pos3++)
				 {
					 String num3=s.substring(pos1+pos2, pos1+pos2+pos3);
					 String num4=s.substring(pos1+pos2+pos3, s.length());
					 if(!isValidOctet(num3)||!isValidOctet(num4))
						 continue;
					 else
						 {
						 StringBuilder ip=new StringBuilder();
						 ip.append(num1).append(".");
						 ip.append(num2).append(".");
						 ip.append(num3).append(".");
						 ip.append(num4);
						 res.add(ip.toString());
						 }
				 }
			 }
		 }
	    return res;
	  }
  
  static boolean isValidOctet(String str)
	{
	  //This is possible for the last octet as we take substring till the end of the string
	  if(str.length()>3)
		  return false;

	// "00" , "000" , "00", etc. are not valid, but "0" is valid.
	  //as in the last loop pos1+pos2+pos3 are < s.length so String num4=s.substring(pos1+pos2+pos3, s.length()); 
	  //num4 will have at least one character and it never be empty or null. Hence we need not to
	  
		if (Integer.parseInt(str) > 255||(str.charAt(0)=='0' && str.length()>1))
			return false;
		return true;
	}
  
  
  public static List<String> getValidIpAddress_1stApproach(String s) {
    // TODO - you fill in here.
	  List<String> res= new ArrayList<String>();
	 if(s.length()>12 || s.length()<4)
		 return res;
	
	 
	 
	 for(int pos1=0; pos1<s.length()&& pos1<3; pos1++)
	 {
		 String num1=s.substring(0, pos1+1);
		 if(!validateIPOctet_1stApproach(num1))
			 continue;
		 
		 for(int pos2=pos1+1; pos2<s.length()&& pos2<pos1+4; pos2++)
		 {
			 String num2=s.substring(pos1+1, pos2+1);
			 if(!validateIPOctet_1stApproach(num2))
				 continue;
			 
			 for(int pos3=pos2+1; pos3<s.length()&& pos3<pos2+4; pos3++)
			 {
				 String num3=s.substring(pos2+1, pos3+1);
				 String num4=s.substring(pos3+1, s.length());
				 if(!validateIPOctet_1stApproach(num3)||!validateIPOctet_1stApproach(num4))
					 continue;
				 else
					 {
					 StringBuilder ip=new StringBuilder();
					 ip.append(num1).append(".");
					 ip.append(num2).append(".");
					 ip.append(num3).append(".");
					 ip.append(num4);
					 res.add(ip.toString());
					 }
			 }
		 }
	 }
    return res;
  }
  
 static boolean validateIPOctet_1stApproach(String str)
	{

		if (str == null || str.isEmpty() || Integer.parseInt(str) > 255||(str.charAt(0)=='0' && str.length()>1))
			return false;
		return true;
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
	//  getValidIpAddress("1111");
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
