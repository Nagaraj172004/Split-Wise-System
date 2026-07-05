package com.airtribe.splitwise.strategy;

import com.airtribe.splitwise.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public Map<User, Double> calculateSplit(double totalAmount, List<User> members, Map<User, Double> exactShares) {
        Map<User, Double> splits = new HashMap<>();

        double splitAmount = totalAmount / members.size();

        for (User member : members) {
            splits.put(member, splitAmount);
        }

        return splits;
    }
}