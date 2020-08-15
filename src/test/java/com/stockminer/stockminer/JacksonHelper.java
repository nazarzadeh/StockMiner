package com.stockminer.stockminer;

import java.util.List;

public class JacksonHelper {
    private List<RuleScript> RSlist;

    public JacksonHelper() {
    }

    public JacksonHelper(List<RuleScript> RSlist) {
        this.RSlist = RSlist;
    }

    public List<RuleScript> getRSlist() {
        return RSlist;
    }

    public void setRSlist(List<RuleScript> RSlist) {
        this.RSlist = RSlist;
    }
}
