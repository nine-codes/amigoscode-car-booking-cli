package com.nine.user;

import java.util.Arrays;
import java.util.UUID;

public class UserDao {
    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.fromString("567fc5e9-e94c-4003-95c3-5c3b3b2f8d82"), "James"),
                new User(UUID.fromString("8f942095-26ac-4a64-b67c-3229ed5df70f"), "John"),
                new User(UUID.fromString("0b4adf9f-4349-47f8-bcff-48984b362435"), "Steve"),
                new User(UUID.fromString("d6af29b2-9b79-4619-9788-d4fc5d773455"), "Michael")
        };
    }

    public User getUser(UUID id) {
        for (User user : users) {
            if(user.id.equals(id)) {
                return user;
            }
        }

        return null;
    }

    public User[] getUsers() {
        return users;
    }
}
