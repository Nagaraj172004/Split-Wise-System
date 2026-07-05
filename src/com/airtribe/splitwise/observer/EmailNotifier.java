package com.airtribe.splitwise.observer;

import com.airtribe.splitwise.model.Expense;

public class EmailNotifier implements ExpenseObserver {

    @Override
    public void onExpenseAdded(Expense expense) {
        System.out.println("[EMAIL NOTIFICATION] Sending email summary to all group members for: " + expense.getDescription());
    }
}