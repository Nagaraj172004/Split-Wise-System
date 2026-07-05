# 💸 Splitwise-Style Expense Tracker System

A console-based Core Java application that simulates a shared expense tracking engine. This project is designed to help groups of friends or flatmates record shared expenses and calculate exact balances (who owes whom) without any mental math. 

This project was built to demonstrate advanced Object-Oriented Programming (OOP) principles, clean architecture, and Gang of Four (GoF) Design Patterns.

## 🌟 Key Features

* [cite_start]**Group & User Management:** Create users and group them together to track shared expenses[cite: 3447, 3448].
* [cite_start]**Dynamic Expense Splitting:** Supports multiple ways to divide bills, including `EQUAL` splits and `EXACT` amount splits[cite: 3450, 3451, 3452]. 
* [cite_start]**Automated Notifications:** Automatically broadcasts new expense alerts to registered channels (like App Notifications and Email)[cite: 3457, 3458].
* [cite_start]**Smart Balance Calculator:** Iterates through all group expenses to calculate the exact net balance (positive or negative) for every member[cite: 3460, 3461].
* [cite_start]**Strict Input Validation:** Centralized error handling rejects invalid inputs (e.g., exact shares not matching the total, negative amounts, or missing group members) gracefully[cite: 3463, 3464, 3465].

## 🏗️ Architecture & Design Patterns Demonstrated

This application is built with a highly decoupled architecture, making it completely modular and strictly adhering to the **Open/Closed Principle**:

* [cite_start]**Strategy Pattern:** Used to handle the dynamic split algorithms (`EqualSplit` and `ExactSplit`)[cite: 3474, 3475]. [cite_start]Adding a new split method (like Percentage) later requires zero modifications to existing classes[cite: 3476].
* [cite_start]**Factory Pattern:** Utilized a `SplitFactory` to centralize the instantiation of the correct split strategy objects[cite: 3477, 3478]. 
* **Observer Pattern:** Handled the notification engine. [cite_start]The `Group` maintains a decoupled list of `ExpenseObserver` interfaces, automatically triggering updates to the `AppNotifier` and `EmailNotifier` without knowing their concrete implementations[cite: 3479, 3480].
* [cite_start]**Singleton Pattern:** Used to create the `ExpenseManager`, ensuring exactly one centralized instance manages all users, groups, and transactions across the entire application[cite: 3481].

## 📂 Project Structure

[cite_start]The codebase is organized into distinct packages for a clean separation of concerns[cite: 3486]:
* `model/` - Core domain entities (User, Group, Expense)
* `strategy/` - Split algorithm implementations
* `factory/` - Object creation logic
* `observer/` - Notification listeners
* `util/` - Validation and Balance Math Engines
* `core/` - Singleton Manager and execution entry point

## 🚀 How to Run

1. Clone this repository to your local machine.
2. Open the project in your preferred Java IDE (IntelliJ IDEA recommended).
3. Navigate to `src/com/airtribe/splitwise/core/SplitwiseMain.java` (or your Main execution file).
4. Run the main method to execute the simulation and view the terminal output of the expense calculations and notifications.

## 🔮 Future Scope

* **Concurrency Control:** Implement thread-safe locking mechanisms to handle simultaneous expense additions from multiple users in a group.
* **Database Persistence:** Replace the in-memory collections with a relational database (PostgreSQL/MySQL) to persist user and group balances.
* **Debt Simplification Algorithm:** Add a graph-based algorithm to minimize the total number of transactions required to settle all debts in a group.

> Built with passion at Airtribe. 🚀
