package com.airtribe.splitwise.util;

import com.airtribe.splitwise.model.Group;
import com.airtribe.splitwise.model.User;

public class Validator {

    public static void validateString(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank.");
        }
    }

    public static void validateNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
    }

    public static void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Expense amount must be greater than 0.");
        }
    }

    public static void validateGroupHasMembers(Group group) {
        if (group.getMembers().isEmpty()) {
            throw new IllegalArgumentException("A group must have at least one member before recording an expense.");
        }
    }

    public static void validatePayerInGroup(User payer, Group group) {
        if (!group.getMembers().contains(payer)) {
            throw new IllegalArgumentException("The payer must be a member of the group.");
        }
    }

    public static void validateSplitSum(double totalAmount, double calculatedSum) {
        if (Math.abs(totalAmount - calculatedSum) > 0.01) {
            throw new IllegalArgumentException("Split shares must exactly sum up to the total amount.");
        }
    }
}