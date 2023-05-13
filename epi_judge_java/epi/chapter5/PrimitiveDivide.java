package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    // TODO - you fill in here.
	  
	int power=16;
	long yPower = (y << power); // sifting 1 place to the left is equal to multiplying by 2. Shifting 32 place
								// is multiplying y by 32. Here the the divisor y is of type int hence we can
								// maximum shift it to 32 places.
	int result=0;
	
	while(x>=y)
	{
		
		while(yPower>x)
		{
			/*
									 * reducing the value of yPower by right shifting which is diving by 2 also. As
									 * a result we are reducing the power by 1 every iteration
									 */
			power--;
			yPower = (y << power);
		}
		
		result+=1<<power;
		x=x-(y << power);
		
	}
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
