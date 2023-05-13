package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    // TODO - you fill in here.
	int maxReachableIndex=0;
	int lastIndex=maxAdvanceSteps.size()-1;
	
	//This condition i<=maxReachableIndex basically compute the maxReachableIndex based on what indexes are already reachble
	//(3, 3, 1, 0, 2, 0, 1)
	/*
	 * Iteration 0: maxReachableIndex 0+3=3
	 * Iteration 1: maxReachableIndex 1+3=4
	 */
	// This condition maxReachableIndex<lastIndex is for performance improvement.
	// For example if <50,0,0,0,0,0> in this case, after first iteration the
	// maxReachableIndex is 0+50=50, hence we can reach the last index and no more
	// iteration is required
	//TC:O(n) SC:O(1)
	for(int i=0; i<=maxReachableIndex && maxReachableIndex<lastIndex; i++)
	{
		maxReachableIndex=Math.max(maxReachableIndex, i+maxAdvanceSteps.get(i));
	}
	if(maxReachableIndex>=lastIndex)
		return true;
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
