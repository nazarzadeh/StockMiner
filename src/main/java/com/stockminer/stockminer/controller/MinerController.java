package com.stockminer.stockminer.controller;

import com.stockminer.stockminer.service.CsvFileLoaderServiceImpl;
import com.stockminer.stockminer.service.StrategyRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;

@RestController
public class MinerController {
    @PostMapping(path = "/rule")
    public String rule(@RequestBody RuleHolder ruleHolder){
        CsvFileLoaderServiceImpl csvFileLoaderService = new CsvFileLoaderServiceImpl("TWTR.csv");
        StrategyRunner strategyRunner = new StrategyRunner(csvFileLoaderService);
        return "Total profit for the strategy: " + strategyRunner.getTotalProfit(ruleHolder);
    }
}
