package com.nine.user;

import java.util.UUID;

public class User {
    public UUID id;
    public String name;

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
