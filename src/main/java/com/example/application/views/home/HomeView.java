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
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        H1 h1 = new H1();
        H3 h3 = new H3();
        Hr hr = new Hr();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        Hr hr2 = new Hr();
        H2 h22 = new H2();
        Paragraph textLarge2 = new Paragraph();
        Paragraph textLarge3 = new Paragraph();
        H2 h23 = new H2();
        Paragraph textLarge4 = new Paragraph();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Crea tu lista de regalos");
        h1.setWidth("max-content");
        h3.setText("Tu aliado perfecto para organizar y gestionar tus listas de regalos");
        h3.setWidth("max-content");
        h2.setText("¿Qué es Gift Planner?");
        h2.setWidth("max-content");
        textLarge.setText(
                "Gift Planner es tu herramienta definitiva para planificar, organizar y gestionar todos tus regalos en un solo lugar. Olvídate del estrés y la confusión de última hora. Con Gift Planner, regalar se convierte en una experiencia divertida y libre de preocupaciones.");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        h22.setText("¿Cómo Funciona?");
        h22.setWidth("max-content");
        textLarge2.setText("1. Regístrate Gratis: Crea tu cuenta en Gift Planner de manera rápida y sencilla.");
        textLarge2.setWidth("100%");
        textLarge2.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textLarge3.setText(
                "2. Crea tu Lista de Regalos: Añade ideas y sugerencias a tus listas de regalos personalizadas.");
        textLarge3.setWidth("100%");
        textLarge3.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        h23.setText("Únete a Gift Planner Hoy");
        h23.setWidth("max-content");
        textLarge4.setText(
                "Empieza a disfrutar de una experiencia de planificación de regalos sin igual. ¡Registrarse es gratis y toma solo unos minutos!");
        textLarge4.setWidth("100%");
        textLarge4.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        buttonPrimary.setText("Regístrate Ahora");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h1);
        getContent().add(h3);
        getContent().add(hr);
        getContent().add(h2);
        getContent().add(textLarge);
        getContent().add(hr2);
        getContent().add(h22);
        getContent().add(textLarge2);
        getContent().add(textLarge3);
        getContent().add(h23);
        getContent().add(textLarge4);
        getContent().add(buttonPrimary);
    }
}
