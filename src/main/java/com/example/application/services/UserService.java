package com.example.application.services;

import com.example.application.data.Role;
import com.example.application.data.User;
import com.example.application.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    public User registerUser(String username, String name, String surname, String password, String email, String phone) {
        if (repository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setHashedPassword(passwordEncoder.encode(password));
        user.setMail(email);
        user.setPhone(phone);
        user.setRoles(Collections.singleton(Role.USER)); // Asignar el rol de usuario

        return repository.save(user);
    }

    public Optional<User> getByUsername(String username) {
        return Optional.ofNullable(repository.findByUsername(username));
    }

}
