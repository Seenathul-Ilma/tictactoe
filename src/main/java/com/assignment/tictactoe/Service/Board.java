package com.assignment.tictactoe.Service;

public interface Board {
    void initializeBoard();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    void printBoard();
    Winner checkWinner();
}
