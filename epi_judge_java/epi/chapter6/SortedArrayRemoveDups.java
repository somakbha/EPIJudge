package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.List;
public class SortedArrayRemoveDups {
  // Returns the number of valid entries after deletion.
	//TC: O(n) SC: O(1)
	public static int deleteDuplicates(List<Integer> A) {
		// TODO - you fill in here.

		if (A.size() == 0)
			return 0;

		int nonDupIdx = 1;
		for (int i = 1; i < A.size(); i++) {
			int curElem = A.get(i);
			int prevElem = A.get(i - 1);

			if (curElem != prevElem) {
				A.set(nonDupIdx, curElem);
				nonDupIdx++;
			}

		}
		return nonDupIdx;
	}
  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
