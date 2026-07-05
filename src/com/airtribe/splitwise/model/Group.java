package com.airtribe.splitwise.model;

import com.airtribe.splitwise.observer.ExpenseObserver;
import com.airtribe.splitwise.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String id;
    private String name;
    private List<User> members;
    private List<Expense> expenses;
    private List<ExpenseObserver> observers; // The blind list of notification listeners

    public Group(String id, String name) {
        Validator.validateString(id, "Group ID");
        Validator.validateString(name, "Group Name");
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addMember(User user) {
        Validator.validateNotNull(user, "User");
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    public void addObserver(ExpenseObserver observer) {
        Validator.validateNotNull(observer, "Expense Observer");
        observers.add(observer);
    }

    public void addExpense(Expense expense) {
        Validator.validateNotNull(expense, "Expense");
        expenses.add(expense);

        // Broadcast the update to all listeners (Requirement 5)
        for (ExpenseObserver observer : observers) {
            observer.onExpenseAdded(expense);
        }
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<User> getMembers() { return members; }
    public List<Expense> getExpenses() { return expenses; }
}