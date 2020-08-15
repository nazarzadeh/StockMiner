package com.stockminer.stockminer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockminer.stockminer.controller.RuleHolder;
import com.stockminer.stockminer.indicators.HighestValueRSIndicator;
import com.stockminer.stockminer.indicators.OhlcRSIndicator;
import com.stockminer.stockminer.indicators.RsiRSIndicator;
import com.stockminer.stockminer.ruleScript.SingleIndicatorCrossedValueRS;
import com.stockminer.stockminer.ruleScript.StopGainRS;
import com.stockminer.stockminer.ruleScript.TwoIndicatorCrossedValueRS;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class JacksonTest {
    @Test
    public void ObjectToJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        OhlcRSIndicator ohlcClose = new OhlcRSIndicator(OHLC.CLOSE);
        RsiRSIndicator rsiIndicator = new RsiRSIndicator(14, ohlcClose);
        HighestValueRSIndicator highestValueRSIndicator = new HighestValueRSIndicator(ohlcClose,1000);
        SingleIndicatorCrossedValueRS firstRuleScript = new SingleIndicatorCrossedValueRS(rsiIndicator, 30);
        TwoIndicatorCrossedValueRS secondRuleScript = new TwoIndicatorCrossedValueRS(highestValueRSIndicator, ohlcClose, (short)3);
        List<RuleScript> entryRuleScripts = List.of(firstRuleScript,secondRuleScript);

        StopGainRS stopGainRS = new StopGainRS(20);
        List<RuleScript> exitRuleScripts = List.of(stopGainRS);
        RuleHolder ruleHolder = new RuleHolder(entryRuleScripts,exitRuleScripts);

        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ruleHolder);

//        RuleHolder list = objectMapper.readValue(s,RuleHolder.class);
        System.out.println(s);
//        System.out.println(list);
    }
}
