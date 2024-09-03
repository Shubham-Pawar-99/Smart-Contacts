package com.smart_contact.smart_contact.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.smart_contact.smart_contact.entity.Providers;
import com.smart_contact.smart_contact.entity.User;
import com.smart_contact.smart_contact.helper.AppConstants;
import com.smart_contact.smart_contact.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        System.out.println(authorizedClientRegistrationId);

        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        // set the default fields for user
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");

        // If user is login with google account
        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This Account is created using google");

            // if user is login with github account
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString()
                    : oauthUser.getAttribute("login").toString() + "@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            String providerUserId = oauthUser.getName();

            // set the user details
            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);

            user.setAbout("This Account is created using github");
        }

        /*
         * 
         * // save data into database
         * String email = user.getAttribute("email").toString();
         * String name = user.getAttribute("name").toString();
         * String picture = user.getAttribute("picture").toString();
         * 
         * // create user and save it in database
         * User user2 = new User();
         * user2.setEmail(email);
         * user2.setName(name);
         * user2.setProfilePic(picture);
         * user2.setPassword("password");
         * user2.setUserId(UUID.randomUUID().toString());
         * user2.setProvider(Providers.GOOGLE);
         * user2.setEnabled(true);
         * 
         * user2.setEmailVerified(true);
         * user2.setProviderUserId(user.getName());
         * user2.setRoleList(List.of(AppConstants.ROLE_USER));
         * user2.setAbout("This account is created using google");
         * /
         * // save the user
         * 
         * User user3 = repo.findByEmail(email).orElse(null);
         * 
         * if (user3 == null) {
         * repo.save(user2);
         * System.out.println("User saved using google");
         * }
         * 
         */

        // check the user is present in the databse
        User user2 = repo.findByEmail(user.getEmail()).orElse(null);

        // if use is not present in the databs=ase then save it
        if (user2 == null) {
            repo.save(user);
            System.out.println("User saved " + user.getEmail());
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}

