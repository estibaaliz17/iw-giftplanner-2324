package com.example.application.security;

import com.example.application.data.User;
import com.example.application.data.UserRepository;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;

    @Autowired
    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<User> get() {
        Optional<UserDetails> userDetailsOptional = authenticationContext.getAuthenticatedUser(UserDetails.class);
        if (userDetailsOptional.isPresent()) {
            UserDetails userDetails = userDetailsOptional.get();
            String username = userDetails.getUsername();
            return userRepository.findByUsername(username);
        } else {
            return Optional.empty();
        }
    }

    public void logout() {
        authenticationContext.logout();
    }
}
