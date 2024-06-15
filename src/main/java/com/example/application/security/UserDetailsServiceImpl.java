package com.example.application.security;

import com.example.application.data.User;
import com.example.application.data.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities());
    }

    private static List<GrantedAuthority> getAuthorities() {
        // Since roles are not used, return a default authority
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
