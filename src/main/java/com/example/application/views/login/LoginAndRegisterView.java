package com.example.application.views.login;

import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.application.services.UserService;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")

public class LoginAndRegisterView extends VerticalLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginAndRegisterView() {
        // Formulario de login
        FormLayout loginForm = new FormLayout();
        TextField loginUsername = new TextField("Username");
        PasswordField loginPassword = new PasswordField("Password");
        Button loginButton = new Button("Login");

        loginButton.addClickListener(event -> {
            // Lógica de autenticación
            // Llamar al servicio de autenticación
        });

        loginForm.add(loginUsername, loginPassword, loginButton);

        // Formulario de registro
        FormLayout registerForm = new FormLayout();
        TextField registerUsername = new TextField("Username");
        PasswordField registerPassword = new PasswordField("Password");
        Button registerButton = new Button("Register");

        registerButton.addClickListener(event -> {
            // Lógica de registro
            String username = registerUsername.getValue();
            String password = passwordEncoder.encode(registerPassword.getValue());
            userService.registerNewUser(username, password);
        });

        registerForm.add(registerUsername, registerPassword, registerButton);

        // Agregar ambos formularios al layout principal
        add(loginForm, registerForm);
    }
}
/*
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;

    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Gift Planner");
        i18n.getHeader().setDescription("Login using user/user or admin/admin");
        i18n.setAdditionalInformation(null);
        setI18n(i18n);

        setForgotPasswordButtonVisible(false);
        setOpened(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            setOpened(false);
            event.forwardTo("");
        }

        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}*/
