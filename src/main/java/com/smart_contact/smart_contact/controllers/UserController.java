package com.smart_contact.smart_contact.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // @Autowired
    // private UserService userService;

    // @ModelAttribute
    // public void getLoggedInUser(Model model, Authentication authentication) {
    //     System.out.println("Adding user information to the model");
    //     // get authenticateed logged user details
    //     String username = Helper.getEmailOfLoggedInUsers(authentication);
    //     User user = userService.getUserByEmail(username);
    //     System.out.println(user.getName());
    //     System.out.println(user.getEmail());

    //     model.addAttribute("user", user);
    // }

    // user dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        System.out.println("User Dashboard");
        return "user/dashboard";
    }

    // user Profile
    @GetMapping("/profile")
    public String userProfile(Authentication authentication) {
        System.out.println("User Profile");
        return "user/profile";
    }

    // user add contact

    // user view contact

    // user delete contact

    // user edit contact
}

