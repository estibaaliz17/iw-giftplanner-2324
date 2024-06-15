package com.example.application.services;

import com.example.application.data.User;
import com.example.application.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
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

    // Método corregido para utilizar Specification<User> y Pageable
    public Page<User> list(Specification<User> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    // Método para cargar un usuario por nombre de usuario
    public User loadUserByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    // Método para registrar un usuario con seguridad mejorada de contraseñas
    public boolean registerNewUser(String username, String password) {
        Optional<User> existingUser = repository.findByUsername(username);
        if (existingUser.isPresent()) {
            return false; // El nombre de usuario ya está en uso
        }

        User user = new User();
        user.setId(Long.parseLong(UUID.randomUUID().toString())); // Generar ID aleatorio
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Codificar la contraseña
        repository.save(user);
        return true;
    }

    // Método para verificar la contraseña de un usuario
    public boolean verifyPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

}
