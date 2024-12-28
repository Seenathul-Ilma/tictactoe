package com.assignment.tictactoe.Service;

public class HumanPlayer extends Player {
    public HumanPlayer(BoardImpl boardImpl) {
        super(boardImpl);
    }

    @Override
    public void move(int row, int col) {
        if(boardImpl.isLegalMove(row, col)) {
            boardImpl.updateMove(row, col, Piece.X);
        }
    }
}
