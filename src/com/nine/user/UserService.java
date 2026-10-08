package com.nine.user;

import java.util.UUID;

public class UserService {
    public UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User[] getUsers() {
        return userDao.getUsers();
    }

    public User getUser(UUID id) {
        return userDao.getUser(id);
    }
}
