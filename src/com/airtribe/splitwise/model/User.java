package com.airtribe.splitwise.model;

import com.airtribe.splitwise.util.Validator;

public class User {
    private String id;
    private String name;

    public User(String id, String name) {
        Validator.validateString(id, "User ID");
        Validator.validateString(name, "User Name");

        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}