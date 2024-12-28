module com.assignment.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.assignment.tictactoe.Service to javafx.fxml;
    opens com.assignment.tictactoe.Controller to javafx.fxml;
    opens com.assignment.tictactoe to javafx.fxml;
    exports com.assignment.tictactoe;
}