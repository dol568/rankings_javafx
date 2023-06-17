module com.example.testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.logging;
    requires org.apache.poi.poi;

    opens org.mwo.agh.edu to javafx.fxml;
    exports org.mwo.agh.edu;
    exports org.mwo.agh.edu.models;
    opens org.mwo.agh.edu.models to javafx.fxml;
    exports org.mwo.agh.edu.report;
    opens org.mwo.agh.edu.report to javafx.fxml;
    exports org.mwo.agh.edu.converters;
    opens org.mwo.agh.edu.converters to javafx.fxml;
    exports org.mwo.agh.edu.pages;
    opens org.mwo.agh.edu.pages to javafx.fxml;
    exports org.mwo.agh.edu.printers;
    opens org.mwo.agh.edu.printers to javafx.fxml;
}