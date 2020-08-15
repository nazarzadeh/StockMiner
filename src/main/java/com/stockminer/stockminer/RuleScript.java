package com.stockminer.stockminer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stockminer.stockminer.ruleScript.SingleIndicatorCrossedValueRS;
import com.stockminer.stockminer.ruleScript.StopGainRS;
import com.stockminer.stockminer.ruleScript.TwoIndicatorCrossedValueRS;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleIndicatorCrossedValueRS.class, name = "SingleIndicatorCrossedValueRS"),
        @JsonSubTypes.Type(value = TwoIndicatorCrossedValueRS.class, name = "TwoIndicatorCrossedValueRS"),
        @JsonSubTypes.Type(value = StopGainRS.class, name = "StopGainRS"),
})
public interface RuleScript {
    Rule getRule(BarSeries barSeries);
}
