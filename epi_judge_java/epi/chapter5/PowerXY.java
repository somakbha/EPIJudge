package epi.chapter5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
	
	int power=y;
	if(power<0) //this block is handing when the power is negative, for example x^-2 == (1/x)^2
	{
		x=1/x;
		power=-power;
	}
	
	//To understand the logic you can check like 2^31 where 31 in binary is 11111. 
	//2^31=(2^1)*(2^30)=(2^1)*(2^2)^15
	//4^15=(4^1)*(4^14)=(4^1)*(4^2)^7
	//16^7=(16^1)*(16^6)=(16^1)*(16^2)^3
	//256^3=(256^1)*(256^2)=(256^1)*(256^2)^1
	//65536^1=(65536^1)*(65356*65356)
	// here the loop can maximum run based on number of bits in power y so O(n).
	//in case of even power, like x^ 4 (5=b100)
	//iteration 1: result:1.0, x=x^2, power=2 (b10)
	//iteration 2: result:1.0, x=(x^2)^2=x^4, power=1(b1)
	//iteration 3: result:1.0*x^4, x=x^4*x^4, power=0(b0)
	//result x^4
	
	//in case of odd power, like x^ 5 (5=b101)
	//iteration 1: result:1.0*x, x=x^2, power=2 (b10)
	//iteration 2: result:1.0*x, x=(x^2)^2=x^4, power=1(b1)
	//iteration 3: result:1.0*x*x^4, x=x^4*x^4, power=0(b0)
	//result x^5
	
	double result=1.0;
	while(power!=0)
	{
		if((power&1)==1)
		{
			result*=x;
		}
		x*=x;
		power=power>>>1;
	}
    return result;
  }

  public static void main(String[] args) {
	  
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
