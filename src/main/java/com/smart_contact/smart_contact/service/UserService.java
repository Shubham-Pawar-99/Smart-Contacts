package com.smart_contact.smart_contact.service;

import java.util.List;
import java.util.Optional;

import com.smart_contact.smart_contact.entity.User;



public interface UserService {
    
    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updatUser(User user);

    void deleteUser(String id);
   
    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUser();

    User getUserByEmail(String email);
}
