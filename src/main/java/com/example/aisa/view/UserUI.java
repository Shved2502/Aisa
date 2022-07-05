package com.example.aisa.view;

import com.example.aisa.service.RecipeService;
import com.example.aisa.service.WorkService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("api/v2")
public class UserUI extends VerticalLayout {

    VerticalLayout layout;
    Button statusButton;
    Button coffeeButton;
    Button containerButton;
    ComboBox<String> coffeeBox = new ComboBox<>("Coffee");
    ComboBox<String> containerBox = new ComboBox<>("Container");

    public UserUI(RecipeService recipeService, WorkService workService) {
        layout = new VerticalLayout();

        statusButton = new Button("Get status");
        statusButton.addClickListener(click -> {
            String status = workService.getStatus();

            if (status != null) {
                statusButton.setEnabled(false);
                Notification notification = Notification.show(status);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.addDetachListener(detachEvent -> statusButton.setEnabled(true));
            }
        });

        coffeeButton = new Button("Make coffee");
        coffeeButton.addClickListener(click -> {
            statusButton.setEnabled(false);
            workService.makeCoffee(coffeeBox.getValue());
            Notification notification = Notification.show(workService.getStatus());
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> statusButton.setEnabled(true));
        });

        containerButton = new Button("Update container");
        containerButton.addClickListener(click -> {
            statusButton.setEnabled(false);
            workService.updateContainer(containerBox.getValue());
            Notification notification = Notification.show(workService.getStatus());
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> statusButton.setEnabled(true));
        });

        coffeeBox.setItems(recipeService.getRecipes());

        containerBox.setItems("Coffee", "Milk", "Water");

        HorizontalLayout statusLayout = new HorizontalLayout(statusButton);
        HorizontalLayout coffeeLayout = new HorizontalLayout(coffeeBox, coffeeButton);
        HorizontalLayout containerLayout = new HorizontalLayout(containerBox, containerButton);

        add(
                statusLayout,
                coffeeLayout,
                containerLayout
        );
    }
}
