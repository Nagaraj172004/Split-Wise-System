package com.airtribe.splitwise.strategy;

import com.airtribe.splitwise.model.User;
import com.airtribe.splitwise.util.Validator;

import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public Map<User, Double> calculateSplit(double totalAmount, List<User> members, Map<User, Double> exactShares) {
        Validator.validateNotNull(exactShares, "Exact shares map");

        double calculatedSum = 0;
        for (Double share : exactShares.values()) {
            calculatedSum += share;
        }

        Validator.validateSplitSum(totalAmount, calculatedSum);

        return exactShares;
    }
}