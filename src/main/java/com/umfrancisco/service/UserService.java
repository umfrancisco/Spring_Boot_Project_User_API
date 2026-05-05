package com.umfrancisco.service;

import java.util.List;
import com.umfrancisco.model.User;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getUsersByCity(String city);
    List<User> getUsersByCountry(String country);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
