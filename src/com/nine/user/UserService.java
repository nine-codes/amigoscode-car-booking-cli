package com.nine.user;

public class UserService {
    public UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User[] getUsers() {
        return userDao.getUsers();
    }
}
