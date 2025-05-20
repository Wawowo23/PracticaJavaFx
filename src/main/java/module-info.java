module org.example.fichajesjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens org.example.fichajesjavafx to javafx.fxml;
    opens modelos to javafx.base;
    exports org.example.fichajesjavafx;

}