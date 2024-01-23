package Builders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;


public interface MenuBuilder {
    MenuBuilder createMenu(String name);
    MenuBuilder addItem(MenuItem item);
    MenuBuilder addItem(String name, EventHandler<ActionEvent> handler);
    MenuBuilder addItem(String name, EventHandler<ActionEvent> handler, String combination);
    Menu getMenu();
}
