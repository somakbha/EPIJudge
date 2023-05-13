package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  
  
  //This approach is optimized approach on top of Sieve of Eratosthenes Approach
  public static List<Integer> generatePrimes(int n) {
	  
	  ArrayList<Integer> res = new ArrayList<Integer>();
	  if(n<=1)
		  return res;
	  
	  res.add(2);
	  
		// here we do not want to store all the even numbers. So we do not need to store
		// multiples of 2. as they will never be prime. so we reduce half the storage
		// space requirement hence 0.5
		// we do not need to compute on 0,1,2 hence n-3. We add 2 already as a result.
		// try for n=9, we need one additional place for number up to and including 9.
		// hence +1;
	  int size=(int) (Math.floor(0.5*(n-3))+1);
	  ArrayList<Boolean> sieve= new ArrayList<Boolean>(Collections.nCopies(size, true));
	  
	  //Each of the index in the sieve array represents the number in 2i+3 format, for example index 0:3, index 1:5, index 2: 7
	  //another optimization here we have done is as follows:
	  //for every number say 5, every k.p where k<p will already be computed by the we take up p. So we can start from p^2, for example for 5, we need not to check value for 10 and 15
	  //as 10 (p=5*k=2) will already be computed when we compute for 2
	  //as 15 (p=5*k=3) will already be computed when we compute for 3
	  // so we should start our iteration from p^2
	  //Now every p can be represented as 2*i+3
	  // so p^2=4*i^2+12i+9. We need to find in which index of sieve array represents this value. Assume that index is d. Now we know 2*index+3 represents a number
	  //so 2*d+3=4*i^2+12*i+9==>d=2*i^2+6*i+3
	  //Hence we start verifying setting the values to false from d i.e 2*i^2+6*i+3
	  //in case of 5 (i==1), first multiple we check is 5^2=25. so d=11
	  //next index is d+5, that is 16, number is 2*16+3=35. We need to check 30 as it is even.
	  
		// Unfortunately this code only passes 22 testcase out of 24 testcases, for last
		// 2 testcase even after using the long data type it throws overflow error
	  for(int i=0; i<sieve.size(); i++)
	  {
		  if(sieve.get(i))
		  {
			  int p=2*i+3;
			  res.add(p);
			 // System.out.println(i);
			  for(long index=(2*(i*i))+6*i+3; index<size; index+=p)
			  {
					  sieve.set((int)index, false);
			  }
		  }
		 
		 
	  }
	  
	  return res;
	  
  }
	public static List<Integer> generatePrimes_Sieve_of_Eratosthenes_Approach(int n) {
		// TODO - you fill in here
	  
	  
	  //The approach ehere known as Sieve of Eratosthenes. Pick a number and remove all it's multiples as non-prime.
	  // The TC: O(nlog logn) SC: O(n) see EPI page 86 for the same

		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<Boolean> sieve= new ArrayList<Boolean>(Collections.nCopies(n+1, true));
		sieve.set(0, false);
		sieve.set(1, false);

		for (int i = 2; i < sieve.size(); i++) {
			if (sieve.get(i)) {
				res.add(i);

				int counter = 2;
				while (i * counter <= n) {
					sieve.set(i* counter, false);
					counter++;
				}
			}
		}

		return res;

	}

  public static void main(String[] args) {
	//  System.out.println(generatePrimes(9));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
