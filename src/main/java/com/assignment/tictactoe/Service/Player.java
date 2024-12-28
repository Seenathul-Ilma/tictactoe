package com.assignment.tictactoe.Service;

public abstract class Player {
    protected BoardImpl boardImpl;

    public Player(BoardImpl boardImpl) {
        this.boardImpl = boardImpl;
    }

    public abstract void move(int row, int col);
}
