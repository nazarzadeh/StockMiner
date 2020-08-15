package com.stockminer.stockminer.indicators;

import com.stockminer.stockminer.RuleScriptIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.HighestValueIndicator;
import org.ta4j.core.num.Num;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighestValueRSIndicator implements RuleScriptIndicator {
    RuleScriptIndicator indicator;
    int barCount;

    @Override
    public Indicator<Num> getIndicator(BarSeries barSeries) {
        Indicator<Num> indicator = this.indicator.getIndicator(barSeries);
        return new HighestValueIndicator(indicator,barCount);
    }
}
