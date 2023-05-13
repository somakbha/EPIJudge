package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  
	/*
	 * Final Approach, TC=O(n) SC=O(1), Each iteration decreases the size of
	 * unclassified by 1, and the time spent within each iteration is0(1),implying
	 * the time complexity is0(n). The space complexity is clearly 0(1).
	 */ 
  
	/*
	 * Keep the following invariants during partitioning: 
	 * bottom group: A.subList(0,smaller).
	 * middle group: A.subList(smaller,equal). 
	 * unclassified group: A.subList(equal,larger). 
	 * top group: A.subList(larger,A.size()).
	 * 
	 * LIST=<0,0,0,1,1,1,2,1,1,0,2,2,2>
	 * bottom group: A.subList(0,2).
	 * middle group: A.subList(3,5).
	 * unclassified group: A.subList(6,9).
	 * top group: A.subList(10,12).
	 */
	public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
		// TODO - you fill in here.

		int pivot = A.get(pivotIndex).ordinal();

		int start = 0;
		int end = A.size() - 1;
		for (int i = 0; i <= end;) {
			Color curElem = A.get(i);
			//in case of lesser element than pivot, the swap in element will always be equal to pivot. it cannot be greater than pivot. hence we advance i.
			// when we encounter 0, the element to be swapped is 1 only. because any 2's present in the list prior to 0, must have been already swapped by 0 or 1
			if (curElem.ordinal() < pivot) {
				Collections.swap(A, i, start);
				i++;
				start++;

			} else if (curElem.ordinal() > pivot) { // here the swapped element can be 0 or 1, so we can not move the i.
				Collections.swap(A, i, end);

				end--;

			} else {
				i++;
			}

		}

		return;
	}
  
  //TC:O(n) SC: O(1)
  public static void dutchFlagPartition_Approach2(int pivotIndex, List<Color> A) {
	    // TODO - you fill in here.
		
		 
		 int pivot=A.get(pivotIndex).ordinal();
		 
			// pushing lesser elements at the starting, start from the start so that if some
			// elements are there at the start of the list they do not come in the middle
			// because of swap
		 int start=0;
		 for(int i=0; i<A.size(); i++)
		 {
			 Color curElem=A.get(i);
			 if (curElem.ordinal()<pivot)
			 {
				 Collections.swap(A, i, start++);
				
			 }
			
		 }
			// pushing greater elements at the end, start from the end so that if some
			// elements are there at the end of the list they do not come in the middle
			// because of swap
		 int end=A.size()-1;
		 for(int i=A.size()-1; i>=0; i--)
		 {
			 Color curElem=A.get(i);
			 if (curElem.ordinal()>pivot)
			 {
				 Collections.swap(A, i, end--);
				 
			 }
			
		 }
		 
	    return;
	  }
  //TC=O(n), SC=O(n)
  public static void dutchFlagPartition_Approach1(int pivotIndex, List<Color> A) {
    // TODO - you fill in here.
	 List<Color> smaller=new ArrayList<Color>();
	 List<Color> equal=new ArrayList<Color>();
	 List<Color> greater=new ArrayList<Color>();
	 
	 
	 int pivot=A.get(pivotIndex).ordinal();
	 
	 for(Color c: A)
	 {
		 if (c.ordinal()<pivot)
			 smaller.add(c);
		 else if(c.ordinal()==pivot)
			 equal.add(c);
		 else
			 greater.add(c);
	 }
	 A.removeAll(A);
	 for(Color c: smaller)
	 {
		 A.add(c);
	 }
	 for(Color c: equal)
	 {
		 A.add(c);
	 }
	 for(Color c: greater)
	 {
		 A.add(c);
	 }
    return;
  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
