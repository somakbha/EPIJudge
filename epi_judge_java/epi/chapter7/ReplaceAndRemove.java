package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove {

	//TC: O(n) both forward and backward iteration takes O(n) time. SC: O(1)
	public static int replaceAndRemove(int size, char[] s) {
		// TODO - you fill in here.

		//EPI page 112 contains the core logic to solve this problem
		int insertPos = 0;
		int numberOfA=0;
		//forward iteration to remove all the b's
		for (int k = 0; k <size; k++) {
			
			if(s[k]!='b')
			{
			s[insertPos]=s[k];
			insertPos++;
			}
			if(s[k]=='a')
				numberOfA++; //this indicates total additional space required to hold 2 d's for a every a
		}
		
		

		int updInsPos = insertPos +numberOfA - 1;
		int resultIndex=updInsPos;
		//backward iteration to replace all the a's with 2 d's
		for (int k = insertPos - 1; k >= 0; k--) {
			if (s[k] == 'a') {
				s[updInsPos--] = 'd';
				s[updInsPos--] = 'd';

			} else {
				s[updInsPos--] = s[k];

			}
		}
		return resultIndex+1;
	}
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
