package com.assignment.tictactoe.Service;

import javafx.scene.control.Alert;

public class BoardImpl implements Board {
    private Piece[][] pieces = new Piece[3][3];

    public BoardImpl() {
        initializeBoard();
    }

    public Piece[][] getPieces(){
        return pieces;
    }

    @Override
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        if(pieces[row][col] != Piece.EMPTY){
            new Alert(Alert.AlertType.ERROR, "Invalid move...!").show();
            System.out.println("Invalid Move");
            return false;
        }
        return true;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }

    @Override
    public Winner checkWinner() {
        for(int i = 0; i < 3; i++){
            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]){
                return new Winner(pieces[0][i], 0, i, 1, i, 2, i);
            }else if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]){
                return new Winner(pieces[i][0], i, 0, i, 1, i, 2);
            }
        }

        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]){
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2);
        } else if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]){
            return new Winner(pieces[0][2], 0, 2, 1, 1, 2, 0);
        }
        return null;
    }

    @Override
    public void printBoard() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < pieces.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < pieces[i].length; j++) {
                // Display X, O, or an empty space depending on the piece at each position
                char pieceChar = (pieces[i][j] == Piece.EMPTY) ? ' ' : (pieces[i][j] == Piece.X) ? 'X' : 'O';
                System.out.print(pieceChar + " | ");
            }
            System.out.println();
            System.out.println("+---+---+---+");
        }
        System.out.println();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
