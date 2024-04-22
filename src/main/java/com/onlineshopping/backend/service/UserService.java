package com.onlineshopping.backend.service;

import com.onlineshopping.backend.model.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getUsers();

    void deleteUser(String email);

    User getUser(String email);

    @Transactional
    void deleteUserById(Long id);
}
