package com.assignment.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));
        Scene scene = new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);

        Image image = new Image(getClass().getResourceAsStream("/Assets/gameLogo.png"));
        primaryStage.getIcons().add(image);
        primaryStage.show();

    }
}
