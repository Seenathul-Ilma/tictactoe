package com.assignment.tictactoe.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainPageController {

    @FXML
    private AnchorPane pageOne;

    @FXML
    void playOnAction(ActionEvent event) throws IOException {
        pageOne.getChildren().clear();
        pageOne.setStyle("-fx-background-color: #ffffff");
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/BoardPage.fxml"));
        pageOne.getChildren().add(load);
        System.out.println("Human will place the move first!");
    }
}
