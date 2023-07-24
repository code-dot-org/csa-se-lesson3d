module com.codedotorg {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens com.codedotorg to javafx.fxml;
    exports com.codedotorg;
}
