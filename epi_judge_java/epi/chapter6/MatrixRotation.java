package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class MatrixRotation {

	//This is the approach what EPI suggests. Here basically you have to keep the original matrix and transpose matrix side by side
	//at one go you have to swap 4 values.
	// pick one value which is on or above the main diagonal and then find where this value will go in the rotated matrix. 
	//Also because of this movement what other 3 values will be shifted.
	
	// for example if I pick the 4*4 matrix of the EPI example, 
	//(row, col) in the original matrix goes to (row', col') in the transposed matrix
	//(0,1) -> (1,3)
	//(1,3) -> (3,2)
	//(3,2) -> (2,0)
	//(2,0) -> (0,1)
	// keep the matrix size as size=matrix.size()-1==>3
	public static void rotateMatrix(List<List<Integer>> squareMatrix) {
		
		int size=squareMatrix.size()-1;
		
		//here the loop condition is very importatnt we should not rotate a single element multiple time. Note in one go we swap 4 elements. So we should not re swap them.
	
		for(int i=0; i<squareMatrix.size()/2; i++)
		{
			for(int j=i; j<size-i; j++)
			{
				//remember here all the conversion should happen with respect to (i,j) that is in above example (0,1)
				int temp1=squareMatrix.get(size-j).get(i);//(2,0)
				int temp2=squareMatrix.get(size-i).get(size-j);//(3,2)
				int temp3=squareMatrix.get(j).get(size-i);//(1,3)
				int temp4=squareMatrix.get(i).get(j);//(0,1)
				
				//now temp4 should be assigned to temp3's location
				//now temp3 should be assigned to temp2's location
				//now temp2 should be assigned to temp1's location
				//now temp1 should be assigned to temp0's location
				
				squareMatrix.get(j).set(size-i, temp4);
				squareMatrix.get(size-i).set(size-j, temp3);
				squareMatrix.get(size-j).set(i, temp2);
				squareMatrix.get(i).set(j, temp1);				
			}
		}
		
	}
	
  // This approach I learned from leetcode
  public static void rotateMatrix_LeetCodeApproach(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
	  
	  //transpose around the main diagonal
	  
	  for(int i=0; i<squareMatrix.size(); i++)
	  {
		  for(int j=i; j<squareMatrix.size(); j++)
		  {
			  int val1=squareMatrix.get(i).get(j);
			  squareMatrix.get(i).set(j, squareMatrix.get(j).get(i));
			  squareMatrix.get(j).set(i, val1);
		  }
	  }
	  
	 //swap the columns of transposed matrix 1st with last, 2nd with second last
	  
	  for(int col=0; col<squareMatrix.size()/2; col++)
	  {
		  int anotherColIndx=squareMatrix.size()-1-col;
		  
		  //the swap should happen between two columns for very row
		  for(int row=0; row<squareMatrix.size(); row++)
		  {
			  int val2=squareMatrix.get(row).get(col);
			  squareMatrix.get(row).set(col, squareMatrix.get(row).get(anotherColIndx));
			  squareMatrix.get(row).set(anotherColIndx, val2);
			  
		  }
	  }
    return;
  }
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
