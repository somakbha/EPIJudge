package epi.chapter6;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class BuyAndSellStockTwice {
	@EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
	public static double buyAndSellStockTwice(List<Double> prices) {
		// TODO - you fill in here.
		/*
		 * forward iteration 1: find out at any given day if you sell a stock, how much
		 * profit you can make 2: compare it with maximum profit made already 3. store
		 * the maximum profit in an array profitForward[] against the days index This
		 * implies on day i, maximum profit made by one sell is profitForward[i]
		 */
		double maxProfitSell = 0.0;
		double minStockPriceSoFarFwd = Double.MAX_VALUE;
		Double[] profitForward = new Double[prices.size()];

		for (int i = 0; i < prices.size(); i++) {
			double curProfit = prices.get(i) - minStockPriceSoFarFwd;
			maxProfitSell = Math.max(maxProfitSell, curProfit);
			minStockPriceSoFarFwd = Math.min(prices.get(i), minStockPriceSoFarFwd);
			profitForward[i] = maxProfitSell;
		}

		/*
		 * reverse iteration 1: find out at any given day if you buy a stock, how much
		 * profit you can make by selling it in future 2: compare it with maximum profit
		 * made already 3. store the maximum profit in an array profitBackward[] against
		 * the days index This implies on day i, maximum profit made by one buy is
		 * profitBackward[i]
		 */

		double maxProfitBuy = 0.0;
		double maxStockPriceSoFarRev = Double.MIN_VALUE;
		Double[] profitBackward = new Double[prices.size()];

		for (int i = prices.size() - 1; i >= 0; i--) {
			double curProfit = maxStockPriceSoFarRev - prices.get(i);
			maxProfitBuy = Math.max(maxProfitBuy, curProfit);
			maxStockPriceSoFarRev = Math.max(prices.get(i), maxStockPriceSoFarRev);
			profitBackward[i] = maxProfitBuy;
		}

		/*
		 * there can be one secnario where the prices list is <0.1, 0.2> in such case
		 * only one transaction (buy and sell) is possible. But we should still compute
		 * that. As per condition the second buy should always happen after first sell.
		 * hence while adding we do profitBackward[i] + profitForward[i - 1] except at
		 * i==0
		 */
		/*
		 * As per EPI this code can be further improved by not storing the values of
		 * reverse iteration in the profitBackward[]. rather we compute the maxprofit on
		 * the fly. This will help to improve space complexity
		 */

		double maxProfit = 0.0;
		for (int i = 0; i < profitBackward.length; i++) {

			double totalProfit = (i == 0) ? profitBackward[i] : profitBackward[i] + profitForward[i - 1];
			maxProfit = Math.max(maxProfit, totalProfit);
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		
		
		
		  System.exit(GenericTest.runFromAnnotations(args, "BuyAndSellStockTwice.java",
		  new Object() { }.getClass().getEnclosingClass()).ordinal());
		 
	}
}
