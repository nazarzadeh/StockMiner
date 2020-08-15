package com.stockminer.stockminer.indicators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stockminer.stockminer.OHLC;
import com.stockminer.stockminer.RuleScriptIndicator;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.PriceIndicator;
import org.ta4j.core.num.Num;

import java.util.function.Function;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OhlcRSIndicator extends PriceIndicator implements RuleScriptIndicator {
    OHLC ohlc;

    public OhlcRSIndicator(OHLC ohlc) {
        super(null,null);
        this.ohlc = ohlc;
    }

    public OhlcRSIndicator() {
        super(null,null);
    }

    public OhlcRSIndicator(BarSeries series, Function<Bar, Num> priceFunction) {
        super(series, priceFunction);
    }

    @Override
    public Indicator<Num> getIndicator(BarSeries barSeries) {
        return new OhlcRSIndicator(barSeries,ohlc.getPriceFunction());
    }

    public OHLC getOhlc() {
        return ohlc;
    }

    public void setOhlc(OHLC ohlc) {
        this.ohlc = ohlc;
    }
}
