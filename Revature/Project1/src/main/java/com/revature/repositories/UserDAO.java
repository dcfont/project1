package com.revature.repositories;

import com.revature.models.User;

public interface UserDAO {

    public void createEmployee(User user);
    public void createManager(User user);
    public User getUserUsername(String username);
    public User getUserEmail(String email);
    public User validateCredentials(String username,  String password);
}
