package com.smart_contact.smart_contact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart_contact.smart_contact.entity.User;
import com.smart_contact.smart_contact.forms.UserForm;
import com.smart_contact.smart_contact.helper.Message;
import com.smart_contact.smart_contact.helper.MessageType;
import com.smart_contact.smart_contact.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String index() {
        return "redirect:/home";
    }
    

    // home route
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("HomePAge Handler");
        model.addAttribute("name", "Shubham Pawar");
        model.addAttribute("age", "25");
        model.addAttribute("place", "Pune");
        model.addAttribute("education", "MCA");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page Loading..");
        return "about";
    }

    // Servicce route
    @RequestMapping("/service")
    public String servicePage() {
        System.out.println("service Page Loading..");
        return "service";
    }

    // contact route
    @RequestMapping("/contact")
    public String contactpagee() {
        System.out.println("contact Page Loading..");
        return "contact";
    }

    @RequestMapping("/login")
    public String loginpage() {
        System.out.println("login Page Loading..");
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        // System.out.println("register Page Loading..");
        UserForm userForm = new UserForm();
        // userForm.setName("Shubham");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // Processing
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession httpSession) {
        System.out.println("Register processing");
        System.out.println(userForm);

        // form validation
        if (rBindingResult.hasErrors()) {
            return "register";
        }


        // userService

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/510px-Default_pfp.svg.png?20220226140232");

        // User saveUser = userService.saveUser(user);
        userService.saveUser(user);

        // Add message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        httpSession.setAttribute("message", message);

        System.out.println("saved user");

        return "redirect:/register";
    }

}

