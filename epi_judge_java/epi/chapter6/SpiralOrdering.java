package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")
  
  //This is EPI's second approach. This approach will be useful also when we want to do the reverse like given an array of elements build the spiral order
  //First variant at page 102. TC:O(n^2)
  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
	  ArrayList<Integer> res= new ArrayList<Integer>();
	  int row=0;
	  int col=0;
	  int dir=0; //dir 0: left -> right, 1: top -> bottom, 2: right -> left, 3: bottom -> top
	  int [][] SHIFT={{0,1},{1,0},{0,-1},{-1,0}};
    for(int i=0; i<squareMatrix.size()*squareMatrix.size(); i++)
    {
    	res.add(squareMatrix.get(row).get(col));
    	squareMatrix.get(row).set(col, -1);
    	
    	int newRow=row+SHIFT[dir][0];
    	int newCol=col+SHIFT[dir][1];
    	
    	//Here if newRow or newCol exceeds the boundary, we need to recalculate the index in the IF block.
    	//do not use the row or col value. instead use new variable like newRow or newCol. for a 2*2 matrix, col will increase to 3
    	//so we cannot reset it. hence use new variable and at the end of the loop reassign it to row and col.
    	if(newRow<0||newRow>=squareMatrix.size()||newCol<0||newCol>=squareMatrix.size()||squareMatrix.get(newRow).get(newCol)==-1)
    	{
    		dir=(dir+1)%4;
    		newRow=row+SHIFT[dir][0];
        	newCol=col+SHIFT[dir][1];
    	}
    	row=newRow;
    	col=newCol;
    			
    }
    
    
    return res;

  }

  public static List<Integer>
  matrixInSpiralOrder_OldApproach(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    int top=0;
    int bottom=squareMatrix.size()-1;
    int left=0;
    int right=squareMatrix.size()-1;
    
    ArrayList<Integer> res= new ArrayList<Integer>();
    
    // The EPI's second solution at page 102 is similar to what I have already coded in leetcode https://leetcode.com/submissions/detail/597939995/
    //Here however I further reduced the code to not introduce direction. Rather followed a sequence
    //1. print top row.
    //2. print right column.
    //3. print bottom row.
    //4. print left column.
    //TC: O(n^2)
    
       
    while(top<=bottom && left<=right)
    {
    	
    	for(int i=left; i<=right; i++)
    	{
    		res.add(squareMatrix.get(top).get(i));
    		
    	}
    	top++;
    	for(int i=top; i<=bottom; i++)
    	{
    		res.add(squareMatrix.get(i).get(right));
    		
    	}
    	right--;
    	for(int i=right; i>=left; i--)
    	{
    		res.add(squareMatrix.get(bottom).get(i));
    		
    	}
    	bottom--;
    	for(int i=bottom; i>=top; i--)
    	{
    		res.add(squareMatrix.get(i).get(left));
    		
    	}
    	left++;
    }
    
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
