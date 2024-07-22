package org.vaadin.example;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_USER")
@Route("")
public class MainView extends VerticalLayout implements AfterNavigationObserver {

    public MainView() {
        // MainView constructor code
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        UI.getCurrent().navigate(DashboardView.class);
    }
}