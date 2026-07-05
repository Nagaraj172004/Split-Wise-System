package com.airtribe.splitwise.model;

import com.airtribe.splitwise.util.Validator;
import java.util.Map;

public class Expense {
    private String id;
    private String description;
    private double amount;
    private User payer;
    private Map<User, Double> splits;

    public Expense(String id, String description, double amount, User payer, Map<User, Double> splits) {
        Validator.validateString(id, "Expense ID");
        Validator.validateString(description, "Description");
        Validator.validateAmount(amount);
        Validator.validateNotNull(payer, "Payer");
        Validator.validateNotNull(splits, "Splits map");

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.payer = payer;
        this.splits = splits;
    }

    public String getId() {
        return id; }
    public String getDescription() {
        return description; }
    public double getAmount() {
        return amount; }
    public User getPayer() {
        return payer; }
    public Map<User, Double> getSplits() {
        return splits; }
}