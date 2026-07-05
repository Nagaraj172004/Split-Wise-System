package com.airtribe.splitwise.core;

import com.airtribe.splitwise.factory.SplitFactory;
import com.airtribe.splitwise.model.Expense;
import com.airtribe.splitwise.model.Group;
import com.airtribe.splitwise.model.User;
import com.airtribe.splitwise.strategy.SplitStrategy;
import com.airtribe.splitwise.util.BalanceCalculator;
import com.airtribe.splitwise.util.Validator;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {
    // The single, static instance of the manager
    private static ExpenseManager instance;

    // In-memory databases for Users and Groups
    private Map<String, User> userMap;
    private Map<String, Group> groupMap;

    // Private constructor prevents anyone else from using 'new ExpenseManager()'
    private ExpenseManager() {
        userMap = new HashMap<>();
        groupMap = new HashMap<>();
    }

    // The only way to get the manager instance
    public static synchronized ExpenseManager getInstance() {
        if (instance == null) {
            instance = new ExpenseManager();
        }
        return instance;
    }

    public User createUser(String id, String name) {
        User user = new User(id, name);
        userMap.put(id, user);
        return user;
    }

    public Group createGroup(String id, String name) {
        Group group = new Group(id, name);
        groupMap.put(id, group);
        return group;
    }

    public void addUserToGroup(String userId, String groupId) {
        User user = userMap.get(userId);
        Group group = groupMap.get(groupId);
        Validator.validateNotNull(user, "User");
        Validator.validateNotNull(group, "Group");
        group.addMember(user);
    }

    // The heart of the application: Adding an expense
    public void addExpense(String groupId, String expenseId, String description, double amount,
                           String payerId, String splitType, Map<User, Double> exactShares) {

        Group group = groupMap.get(groupId);
        User payer = userMap.get(payerId);

        // 1. Strict Validation
        Validator.validateNotNull(group, "Group");
        Validator.validateNotNull(payer, "Payer");
        Validator.validateGroupHasMembers(group);
        Validator.validatePayerInGroup(payer, group);

        // 2. Factory creates the strategy dynamically
        SplitStrategy strategy = SplitFactory.getStrategy(splitType);

        // 3. Strategy calculates the exact splits
        Map<User, Double> splits = strategy.calculateSplit(amount, group.getMembers(), exactShares);

        // 4. Create and save the expense
        Expense expense = new Expense(expenseId, description, amount, payer, splits);
        group.addExpense(expense); // This automatically triggers our Observers!
    }

    public void showBalances(String groupId) {
        Group group = groupMap.get(groupId);
        Validator.validateNotNull(group, "Group");

        System.out.println("=== Balances for Group: " + group.getName() + " ===");
        Map<User, Double> balances = BalanceCalculator.calculateBalances(group);

        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            double balance = entry.getValue();
            String name = entry.getKey().getName();

            if (balance > 0.01) {
                System.out.println(name + " gets back Rs. " + String.format("%.2f", balance));
            } else if (balance < -0.01) {
                System.out.println(name + " owes Rs. " + String.format("%.2f", Math.abs(balance)));
            } else {
                System.out.println(name + " is settled up.");
            }
        }
        System.out.println("=========================================\n");
    }

    public Group getGroup(String groupId) {
        return groupMap.get(groupId);
    }
}