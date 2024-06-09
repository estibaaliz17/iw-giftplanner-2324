package com.example.application.views;

import com.example.application.data.UserRepository;
import com.example.application.data.User;
import com.example.application.data.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterView extends VerticalLayout {

    private final UserRepository userRepository;

    public RegisterView(UserRepository userRepository) {
        this.userRepository = userRepository;

        TextField usernameField = new TextField("Username");
        TextField nameField = new TextField("Name");
        TextField surnameField = new TextField("Surname");
        PasswordField passwordField = new PasswordField("Password");
        EmailField emailField = new EmailField("Email");
        TextField phoneField = new TextField("Phone");

        Button registerButton = new Button("Register", event -> {
            String username = usernameField.getValue();
            String name = nameField.getValue();
            String surname = surnameField.getValue();
            String password = passwordField.getValue();
            String email = emailField.getValue();
            String phone = phoneField.getValue();

            if (username.isEmpty() || name.isEmpty() || surname.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Notification.show("All fields except phone are required.");
                return;
            }

            // Verificar si el usuario ya existe
            if (userRepository.findByUsername(username) != null) {
                Notification.show("Username already exists.");
                return;
            }

            // Crear y guardar el nuevo usuario
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setHashedPassword(password);
            newUser.setMail(email);
            newUser.setPhone(phone);

            userRepository.save(newUser);
            Notification.show("User registered successfully!");

            // Limpiar los campos
            usernameField.clear();
            nameField.clear();
            surnameField.clear();
            passwordField.clear();
            emailField.clear();
            phoneField.clear();
        });

        add(usernameField, nameField, surnameField, passwordField, emailField, phoneField, registerButton);
    }
}
