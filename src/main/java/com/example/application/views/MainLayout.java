package com.example.application.views;

import com.example.application.data.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.allegado.AllegadoView;
import com.example.application.views.allegados.AllegadosView;
import com.example.application.views.detallelistaregalos.DetalleListaRegalosView;
import com.example.application.views.home.HomeView;
import com.example.application.views.listaregalos.ListaRegalosView;
import com.example.application.views.listasregalos.ListasRegalosView;
import com.example.application.views.perfil.PerfilView;
import com.example.application.views.regalo.RegaloView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("Gift Planner");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if (accessChecker.hasAccess(HomeView.class)) {
            nav.addItem(new SideNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create()));

        }
        if (accessChecker.hasAccess(PerfilView.class)) {
            nav.addItem(new SideNavItem("Perfil", PerfilView.class, LineAwesomeIcon.USER.create()));

        }
        if (accessChecker.hasAccess(AllegadosView.class)) {
            nav.addItem(new SideNavItem("Allegados", AllegadosView.class, LineAwesomeIcon.USER_SOLID.create()));

        }
        if (accessChecker.hasAccess(AllegadoView.class)) {
            nav.addItem(new SideNavItem("Allegado", AllegadoView.class, LineAwesomeIcon.USER.create()));

        }
        if (accessChecker.hasAccess(ListasRegalosView.class)) {
            nav.addItem(
                    new SideNavItem("Listas Regalos", ListasRegalosView.class, LineAwesomeIcon.GIFT_SOLID.create()));

        }
        if (accessChecker.hasAccess(ListaRegalosView.class)) {
            nav.addItem(new SideNavItem("Lista Regalos", ListaRegalosView.class, LineAwesomeIcon.GIFT_SOLID.create()));

        }
        if (accessChecker.hasAccess(DetalleListaRegalosView.class)) {
            nav.addItem(new SideNavItem("Detalle Lista Regalos", DetalleListaRegalosView.class,
                    LineAwesomeIcon.GIFT_SOLID.create()));

        }
        if (accessChecker.hasAccess(RegaloView.class)) {
            nav.addItem(new SideNavItem("Regalo", RegaloView.class, LineAwesomeIcon.GIFT_SOLID.create()));

        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
