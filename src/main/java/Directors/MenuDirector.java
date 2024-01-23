package Directors;

import Builders.MenuBuilder;
import Builders.MenuBuilderImp;
import javafx.scene.control.*;

import java.io.*;

import java.util.Optional;
import javafx.event.Event;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

import javafx.stage.FileChooser;


public class MenuDirector {
    private MenuBuilder builder;
    private TextArea textArea;

    private boolean isFileChanged;

    private final String SAVE = "Save";
    private final String DONT_SAVE = "Don't Save";
    private final String CANCEL = "Cancel";


    public MenuDirector(TextArea textArea){
        this.builder = new MenuBuilderImp();
        this.textArea = textArea;
        textArea.textProperty().addListener((observable, oldValue, newValue) ->  isFileChanged = true);
    }

    public Menu getFileMenu(){
        builder.createMenu("File")
        .addItem("new", e -> showNewDialog(), "Ctrl+n")
        .addItem(new SeparatorMenuItem())
        .addItem("Open", e -> showOpenDialog())
        .addItem("Save", e -> showSaveDialog())
        .addItem("Exit", e -> showExitDialog(e));
        return builder.getMenu();
    }

    public Menu getEditMenu(){
        builder.createMenu("Edit")
        .addItem("Undo", e -> textArea.undo())
        .addItem("Copy", e -> textArea.copy())
        .addItem("Cut", e -> textArea.cut())
        .addItem("Paste", e -> textArea.paste())
        .addItem("Delete", e -> textArea.clear())
        .addItem("SelectAll", e -> textArea.selectAll());
        return builder.getMenu();
    }

    public Menu getHelpMenu(){
        builder.createMenu("About")
        .addItem("About", e -> showAboutDialog());
        return builder.getMenu();
    }


    private void showNewDialog(){

        if(isFileChanged){
            Optional<ButtonType> result = showSaveBeforeExitDialog();

            String choice = result.get().getText();
            if(choice.equals(SAVE)){
                showSaveDialog();
            }
            else if(choice.equals(CANCEL))
                return;

        }

        textArea.clear();
    }

    private void showOpenDialog(){

        if(isFileChanged){
            Optional<ButtonType> result = showSaveBeforeExitDialog();

            String choice = result.get().getText();
            if(choice.equals(SAVE)){
                showSaveDialog();
            }
            else if(choice.equals(CANCEL))
                return;

        }


        FileChooser ch = new FileChooser();
        File file = ch.showOpenDialog(null);
        if(file != null){
            System.out.println("File: " + file.getName() + " is Opened");
            try (var reader = new BufferedReader(new FileReader(file))){
                StringBuilder sb = new StringBuilder();
                int c;
                while((c = reader.read()) != -1){
                    sb.append((char)c);
                }
                textArea.setText(sb.toString());
            }catch (IOException e){
                System.err.println("IOException: " + e.getMessage());
            }
        }
    }

    private void showExitDialog(Event event){
        if(isFileChanged){
            Optional<ButtonType> result = showSaveBeforeExitDialog();
            String choice = result.get().getText();
            if(choice.equals(SAVE)){
                showSaveDialog();
            }
            else if(choice.equals(CANCEL)){
                event.consume();
                return;
            }
        }

        System.exit(0);
    }


    private Optional<ButtonType> showSaveBeforeExitDialog(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Save File");
        alert.setContentText("Want to save this file ?");

        ButtonType save = new ButtonType(SAVE);
        ButtonType dontSave = new ButtonType(DONT_SAVE);
        ButtonType cancel = new ButtonType(CANCEL, ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(save, dontSave, cancel);

        return alert.showAndWait();
    }

    private void showSaveDialog() {
        FileChooser ch = new FileChooser();
        File file = ch.showSaveDialog(null);

        if(file != null){
            isFileChanged = false;
            try(var writer = new BufferedWriter(new FileWriter(file))){
                writer.write(textArea.getText());
            } catch (IOException e){
                System.err.println("IOException: " + e.getMessage());
            }
        }

    }


    private void showAboutDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "App.NotePad");
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("App.NotePad Application\ncreated by Hazem Khaled - ITI Trainee");
        alert.showAndWait();
    }
}
