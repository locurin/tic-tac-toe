package ticTacToe;

import java.util.Scanner;

//game's main class
public class game {
    round currentRound;   //  contains info of current round
    player player1;     //  contains info about player 1
    player player2;     //  contains info about player 2
    int roundsPlayed;    //  amount of games played between player 1 and 2

    // define a scanner
    Scanner input = new Scanner(System.in);

    // main function
    public static void main(String[] args) {

        // create a new object for this game
        game thisGame = new game();

        // create players
        new player().setUpPlayers(thisGame);

        // play a round of the game
        thisGame.play(thisGame);
    }

    //  assign new created players to ticTacToe object
    public void assignPlayers(player[] players) {
        player1 = players[0];
        player2 = players[1];
    }

    // play a round of ticTacToe, display game info at the end and request a new game
    public void play(game thisGame) {
        player winner = new round().newRound(thisGame);
        displayGameStats(winner);
        requestNewGame(thisGame);
    }

    // update game stats and display it at the end of a round
    public void displayGameStats(player winner) {
        updateRoundCounter();
        if (winner != null) {
            updateWinnerCounter(winner); //if round wasn't a tie, update rounds won counter
        }
        System.out.println("\nGames played so far: " + roundsPlayed + "\nGames won by " + player1.name + ": " +
                player1.roundsWon + "\nGames won by " + player2.name + ": " + player2.roundsWon + "\n");

    }

    // updates roundsWon fields in the winner's player object at the end of a round
    public void updateWinnerCounter(player winner) {
        if (winner.equals(player1)) {
            player1.roundsWon++;
        }
        else if (winner.equals(player2)) {
            player2.roundsWon++;
        }
    }

    //   updates gamesPlayed counter for the current game
    public void updateRoundCounter() {
        roundsPlayed++;
    }

    //  ask players if the wish to play another round, start over with new names or stop playing
    public void requestNewGame(game thisGame) {
        System.out.print("Do you wish to keep playing?\n1 - Play again with same names\n2 - Play again with different names\n"  +
                "3 - Exit game\nSelect one of the above options (1, 2 or 3) to continue: ");
        int option = getOption();
        switch (option) {
            case 1:
                newRoundWithSamePlayers(thisGame);
                break;
            case 2:
                newGameWithDifferentPlayers();
                break;
            case 3:
                terminateGame();
        }
    }

    //  get players input about next game and check if it is valid
    public int getOption() {
        String unparsedOption = input.next();
        return checkIfOptionIsValid(unparsedOption);
    }

    //  validate selected option. If valid return it, else request another
    public int checkIfOptionIsValid(String unparsedOption) {
        if (unparsedOption.length() > 1 || !unparsedOption.matches("^[1-3]*$")) {   // ensure slot is only one digit
            System.out.print("Invalid entry. Please choose a valid OPTION from 1 to 3: ");
            return getOption();
        }
        int validOption = Integer.parseInt(unparsedOption);
        if (validOption < 1 || validOption > 3 ) {
            System.out.print("Invalid entry. Please choose a valid OPTION from 1 to 3: ");
            return getOption();
        }
        return validOption;
    }

    //  play again with same names but inverted symbols
    public void newRoundWithSamePlayers(game game) {
        System.out.println("Starting new game with same players...");
        castPlayers();
        castSymbols(player1);
        castSymbols(player2);
        game.play(game);
    }

    //  cast players
    public void castPlayers() {
        player tmp = player1;
        player1 = player2;
        player2 = tmp;
    }

    //  cast a player's symbol
    public void castSymbols(player player) {
        if (player.symbol == 'X') {
            player.symbol = 'O';
        }
        else {
            player.symbol = 'X';
        }
    }

    //  restarts the game and stats to play with different names
    public void newGameWithDifferentPlayers() {
        System.out.println("Starting new game with different players...");
        main(null);
    }

    //  end game
    public void terminateGame() {
        System.out.println("Thank you for playing TIC TAC TOE!");
        System.exit(0);
    }

}

