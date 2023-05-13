package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")
  
  //after reading the EPI solution I have improved my solution to be more precise and coverin all corner cases like numRows==0 or 1, from 1st approach
  
  //for numRows==n, the inner loop will run n times. so the TC is O(1+2+3+...+n)==>O(n^2)
  //SC is also O(n^2)
	public static List<List<Integer>> generatePascalTriangle(int numRows) {
		// TODO - you fill in here.

		List<List<Integer>> pascalsTriangle = new ArrayList<List<Integer>>();

		for (int k = 0; k < numRows; k++) {
			ArrayList<Integer> curRow = new ArrayList<Integer>();

			for (int p = 0; p <= k; p++) {

				if (p == 0 || p == k)
					curRow.add(1);
				else {
					int val = pascalsTriangle.get(k - 1).get(p) + pascalsTriangle.get(k - 1).get(p - 1);
					curRow.add(val);
				}
			}

			pascalsTriangle.add(curRow);
		}
		return pascalsTriangle;
	}

	public static List<List<Integer>> generatePascalTriangle_1stApproach(int numRows) {
		// TODO - you fill in here.

		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return res;

		ArrayList<Integer> firstRow = new ArrayList<Integer>();
		firstRow.add(1);
		res.add(0, firstRow);

		// at k=1, we will 2 elements in the row
		// at k=2, we will 3 elements in the row
		// at k=3, we will 4 elements in the row
		for (int k = 1; k < numRows; k++) {
			ArrayList<Integer> curRow = new ArrayList<Integer>();
			List<Integer> prevRow = res.get(k - 1);

			//every row has 1 at the start and end of it. For K=n, the row length will be n+1. hence 1 will be present at index 0 and k
			curRow.add(0, 1);
			for (int p = 1; p <= k - 1; p++) {
				int val = prevRow.get(p) + prevRow.get(p - 1);
				curRow.add(p, val);
			}
			curRow.add(k, 1);
			res.add(k, curRow);
		}
		return res;
	}

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
