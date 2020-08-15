package com.stockminer.stockminer.service;

import com.stockminer.stockminer.controller.RuleHolder;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BarSeriesManager;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;
import org.ta4j.core.num.Num;

public class StrategyRunner {
    private BarSeries barSeries;
    private RuleService ruleService;

    public StrategyRunner(LoaderService loaderService) {
        this.barSeries = loaderService.getBarSeries();
        this.ruleService = new RuleServiceImpl(this.barSeries);
    }

    public TradingRecord execute(RuleHolder ruleHolder){
        BaseStrategy strategy = ruleService.getStrategy(ruleHolder);
        BarSeriesManager barSeriesManager = new BarSeriesManager(barSeries);
        return barSeriesManager.run(strategy);
    }

    public Num getTotalProfit(RuleHolder ruleHolder){
        return new TotalProfitCriterion().calculate(barSeries, execute(ruleHolder));
    }
}
