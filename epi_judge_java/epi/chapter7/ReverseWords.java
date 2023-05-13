package epi.chapter7;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {
	
	//I have taken the logic of EPI to solve this but coded bit differently than it is stated in EPI.
	//First pass we will reverse the entire string
	//and second pass we will reverse each individual words
	//Assume the string has length of n. At an average, the word length is k so there are around ~n/k words
	// The first pass: reversing the entire string take O(n/2)
	// The second pass: reverse each word individually, the loop will run n times and each word will reverse in O(k/2)
	//Hence TC:O(n*k/2) since n>>k, so TC:O(n/2)
	//Hence taking everything TC: O(n/2)==>O(n).

	public static void reverseWords(char[] input) {
		// TODO - you fill in here.

		reverse(0, input.length - 1, input);

		for (int i = 0; i < input.length;) {
			int j = i;

			while (j < input.length && input[j] != ' ') {
				j++;
			}
			reverse(i, j - 1, input);
			i = j + 1;
		}
		
		return;
	}
  
  public static void reverse(int start, int end, char[] input)
  {
	  while(start<end)
	  {
		  char temp=input[start];
		  input[start]=input[end];
		  input[end]=temp;
		  start++;
		  end--;
	  }
	  
	 
	  
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
	  
	 // reverseWords(new char[] {'I',' ','L', 'O', 'V', 'E',' ','I','N','D','I','A'});
	 // reverseWords(new char[] {'W','E',' ','L', 'O', 'V', 'E',' ','I','N','D','I','A', ' ','O', 'U', 'R',' ','C','O','U','N','T','R','Y'});
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
