package com.growthx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.growthx.model.Admin;
import com.growthx.model.User;
import com.growthx.repository.AdminRepo;
import com.growthx.repository.UserRepo;

import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private AdminRepo adminRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        // Check if the user exists in the User repository
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new CustomOAuth2User(oAuth2User, "USER");
        }
        
        // Check if the user exists in the Admin repository
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return new CustomOAuth2User(oAuth2User, "ADMIN");
        }

        // Default to USER role if not found in either repository
        return new CustomOAuth2User(oAuth2User, "USER");
    }
}
