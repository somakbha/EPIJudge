package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
	  
	int carryIn=1;
	//carryIn controls here how long we should continue the addition
	for(int i=A.size()-1; i>=0 && carryIn!=0; i--)
	{
		int sum=A.get(i)+carryIn;
		A.set(i, sum%10);
		carryIn=sum/10;
	}
	if(carryIn==1)
		A.add(0, carryIn);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
