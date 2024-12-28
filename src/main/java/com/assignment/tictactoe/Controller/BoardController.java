package com.assignment.tictactoe.Controller;

import com.assignment.tictactoe.Service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class BoardController implements BoardUI {
    @FXML private GridPane MainGrid;
    @FXML private Label resultLbl;
    @FXML private Label turnLbl;
    @FXML private AnchorPane pageTwo;

    BoardImpl boardImpl;
    AiPlayer aiPlayer;
    HumanPlayer humanPlayer;

    public BoardController() {
        boardImpl = new BoardImpl();
        humanPlayer = new HumanPlayer(boardImpl);
        aiPlayer = new AiPlayer(boardImpl);
    }

    @Override
    public void update(int row, int col, Piece piece) {
        String buttonId = "id" + row + col;
        for (Node node : MainGrid.getChildren()) {
            if (node instanceof Button button && buttonId.equals(node.getId())) {
                button.setText(piece == Piece.X ? "X" : piece == Piece.O ? "O" : "");
                break;
            }
        }
    }

    @Override
    public void NotifyWinner(Piece winningPiece) {
        boardImpl.printBoard();
        String message = winningPiece == Piece.X ? "Congratulations! You won." : "Better luck next time! AI Won.";
        resultLbl.setStyle(resultLbl.getStyle() + ";-fx-text-fill: black;");
        resultLbl.setText("The winning piece is '"+ winningPiece + "' !");
        MainGrid.setDisable(true);
        System.out.println(message);
        showAlert(message);
    }

    @FXML
    public void clickGridBtnOnAction(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        int row = Integer.parseInt(button.getId().split("")[2]);
        int col = Integer.parseInt(button.getId().split("")[3]);

        if (boardImpl.isLegalMove(row, col)) {
            humanPlayer.move(row, col);
            boardImpl.printBoard();
            turnLbl.setText("AI");
            System.out.println("Current Player: " + turnLbl.getText());
            aiPlayer.findNextBestMove();
            boardImpl.printBoard();
            turnLbl.setText("Human");
            System.out.println("Current Player: " + turnLbl.getText());
            updateUi();
        }

        if(boardImpl.checkWinner() != null){
            MainGrid.setDisable(true);
            NotifyWinner(boardImpl.checkWinner().getWinningPiece());
            moveToNextPage();
        }else if(boardImpl.isBoardFull()){
            boardImpl.printBoard();
            System.out.println("No one wins! It's a Draw.");
            MainGrid.setDisable(true);
            showAlert("No one wins! It's a Draw.");
            moveToNextPage();
        }
    }

    private void moveToNextPage() throws IOException {
        pageTwo.getChildren().clear();
        pageTwo.setStyle("-fx-background-color: #ffffff");
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/PlayAgain.fxml"));
        pageTwo.getChildren().add(load);
    }

    private void updateUi() {
        for (int i=0; i < boardImpl.getPieces().length; i++) {
            for (int j = 0; j < boardImpl.getPieces()[i].length; j++) {
                update(i, j, boardImpl.getPieces()[i][j]);
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setOnCloseRequest((DialogEvent event) -> {
            boardImpl.initializeBoard();
            updateUi();
        });
        alert.showAndWait();
    }
}
