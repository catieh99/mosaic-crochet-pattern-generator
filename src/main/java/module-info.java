module com.mosaicpattern {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires lombok;
    requires transitive java.desktop;
    requires transitive javafx.graphics;

    exports com.csh.mosaicpattern;
    exports com.csh.mosaicpattern.backend;
    exports com.csh.mosaicpattern.util;
}
