package com.example.application.views.register;

import com.example.application.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("register")
public class RegisterView extends VerticalLayout {

    private final UserService userService;

    @Autowired
    public RegisterView(UserService userService) {
        this.userService = userService;

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
            if (userService.getByUsername(username).isPresent()) {
                Notification.show("Username already exists.");
                return;
            }

            // Crear y guardar el nuevo usuario
            userService.registerUser(username, name, surname, password, email, phone);
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
