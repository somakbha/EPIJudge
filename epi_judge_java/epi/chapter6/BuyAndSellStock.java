package epi.chapter6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // TODO - you fill in here.
	  Double minPriceSoFar=Double.MAX_VALUE;
	  Double maxProfitSoFar=0.0;
	  for(int i=0; i<prices.size(); i++)
	  {
		  Double curProfit=prices.get(i)-minPriceSoFar;
		  maxProfitSoFar=Math.max(maxProfitSoFar, curProfit);
		  minPriceSoFar=Math.min(minPriceSoFar, prices.get(i));
	  }
    return maxProfitSoFar;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
