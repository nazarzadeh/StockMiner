package com.stockminer.stockminer;

import org.ta4j.core.Bar;
import org.ta4j.core.num.Num;

import java.util.function.Function;

public enum OHLC {
    OPEN(Bar::getOpenPrice),
    HIGH(Bar::getHighPrice),
    LOW(Bar::getLowPrice),
    CLOSE(Bar::getClosePrice);

    private final Function<Bar, Num> priceFunction;

    OHLC(Function<Bar, Num> priceFunction) {
        this.priceFunction = priceFunction;
    }

    public Function<Bar, Num> getPriceFunction(){
        return this.priceFunction;
    }
}
