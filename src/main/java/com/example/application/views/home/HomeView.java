package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        VerticalLayout content = getContent();
        content.setWidth("100%");
        content.getStyle().set("flex-grow", "1");

        H1 h1 = new H1("Crea tu lista de regalos");
        H3 h3 = new H3("Tu aliado perfecto para organizar y gestionar tus listas de regalos");
        Hr hr = new Hr();

        H2 h2 = new H2("¿Qué es Gift Planner?");
        Paragraph textLarge = new Paragraph(
                "Gift Planner es tu herramienta definitiva para planificar, organizar y gestionar todos tus regalos en un solo lugar. Olvídate del estrés y la confusión de última hora. Con Gift Planner, regalar se convierte en una experiencia divertida y libre de preocupaciones.");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        Hr hr2 = new Hr();
        H2 h22 = new H2("¿Cómo Funciona?");
        Paragraph textLarge2 = new Paragraph("1. Regístrate Gratis: Crea tu cuenta en Gift Planner de manera rápida y sencilla.");
        textLarge2.setWidth("100%");
        textLarge2.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        Paragraph textLarge3 = new Paragraph("2. Crea tu Lista de Regalos: Añade ideas y sugerencias a tus listas de regalos personalizadas.");
        textLarge3.setWidth("100%");
        textLarge3.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        H2 h23 = new H2("Únete a Gift Planner Hoy");
        Paragraph textLarge4 = new Paragraph("Empieza a disfrutar de una experiencia de planificación de regalos sin igual. ¡Registrarse es gratis y toma solo unos minutos!");
        textLarge4.setWidth("100%");
        textLarge4.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        Button buttonPrimary = new Button("Regístrate Ahora", event -> {
            // Navegar a la vista de registro al hacer clic en el botón
            getUI().ifPresent(ui -> ui.navigate("register"));
        });
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        content.add(h1, h3, hr, h2, textLarge, hr2, h22, textLarge2, textLarge3, h23, textLarge4, buttonPrimary);
    }
}
