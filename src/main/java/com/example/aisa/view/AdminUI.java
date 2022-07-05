package com.example.aisa.view;

import com.example.aisa.dto.CoffeeDTO;
import com.example.aisa.service.RecipeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("api/v2/admin")
public class AdminUI extends VerticalLayout {

    public AdminUI(RecipeService recipeService) {

        TextField name = new TextField();
        IntegerField coffeeConsumption = new IntegerField();
        IntegerField milkConsumption = new IntegerField();
        IntegerField waterConsumption = new IntegerField();
        IntegerField purityConsumption = new IntegerField();
        IntegerField timeConsumption = new IntegerField();

        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(name,"Coffee name");
        formLayout.addFormItem(coffeeConsumption,"Coffee consumption");
        formLayout.addFormItem(milkConsumption,"Milk consumption");
        formLayout.addFormItem(waterConsumption,"Water consumption");
        formLayout.addFormItem(purityConsumption,"Purity consumption");
        formLayout.addFormItem(timeConsumption,"Time consumption");

        Button submit = new Button("Submit");
        submit.addClickListener(click -> {
            submit.setEnabled(false);

            CoffeeDTO coffeeDTO = CoffeeDTO.builder()
                    .id(Long.parseLong(String.valueOf(recipeService.getRecipes().size() + 1)))
                    .name(name.getValue())
                    .coffeeConsumption(coffeeConsumption.getValue())
                    .waterConsumption(waterConsumption.getValue())
                    .milkConsumption(milkConsumption.getValue())
                    .timeConsumption(timeConsumption.getValue())
                    .purityConsumption(purityConsumption.getValue()).build();

            recipeService.addRecipe(coffeeDTO);

            Notification notification = Notification.show("Saved");
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> submit.setEnabled(true));
        });

        add(formLayout, submit);
    }
}
