package com.airtribe.splitwise.core;

import com.airtribe.splitwise.model.User;
import com.airtribe.splitwise.observer.AppNotifier;
import com.airtribe.splitwise.observer.EmailNotifier;

import java.util.HashMap;
import java.util.Map;

public class SplitwiseMain {
    public static void main(String[] args) {
        // 1. Get our Singleton Manager
        ExpenseManager manager = ExpenseManager.getInstance();

        // 2. Create Users
        User alice = manager.createUser("U1", "Alice");
        User bob = manager.createUser("U2", "Bob");
        User carol = manager.createUser("U3", "Carol");

        // 3. Create Group & Add Members
        manager.createGroup("G1", "Goa Trip");
        manager.addUserToGroup("U1", "G1");
        manager.addUserToGroup("U2", "G1");
        manager.addUserToGroup("U3", "G1");

        // 4. Register Notification Observers
        manager.getGroup("G1").addObserver(new AppNotifier());
        manager.getGroup("G1").addObserver(new EmailNotifier());

        System.out.println("\n--- SCENARIO 1: EQUAL SPLIT ---");
        // Alice pays Rs. 900 for a hotel (Equal split)
        manager.addExpense("G1", "E1", "Hotel Booking", 900.0, "U1", "EQUAL", null);

        // Show Balances
        manager.showBalances("G1");

        System.out.println("\n--- SCENARIO 2: EXACT SPLIT ---");
        // Bob pays Rs. 550 for Dinner (Exact split: Alice 250, Bob 180, Carol 120)
        Map<User, Double> dinnerSplits = new HashMap<>();
        dinnerSplits.put(alice, 250.0);
        dinnerSplits.put(bob, 180.0);
        dinnerSplits.put(carol, 120.0);

        manager.addExpense("G1", "E2", "Seafood Dinner", 550.0, "U2", "EXACT", dinnerSplits);

        // Show Updated Balances
        manager.showBalances("G1");

        System.out.println("\n--- SCENARIO 3: TESTING VALIDATION ---");
        try {
            // Trying to split 500 into shares that don't add up (100 + 100 + 100 = 300)
            Map<User, Double> badSplits = new HashMap<>();
            badSplits.put(alice, 100.0);
            badSplits.put(bob, 100.0);
            badSplits.put(carol, 100.0);

            System.out.println("Attempting an invalid exact split...");
            manager.addExpense("G1", "E3", "Snacks", 500.0, "U1", "EXACT", badSplits);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ SYSTEM BLOCKED INVALID EXPENSE: " + e.getMessage());
        }

        System.out.println("\n--- SCENARIO 4: PERCENTAGE SPLIT ---");
        // Carol pays Rs. 1000 for Gas. Split: Alice 50%, Bob 25%, Carol 25%

        // We need to fetch the users again for the map
        User aliceUser = manager.getGroup("G1").getMembers().get(0); // Alice
        User bobUser = manager.getGroup("G1").getMembers().get(1);   // Bob
        User carolUser = manager.getGroup("G1").getMembers().get(2); // Carol

        Map<User, Double> percentSplits = new HashMap<>();
        percentSplits.put(aliceUser, 50.0);
        percentSplits.put(bobUser, 25.0);
        percentSplits.put(carolUser, 25.0);

        manager.addExpense("G1", "E4", "Road Trip Gas", 1000.0, "U3", "PERCENTAGE", percentSplits);

        // Show Final Balances
        manager.showBalances("G1");
    }
}