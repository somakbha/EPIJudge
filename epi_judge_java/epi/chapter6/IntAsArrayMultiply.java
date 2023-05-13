package epi.chapter6;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class IntAsArrayMultiply {

	// This is the EPI approach. This approach is neat compare to the approach I
	// tried initially. The nested loops runs here O(nm) time where n and m are
	// sizes of num1 and num2 respectively
	@EpiTest(testDataFile = "int_as_array_multiply.tsv")
	public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
		// TODO - you fill in here.

		// through XOR we can identify if the final product is negative
		boolean isNegative = (num1.get(0) < 0) ^ (num2.get(0) < 0);
		// before multiplying making both the numbers positive
		num1.set(0, Math.abs(num1.get(0)));
		num2.set(0, Math.abs(num2.get(0)));

		// The number of digits required for the product is at most n + m for n and m
		// digit operands, so we use an array of size n+ m for the result.
		int size = num1.size() + num2.size();
		List<Integer> result = new ArrayList<Integer>(Collections.nCopies(size, 0));

		for (int i = num1.size() - 1; i >= 0; i--) {
			for (int j = num2.size() - 1; j >= 0; j--) {
				// at this steps we compute values like 5*2=10 and store it in result at i+j+1
				// location. also if there are any existing value at i+j+1 location we add it.
				result.set(i + j + 1, result.get(i + j + 1) + (num1.get(i) * num2.get(j)));
				// compute the carry and store it in previous place
				result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
				// compute the actual number to be place at i+j+1 location. Do a dry run 123*92
				// to understand the logic
				result.set(i + j + 1, result.get(i + j + 1) % 10);
			}
		}

		// Now the result may contain leading zeros, we need to remove it
		int first_non_zero = 0;

		// identify at which index the first non zero element found
		while (first_non_zero < result.size()) {
			if (result.get(first_non_zero) != 0)
				break;

			first_non_zero++;
		}
		// create a sublist without leading zero.

		List<Integer> resultSubList = result.subList(first_non_zero, result.size());
		// special case where multiplied with 0 contains all zeros in result and we just
		// need to return one zero
		if (resultSubList.isEmpty()) {
			List<Integer> res = new ArrayList<Integer>();
			res.add(0);
			return res;
		}
		if (isNegative)
			resultSubList.set(0, -resultSubList.get(0));
		return resultSubList;
	}

	// @EpiTest(testDataFile = "int_as_array_multiply.tsv")
	public static List<Integer> multiply_SOMAK_APPROACH(List<Integer> num1, List<Integer> num2) {
		// TODO - you fill in here.

		// through XOR we can identify if the final product is negative
		boolean isNegative = (num1.get(0) < 0) ^ (num2.get(0) < 0);
		// before multiplying making both the numbers positive
		num1.set(0, Math.abs(num1.get(0)));
		num2.set(0, Math.abs(num2.get(0)));

		List<Integer> result = new LinkedList<Integer>();
		// special case handle when one of the number is 0
		if ((num1.size() == 1 && num1.get(0) == 0) || (num2.size() == 1 && num2.get(0) == 0)) {
			result.add(0);
			return result;
		}

		int rowNo = 0;
		for (int i = num2.size() - 1; i >= 0; i--) {
			int digit = num2.get(i);
			List<Integer> rowRes = multiply(num1, digit, rowNo);

			rowNo++;
			result = sum(result, rowRes);

		}
		if (isNegative)
			result.set(0, -result.get(0));
		return result;
	}

	private static List<Integer> sum(List<Integer> result, List<Integer> rowRes) {
		int carryIn = 0;
		List<Integer> tempResult = new LinkedList<Integer>();
		int r1 = result.size() - 1, r2 = rowRes.size() - 1;
		for (; r1 >= 0 && r2 >= 0; r1--, r2--) {
			int sum = result.get(r1) + rowRes.get(r2) + carryIn;
			tempResult.add(0, sum % 10);
			carryIn = sum / 10;
		}
		while (r1 >= 0) {
			int sum = result.get(r1) + carryIn;
			tempResult.add(0, sum % 10);
			carryIn = sum / 10;
			r1--;

		}
		while (r2 >= 0) {
			int sum = rowRes.get(r2) + carryIn;
			tempResult.add(0, sum % 10);
			carryIn = sum / 10;
			r2--;
		}
		if (carryIn != 0)
			tempResult.add(0, carryIn);
		return tempResult;

	}

	private static List<Integer> multiply(List<Integer> num1, int digit, int rowNo) {
		List<Integer> rowRes = new LinkedList<Integer>();
		int carryIn = 0;
		for (int k = num1.size() - 1; k >= 0; k--) {
			int mul = (num1.get(k) * digit) + carryIn;
			rowRes.add(0, mul % 10);
			carryIn = mul / 10;
		}
		if (carryIn != 0)
			rowRes.add(0, carryIn);

		// shifting of rows
		for (int c = 0; c < rowNo; c++)
			rowRes.add(0);

		return rowRes;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IntAsArrayMultiply.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
