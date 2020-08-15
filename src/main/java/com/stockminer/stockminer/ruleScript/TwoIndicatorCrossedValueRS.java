package com.stockminer.stockminer.ruleScript;

import com.stockminer.stockminer.RuleScript;
import com.stockminer.stockminer.RuleScriptIndicator;
import com.stockminer.stockminer.ta4jExtension.LowerValueRuleByDividendFactor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;
import org.ta4j.core.num.Num;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoIndicatorCrossedValueRS implements RuleScript {
    RuleScriptIndicator firstIndicator;
    RuleScriptIndicator secondIndicator;
    short dividendFactor;

    @Override
    public Rule getRule(BarSeries barSeries) {
        return new LowerValueRuleByDividendFactor(this.firstIndicator.getIndicator(barSeries),
                this.secondIndicator.getIndicator(barSeries), dividendFactor);
    }
}
