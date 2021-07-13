package ticTacToe;

public class round {

    int turn;               //current turn number
    player whoseTurnIsIt;   //which player plays this turn


    //  create a new round object
    public player newRound(game thisGame) {
        thisGame.currentRound = new round();
        thisGame.currentRound.turn = 1;
        return startNewRound(thisGame); // play a round of tic tac toe
    }

    //initialize a new game
    public player startNewRound(game thisGame) {
        round thisRound = thisGame.currentRound;          // populate local variables
        player player1 = thisGame.player1;
        player player2 = thisGame.player2;
        thisRound.whoseTurnIsIt = player1;     // player 1 always goes first
        board thisBoard = new board().newGameboard();   // create new tic tac toe graphic board
        return playThisRound(thisRound, player1, player2, thisBoard);
    }

    // play the round until a winner is found, if not then declare a tie
    public player playThisRound(round thisRound, player player1, player player2, board thisBoard) {
        thisBoard.displayCurrentBoard(thisBoard.board);     // display board for the first time
        while (thisRound.turn <= 9) {            // iterate through the 9 rounds of a TicTacToe game
            thisRound = nextTurn(thisRound, player1, player2, thisBoard);
            if (thisRound.turn >= 5) {
                player winner = checkIfWinner(thisBoard, thisRound);
                if (winner != null) {
                    System.out.println(winner.name + " is the winner!!! CONGRATS!"); // announce winner
                    return winner;
                }
            }
        }
        return declareTie();    // if no player won, announce a tie
    }

    // determine which player plays next, then init a new turn of the game
    public round nextTurn(round thisRound, player player1, player player2, board thisBoard) {
        thisRound.whoseTurnIsIt = checkWhoPlaysNext(player1, player2, thisRound);
        if (thisRound.whoseTurnIsIt.equals(player1)) {
            return  playNextTurn(player1, thisBoard, thisRound);
        }
        else {
            return  playNextTurn(player2, thisBoard, thisRound);
        }
    }

    // determines which player plays next turn
    public player checkWhoPlaysNext(player player1, player player2, round thisRound) {
        if (thisRound.turn % 2 != 0) {
            return player1;
        }
        else {
            return player2;
        }
    }

    // execute a turn:  request SLOT, update BOARD, show BOARD again, update GAME TURN
    public round playNextTurn(player player, board thisBoard, round thisRound) {
        System.out.print("it's " + player.name + " turn. You play with " + player.symbol + ".\nPlease select one of " +
                "the available slots (1 to 9) as displayed above: ");
        char slot = thisBoard.requestSlot(thisBoard);       // request slot
        thisBoard.updateBoard(thisBoard.board, slot, player.symbol);      // update board
        thisBoard.displayCurrentBoard(thisBoard.board);             // display updated board
        thisRound.turn++;       // move to next turn
        return thisRound;
    }

    // return true if a player won the round
    public player checkIfWinner(board thisBoard, round thisRound) {
        player player = thisRound.whoseTurnIsIt;
        char[][] board = thisBoard.board;
        if (thisBoard.checkCombinations(player, board)) {
            return player;
        }
        return null;
    }

    // declare a tie if no player won the round
    public player declareTie() {
        System.out.println("The game was a TIE. Better luck next time!");
        return null;
    }

}

