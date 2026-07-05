package com.airtribe.splitwise.observer;
import com.airtribe.splitwise.model.Expense;

public interface ExpenseObserver {
    void onExpenseAdded(Expense expense);
}