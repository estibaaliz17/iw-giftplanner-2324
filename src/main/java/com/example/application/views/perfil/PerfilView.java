package com.example.application.views.perfil;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;

@PageTitle("Perfil")
@Route(value = "perfil", layout = MainLayout.class)
@PermitAll
public class PerfilView extends Composite<VerticalLayout> {

    public PerfilView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        EmailField emailField = new EmailField();
        TextField textField3 = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField passwordField2 = new PasswordField();
        DatePicker datePicker = new DatePicker();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Mi perfil");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField.setLabel("Nombre");
        textField2.setLabel("Apellidos");
        emailField.setLabel("Email");
        textField3.setLabel("Teléfono móvil");
        passwordField.setLabel("Nueva contraseña");
        passwordField.setWidth("min-content");
        passwordField2.setLabel("Repite la nueva contraseña");
        passwordField2.setWidth("min-content");
        datePicker.setLabel("Cumpleaños");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Guardar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(emailField);
        formLayout2Col.add(textField3);
        formLayout2Col.add(passwordField);
        formLayout2Col.add(passwordField2);
        formLayout2Col.add(datePicker);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
    }
}
