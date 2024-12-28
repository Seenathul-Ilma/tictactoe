package com.assignment.tictactoe.Service;

public class Winner {
    private Piece winningPiece;
    private int row1, row2, row3, col1, col2, col3;

    public Winner(Piece winningPiece, int row1, int row2, int row3, int col1, int col2, int col3) {
        this.winningPiece = winningPiece;
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }

    public Piece getWinningPiece() {
        return winningPiece;
    }
}
