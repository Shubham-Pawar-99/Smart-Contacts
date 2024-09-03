package com.smart_contact.smart_contact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smart_contact.smart_contact.entity.User;
import com.smart_contact.smart_contact.helper.Helper;
import com.smart_contact.smart_contact.service.UserService;



@ControllerAdvice
public class RootController {
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void getLoggedInUser(Model model, Authentication authentication) {

        if (authentication==null) {
            return;
        }
        System.out.println("Adding user information to the model");
        // get authenticateed logged user details
        String username = Helper.getEmailOfLoggedInUsers(authentication);
        User user = userService.getUserByEmail(username);

        // if (user == null) {
        //     model.addAttribute(username, null);
        // } else {
        System.out.println(user);
            System.out.println(user.getName());
            System.out.println(user.getEmail());
        // }
        model.addAttribute("loggedUser", user);

    }

}

