package com.stockminer.stockminer.indicators;

import com.stockminer.stockminer.RuleScriptIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.FixedDecimalIndicator;
import org.ta4j.core.num.Num;

/**
 * To represent numbers and prices
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberRSIndicator implements RuleScriptIndicator {
    int num;

    @Override
    public Indicator<Num> getIndicator(BarSeries barSeries) {
        return null;
    }
}
