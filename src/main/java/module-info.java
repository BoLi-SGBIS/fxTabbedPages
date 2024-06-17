module com.example.fxtabbedpages {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.fxtabbedpages to javafx.fxml;
    exports com.example.fxtabbedpages;
}