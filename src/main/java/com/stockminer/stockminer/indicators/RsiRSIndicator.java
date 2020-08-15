package com.stockminer.stockminer.indicators;

import com.stockminer.stockminer.RuleScriptIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RsiRSIndicator implements RuleScriptIndicator {
    private int barCount;
    private OhlcRSIndicator ohlc;

    @Override
    public Indicator<Num> getIndicator(BarSeries barSeries) {
        Indicator<Num> indicator = this.ohlc.getIndicator(barSeries);
        return new RSIIndicator(indicator,barCount);
    }
}
