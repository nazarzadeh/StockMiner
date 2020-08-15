package com.stockminer.stockminer.ruleScript;

import com.stockminer.stockminer.OHLC;
import com.stockminer.stockminer.RuleScript;
import com.stockminer.stockminer.indicators.OhlcRSIndicator;
import com.stockminer.stockminer.ta4jExtension.CustomStopGainRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.StopGainRule;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopGainRS implements RuleScript {
    int gainPercentage;

    @Override
    public Rule getRule(BarSeries barSeries) {
        OhlcRSIndicator closePrice = new OhlcRSIndicator(OHLC.CLOSE);
        Indicator<Num> indicator = closePrice.getIndicator(barSeries);
        return new CustomStopGainRule((OhlcRSIndicator) indicator, gainPercentage);
    }
}
