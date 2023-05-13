package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
	public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
		// TODO - you fill in here.

	    //O(n) time this loop runs for n rows and calls hasDuplicates. hasDuplicates runs for another O(n) time so O(n^2)
		for (int row = 0; row < partialAssignment.size(); row++) {
			if (hasDuplicates(partialAssignment, row, row + 1, 0, partialAssignment.size()))
				return false;
		}
		//O(n) time this loop runs for n columns and calls hasDuplicates. hasDuplicates runs for another O(n) time so O(n^2)
		for (int col = 0; col < partialAssignment.size(); col++) {
			if (hasDuplicates(partialAssignment, 0, partialAssignment.size(), col, col + 1))
				return false;
		}
		//Outer loop runs n/3 times, inner loop runs n/3 times. 
		//hasDuplicate essentially traverses all elements of the board so O(n^2)
		int regionSize=(int) Math.sqrt(partialAssignment.size());
		
		for (int row = 0; row < partialAssignment.size(); row += regionSize) {
			for (int col = 0; col < partialAssignment.size(); col += regionSize) {
				if (hasDuplicates(partialAssignment, row, row + regionSize, col, col + regionSize))
					return false;
			}
		}
		return true;

	}
  
  static boolean hasDuplicates(List<List<Integer>> sudokuBoard, int startRow, int endRow, int startCol, int endCol)
	{

		// sudokuBoard.size() is 9,so the array can store integer from 0 to 8, but in
		// sudoku board we have to check existence values from 1 to 9, hence +1
		boolean[] isPresent = new boolean[sudokuBoard.size() + 1];

		for (int k = startRow; k < endRow; k++) {
			for (int m = startCol; m < endCol; m++) {
				if (sudokuBoard.get(k).get(m) != 0 && isPresent[sudokuBoard.get(k).get(m)]) {

					return true;
				}

				isPresent[sudokuBoard.get(k).get(m)] = true;

			}
		}
		return false;
	}

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
