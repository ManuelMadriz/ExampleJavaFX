module org.example.examplejfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.swing;
    requires javafx.media;


    opens org.example.examplejfx to javafx.fxml;
    exports org.example.examplejfx;
}