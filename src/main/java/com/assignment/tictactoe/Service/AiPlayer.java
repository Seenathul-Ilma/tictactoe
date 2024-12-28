package com.assignment.tictactoe.Service;

public class AiPlayer extends Player {
    public AiPlayer(BoardImpl boardImpl) {
        super(boardImpl);
    }

    @Override
    public void move(int row, int col) {
        if(boardImpl.isLegalMove(row, col)) {
            boardImpl.updateMove(row, col, Piece.O);
            System.out.println("AI has moved to row " + row + ", col " + col);
        }
    }

    public void findNextBestMove(){
        // Initialize variables to keep track of the best value for the AI move
        int bestValue = Integer.MIN_VALUE; // Start with the smallest possible value
        int bestRow = -1; // Initialize row for the best move
        int bestCol = -1; // Initialize column for the best move
        Piece[][] pieces = boardImpl.getPieces();

        // Loop through all cells on the board to evaluate potential moves
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // Check if the cell is empty
                if(pieces[i][j] == Piece.EMPTY) {
                    // Make a hypothetical move for the AI (Piece.O)
                    pieces[i][j] = Piece.O;
                    // Call minimax to evaluate this move's score
                    int moveValue = minimax(pieces, 0, false);
                    // Reset the cell back to empty to restore the board state
                    pieces[i][j] = Piece.EMPTY;

                    // If this move is better than previous moves, update the best move
                    if(moveValue > bestValue) {
                        bestRow = i;
                        bestCol = j;
                        bestValue = moveValue;
                    }
                }
            }
        }

        // Execute the best move found, if a valid move was identified
        if (bestRow != -1 && bestCol != -1) {
            move(bestRow, bestCol); // Execute the AI's best move
        }
    }

    private int minimax(Piece[][] pieces, int depth, boolean isMaximize) {
        // Check if the game has a winner
        Winner winner = boardImpl.checkWinner();
        if(winner != null) {
            if(winner.getWinningPiece() == Piece.O){
                return 10 - depth; // Return a positive score for AI win (early wins are better)
            } else if (winner.getWinningPiece() == Piece.X) {
                return depth - 10; // Return a negative score for player win
            }
        }

        // If the board is full and no winner, return 0 (draw)
        if (boardImpl.isBoardFull()){
            return 0;
        }

        // If it's AI's turn to maximize score
        if(isMaximize) {
            int bestValue = Integer.MIN_VALUE; // Start with the smallest value
            // Iterate over the board cells to simulate moves
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if(pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.O; // AI (Piece.O) makes a move
                        // Recursively call minimax, switching to minimizer's turn
                        bestValue = Math.max(bestValue, minimax(pieces, depth + 1, false));
                        // Reset cell back to empty to explore other moves
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue;
        }else{ // If it's the player's turn to minimize score
            int bestValue = Integer.MAX_VALUE; // Start with the largest value

            // Iterate over the board cells to simulate moves
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if(pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.X; // Player (Piece.X) makes a move
                        // Recursively call minimax, switching to maximizer's turn
                        bestValue = Math.min(bestValue, minimax(pieces, depth + 1, true));
                        // Reset cell back to empty to explore other moves
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue;
        }
    }
}
