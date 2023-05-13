package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;


public class StringIntegerInterconversion {

	//after reading the EPI solution, I made some improvements to the first appraoch to make to code more precise
	// 1. instead of while(num!=0) use do-while loop so that loop at least run one time and if num is 0, we donot need to handle num==0 case separately
	// 2. Using Math.abs always keep the remainder positive so we do not need to store the input in a long variable
	
	public static String intToString(int x) {

		// assume a number N in base 10 has d number of digits. So the number should be
		// 10^(d-1)<=N<10^d.
		// for example when d=3, i.e a 3 digit number should be between
		// 10^(3-1)<=N<10^3==>100<=N<999
		// Based on the above logic 10^(d-1)<=N==> taking log base 10 on both sides,
		// d=logN+1
		// so the below programme will run for number of digits in X i.e log1X+1 times.
		boolean isNeg = false;

		if (x < 0) {
			isNeg = true;

		}
		StringBuilder res = new StringBuilder();
		do {
			//in case of java if you do (-241/10) and (-241%10) the result will be -24 and -1 respectively. Hence remainder can be negative. SO we have to use Math.abs here.
			int digit = Math.abs(x % 10);
			x = x / 10;
			// If you add '0' with int variable, it will return actual value in the char
			// variable.
			// The ASCII value of '0' is 48. So, if you add 1 with 48, it becomes 49 which
			// is equal to 1. The ASCII character of 49 is 1.
			// char c=(char)(a+'0');
			res.append((char) (digit + '0'));
		} while (x != 0);

		if (isNeg == true)
			res.append('-');
		// at this stage if the input is -123, res contain 321-. hence we do the
		// reverse.
		return res.reverse().toString();
	}
	//after reading the EPI solution made some improvements
	//where the the input string contain + or - sign, we will start the loop index from 1 else will start from 0
	public static int stringToInt(String s) {
		// TODO - you fill in here.

		// the maximum negative integer number is one grater that maximum positive
		// integer number (2147483647). hence we cannot process the
		// input parameter just by assigning to an integer number it has to be long
		int number = 0;
		for (int i = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0; i < s.length(); i++) {

			// convert char variables to int by subtracting with char 0
			// int num1 = ch1 - '0'; In the code, ch1 which is equal to '5' has an ASCII
			// value of 53.
			// And ASCII value of 0 is 48 so if we subtract 48 from 53 we get 5 which is the
			// int value of num1.
			// the below line is complex when the input string is maximum negative number
			// i.e -2147483648. In that case at the last loop
			// number=214748364*10+8, so it will overflow and number itself will store as
			// -2147483648
			// now while returning at the last line again we do -number, so ideally it
			// should be positive but if you apply - on min integer value, it does not get
			// positive
			// because post 2147483648 will overflow and cannot fit in int datatype
			//check this code snippet
			//int x=-2147483648;
			//System.out.println(-x);

			number = number * 10 + (s.charAt(i) - '0');
		}
		return (s.charAt(0) == '-') ? -number : number;
	}



	// assume a number N in base 10 has d number of digits. So the number should be 10^(d-1)<=N<10^d.
	//for example when d=3, i.e a 3 digit number should be between 10^(3-1)<=N<10^3==>100<=N<999
	//Based on the above logic 10^(d-1)<=N==> taking log base 10 on both sides, d=logN+1
	// so the below programme will run for number of digits in X i.e logX+1 times.
	public static String intToString_1st_Approach(int x) {
		// for int to string conversion we have couple of edge cases to consider
		// if the number is 0 itself, we need to return it as string
		// if the number is negative, make it positive and compute and add back the
		// negative sign

		if (x == 0)
			return "0";
		boolean isNeg = false;
		// the maximum negative integer number is one grater that maximum positive
		// integer number (2147483647). hence we cannot process the
		// input parameter just by assigning to an integer number it has to be long
		long num = x;
		if (num < 0) {
			isNeg = true;
			num = -num;
		}
		StringBuilder res = new StringBuilder();
		while (num != 0) {
			int digit = (int) (num % 10);
			num = num / 10;
			// If you add '0' with int variable, it will return actual value in the char
			// variable.
			// The ASCII value of '0' is 48. So, if you add 1 with 48, it becomes 49 which
			// is equal to 1. The ASCII character of 49 is 1.
			// char c=(char)(a+'0');
			res.append((char) (digit + '0'));
		}
		if (isNeg == true) 
			res.append('-');
		//at this stage if the input is -123, res contain 321-. hence we do the reverse.
		return res.reverse().toString();
	}

	//TC:O(n) length of the string is n
	public static int stringToInt_1stApparoach(String s) {
		// TODO - you fill in here.

		// String numbers can be started with + and - signs
		boolean isNeg = false;
		// the maximum negative integer number is one grater that maximum positive
		// integer number (2147483647). hence we cannot process the
		// input parameter just by assigning to an integer number it has to be long
		long number = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-')
				isNeg = true;
			else if (s.charAt(i) == '+')
				continue;
			else {
				// convert char variables to int by subtracting with char 0
				// int num1 = ch1 - '0'; In the code, ch1 which is equal to '5' has an ASCII
				// value of 53.
				// And ASCII value of 0 is 48 so if we subtract 48 from 53 we get 5 which is the
				// int value of num1.
				number = number * 10 + (s.charAt(i) - '0');

			}

		}
		return (isNeg == true) ? (int) -number : (int) number;
	}
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
		
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
