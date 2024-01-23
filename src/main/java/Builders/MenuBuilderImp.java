package Builders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;


public class MenuBuilderImp implements MenuBuilder{

    private Menu menu;


    @Override
    public MenuBuilder createMenu(String name) {
        menu = new Menu(name);
        return this;
    }

    @Override
    public MenuBuilder addItem(MenuItem item) {
        menu.getItems().add(item);
        return this;
    }

    @Override
    public MenuBuilder addItem(String name, EventHandler<ActionEvent> handler) {
        MenuItem item = new MenuItem(name);
        item.setOnAction(handler);
        menu.getItems().add(item);
        return this;
    }

    @Override
    public MenuBuilder addItem(String name, EventHandler<ActionEvent> handler, String combination) {
        MenuItem item = new Menu(name);
        item.setOnAction(handler);
        item.setAccelerator(KeyCombination.keyCombination(combination));
        menu.getItems().add(item);
        return this;
    }

    @Override
    public Menu getMenu() {
        return menu;
    }
}
