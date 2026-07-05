package com.airtribe.splitwise.strategy;

import com.airtribe.splitwise.model.User;
import com.airtribe.splitwise.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public Map<User, Double> calculateSplit(double totalAmount, List<User> members, Map<User, Double> percentages) {
        Validator.validateNotNull(percentages, "Percentages map");

        double totalPercentage = 0;
        for (Double percent : percentages.values()) {
            totalPercentage += percent;
        }

        if (Math.abs(100.0 - totalPercentage) > 0.01) {
            throw new IllegalArgumentException("Percentages must exactly sum up to 100.");
        }

        Map<User, Double> splits = new HashMap<>();
        for (Map.Entry<User, Double> entry : percentages.entrySet()) {
            User user = entry.getKey();
            double percentage = entry.getValue();

            double exactAmount = (percentage / 100.0) * totalAmount;

            exactAmount = Math.round(exactAmount * 100.0) / 100.0;

            splits.put(user, exactAmount);
        }

        return splits;
    }
}