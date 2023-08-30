package com.ms.userservice.service;

import java.util.List;

import com.ms.userservice.entities.User;

public interface UserService {
    
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
}
