module org.example.fichajesjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.fichajesjavafx to javafx.fxml;
    exports org.example.fichajesjavafx;
}