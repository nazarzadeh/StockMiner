package com.stockminer.stockminer.service;

import com.stockminer.stockminer.RuleScript;
import com.stockminer.stockminer.controller.RuleHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.trading.rules.BooleanRule;

import java.util.List;

public class RuleServiceImpl implements RuleService {
    private BarSeries barSeries;

    public RuleServiceImpl(BarSeries barSeries) {
        this.barSeries = barSeries;
    }

    private Rule getEntryRuleSet(List<RuleScript> entryRs){
        Rule entryRule = entryRs.remove(0).getRule(barSeries);
        for (RuleScript rs:entryRs){
            Rule rule = rs.getRule(barSeries);
            entryRule = entryRule.and(rule);
        }
        return entryRule;
    }

    private Rule getExitRuleSet(List<RuleScript> exitRs){
        Rule exitRule =  exitRs.remove(0).getRule(barSeries);
        for (RuleScript rs:exitRs){
            Rule rule = rs.getRule(barSeries);
            exitRule = exitRule.and(rule);
        }
        return exitRule;
    }

    @Override
    public BaseStrategy getStrategy(RuleHolder ruleHolder) {
        Rule entryRule = getEntryRuleSet(ruleHolder.getEntryRules());
        Rule exitRule = getExitRuleSet(ruleHolder.getExitRules());
        return new BaseStrategy(entryRule,exitRule);
    }
}
