package com.stockminer.stockminer.ruleScript;

import com.stockminer.stockminer.RuleScript;
import com.stockminer.stockminer.RuleScriptIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleIndicatorCrossedValueRS implements RuleScript {
    RuleScriptIndicator indicator;
    int value;

    @Override
    public Rule getRule(BarSeries barSeries) {
        return new CrossedDownIndicatorRule(indicator.getIndicator(barSeries),value);
    }
}
