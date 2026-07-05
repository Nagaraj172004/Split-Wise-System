package com.airtribe.splitwise.util;

import com.airtribe.splitwise.model.Expense;
import com.airtribe.splitwise.model.Group;
import com.airtribe.splitwise.model.User;

import java.util.HashMap;
import java.util.Map;

public class BalanceCalculator {

    public static Map<User, Double> calculateBalances(Group group) {
        Map<User, Double> balances = new HashMap<>();

        for (User member : group.getMembers()) {
            balances.put(member, 0.0);
        }

        for (Expense expense : group.getExpenses()) {
            User payer = expense.getPayer();

            balances.put(payer, balances.getOrDefault(payer, 0.0) + expense.getAmount());

            for (Map.Entry<User, Double> split : expense.getSplits().entrySet()) {
                User borrower = split.getKey();
                double owedAmount = split.getValue();
                balances.put(borrower, balances.getOrDefault(borrower, 0.0) - owedAmount);
            }
        }

        return balances;
    }
}