package com.stockminer.stockminer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stockminer.stockminer.indicators.HighestValueRSIndicator;
import com.stockminer.stockminer.indicators.NumberRSIndicator;
import com.stockminer.stockminer.indicators.OhlcRSIndicator;
import com.stockminer.stockminer.indicators.RsiRSIndicator;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.num.Num;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RsiRSIndicator.class, name = "Rsi"),
        @JsonSubTypes.Type(value = NumberRSIndicator.class, name = "Number"),
        @JsonSubTypes.Type(value = OhlcRSIndicator.class, name = "Ohlc"),
        @JsonSubTypes.Type(value = HighestValueRSIndicator.class, name = "HighestValue"),
})
public interface RuleScriptIndicator {
    Indicator<Num> getIndicator(BarSeries barSeries);
}
