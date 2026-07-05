package com.airtribe.splitwise.factory;

import com.airtribe.splitwise.strategy.EqualSplitStrategy;
import com.airtribe.splitwise.strategy.ExactSplitStrategy;
import com.airtribe.splitwise.strategy.SplitStrategy;
import com.airtribe.splitwise.strategy.PercentageSplitStrategy;

public class SplitFactory {

    public static SplitStrategy getStrategy(String splitType) {
        if (splitType == null) {
            throw new IllegalArgumentException("Split type cannot be null.");
        }

        if (splitType.equalsIgnoreCase("EQUAL")) {
            return new EqualSplitStrategy();
        } else if (splitType.equalsIgnoreCase("EXACT")) {
            return new ExactSplitStrategy();
        }else if (splitType.equalsIgnoreCase("PERCENTAGE")) {
            return new PercentageSplitStrategy();
        } else {
            throw new IllegalArgumentException("Unsupported split type: " + splitType);
        }
    }
}