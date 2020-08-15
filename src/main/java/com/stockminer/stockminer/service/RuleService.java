package com.stockminer.stockminer.service;

import com.stockminer.stockminer.controller.RuleHolder;
import org.ta4j.core.BaseStrategy;

public interface RuleService {
    BaseStrategy getStrategy(RuleHolder ruleHolder);
}
