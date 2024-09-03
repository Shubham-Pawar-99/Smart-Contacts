package com.smart_contact.smart_contact.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart_contact.smart_contact.entity.User;
import com.smart_contact.smart_contact.helper.AppConstants;
import com.smart_contact.smart_contact.helper.ResourceNotFoundException;
import com.smart_contact.smart_contact.repository.UserRepo;
import com.smart_contact.smart_contact.service.UserService;



@Service
public class ServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User saveUser(User user) {
        // generate user id
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        // password encoder
        user.setPassword(encoder.encode(user.getPassword()));
        // set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return repo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    @Override
    public Optional<User> updatUser(User user) {
        User user2 = repo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Update user
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setEnabled(user.isEnabled());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        // Save the User in the database
        User save = repo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        repo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user = repo.findById(userId).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = repo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUser() {
        List<User> user = repo.findAll();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
    return repo.findByEmail(email).orElse(null);
    }
    

}
