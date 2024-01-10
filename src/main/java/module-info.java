module org.example.flora_kutuphane {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.jfoenix;

    opens org.example.flora_kutuphane to javafx.fxml;
    opens Entities.Concrete to javafx.base;

    exports Entities.Concrete;
    exports org.example.flora_kutuphane;
}