/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014-2017 Marc de Verdelhan, 2017-2019 Ta4j Organization & respective
 * authors (see AUTHORS)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.stockminer.stockminer;

import com.stockminer.stockminer.ta4jExtension.LowerValueRuleByDividendFactor;
import loader.CsvBarsLoader;
import org.junit.Assert;
import org.junit.Test;
import org.ta4j.core.*;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.indicators.helpers.HighestValueIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.StopGainRule;

/**
 * 2-Period RSI Strategy
 *
 * @see <a href=
 * "http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:rsi2">
 * http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:rsi2</a>
 */
public class RSIStrategyTest {


    /**
     * @param series a bar series
     * @return a 2-period RSI strategy
     */
    public Strategy buildStrategy(BarSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        HighestValueIndicator highestValueIndicator = new HighestValueIndicator(new HighPriceIndicator(series), series.getBarCount());
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
        SMAIndicator longSma = new SMAIndicator(closePrice, 200);

        // We use a 14-period RSI indicator to identify buying
        // or selling opportunities within the bigger trend.
        RSIIndicator rsi = new RSIIndicator(closePrice, 14);

        // Entry rule
        Rule entryRule = new LowerValueRuleByDividendFactor(highestValueIndicator, closePrice, 3);
        entryRule = entryRule.and(new CrossedDownIndicatorRule(rsi, 30));

        // Exit rule
        Rule exitRule = new StopGainRule(closePrice, 20); // Trend
        return new BaseStrategy(entryRule, exitRule);
    }

    @Test
    public void test() {

        // Getting the bar series
        BarSeries series = CsvBarsLoader.loadCsvSeries("TWTR.csv");
        // Building the trading strategy
        Strategy strategy = buildStrategy(series);

        // Running the strategy
        BarSeriesManager seriesManager = new BarSeriesManager(series);
        TradingRecord tradingRecord = seriesManager.run(strategy);
        System.out.println("Number of trades for the strategy: " + tradingRecord.getTradeCount());
        for (Trade trade : tradingRecord.getTrades()) {
            System.out.println("Entry: " + trade.getEntry() + " Exit: " + trade.getExit());
        }

        // Analysis
        System.out.println(
                "Total profit for the strategy: " + new TotalProfitCriterion().calculate(series, tradingRecord));
        Assert.assertTrue(tradingRecord.getTrades().size() > 0);
    }

}
