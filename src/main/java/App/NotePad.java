package App;

import Directors.MenuDirector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NotePad extends Application {
    private BorderPane pane;


    @Override
    public void init() throws Exception{
        super.init();
        MenuBar bar = new MenuBar();
        TextArea textArea = new TextArea();
        MenuDirector director = new MenuDirector(textArea);
        Menu fileMenu = director.getFileMenu();
        Menu editMenu = director.getEditMenu();
        Menu helpMenu = director.getHelpMenu();
        bar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(textArea);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(pane, 700, 700);
        stage.setTitle("NotePad");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
