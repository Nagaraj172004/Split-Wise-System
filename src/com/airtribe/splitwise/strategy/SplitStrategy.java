package com.airtribe.splitwise.strategy;

import com.airtribe.splitwise.model.User;
import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<User, Double> calculateSplit(
            double totalAmount,
            List<User> members,
            Map<User, Double> exactShares);
}