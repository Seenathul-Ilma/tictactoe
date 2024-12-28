package com.assignment.tictactoe.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PlayAgainController {
    @FXML private AnchorPane PageThree;
    @FXML private Pane paneOnGridCenter;
    @FXML private Button playAgainBtn;
    @FXML private ImageView playAgainIcon;

    @FXML
    void playAgainOnAction(ActionEvent event) throws IOException {
        PageThree.getChildren().clear();
        PageThree.setStyle("-fx-background-color: #ffffff");
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/BoardPage.fxml"));
        PageThree.getChildren().add(load);
        System.out.println("Human will place the move first!");
    }

}
