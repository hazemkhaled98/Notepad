module Notepad {
    requires javafx.controls;
    requires javafx.fxml;


    opens App to javafx.fxml;
    exports App;
}