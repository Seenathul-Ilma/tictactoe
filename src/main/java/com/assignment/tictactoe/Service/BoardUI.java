package com.assignment.tictactoe.Service;

public interface BoardUI {
    void update(int row, int col, Piece piece);
    void NotifyWinner(Piece winningPiece);
}
