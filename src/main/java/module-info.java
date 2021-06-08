module Sem2App {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.lang3;
    requires java.desktop;
    requires barbecue;
    requires CovidReferenceValues1API;
    requires ExternalModule2API;
    requires ExternalModule3API;
    requires java.logging;
    requires bcrypt;
    requires commons.math3;
    opens app.ui.gui to javafx.fxml, javafx.graphics;
    opens app.ui to javafx.fxml, javafx.graphics;
    exports app.ui;
    exports app.ui.gui;
}