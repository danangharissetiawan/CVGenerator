module com.risset {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
//    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires jasperreports;

    opens com.risset to javafx.fxml;
    exports com.risset;
    exports com.risset.controller;
    opens com.risset.controller to javafx.fxml;

}