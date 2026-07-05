package com.airtribe.splitwise.observer;

import com.airtribe.splitwise.model.Expense;
import com.airtribe.splitwise.model.User;
import java.util.Map;

public class AppNotifier implements ExpenseObserver {

    @Override
    public void onExpenseAdded(Expense expense) {
        System.out.println("\n [APP NOTIFICATION] New Expense Added: " + expense.getDescription());
        System.out.println("Paid by: " + expense.getPayer().getName() + " | Total: Rs. " + expense.getAmount());
        System.out.println("--- Share Breakdown ---");

        for (Map.Entry<User, Double> split : expense.getSplits().entrySet()) {
            System.out.println(" -> " + split.getKey().getName() + " owes Rs. " + split.getValue());
        }
        System.out.println("-----------------------\n");
    }
}