package ticTacToe;
import java.util.Scanner;

public class player {
    String name;    //  player's username
    char symbol;    //  player's symbol (X or O)
    int roundsWon;   //  player's amount of rounds won

    // define a static scanner
    static Scanner input = new Scanner(System.in);

    // generate a new player object
    public player createPlayer(int playerNumber, player[] players) {
        player newPlayer = new player();
        newPlayer.name = newPlayerUsername(playerNumber, players);
        newPlayer.symbol = (playerNumber == 1) ? 'X' : 'O';
        newPlayer.roundsWon = 0;
        System.out.println("PLAYER " + playerNumber + " is " + newPlayer.name + ", will play with " + newPlayer.symbol + "\n");
        return newPlayer;
    }

    // create profile of two profiles for the game and assign them to the current game object
    public void setUpPlayers(game thisGame) {
        System.out.println("-----------\nTIC TAC TOE\n-----------\nWelcome players! Please introduce yourselves\n");
        player[] players = new player[2];
        players[0] = new player().createPlayer(1, players);
        players[1] = new player().createPlayer(2, players);
        thisGame.assignPlayers(players);
    }

    // request player's username
    public java.lang.String newPlayerUsername(int playerNumber, player[] players) {
        System.out.print("Insert name of PLAYER " + playerNumber + ": ");
        String username = input.nextLine().toUpperCase();
        return checkUsername(username, playerNumber, players);
    }

    // check if username is correct. If not, request another
    public String checkUsername(String username, int playerNumber, player[] players) {
        if (username.isBlank() || username.length() > 25 || !username.matches("^[a-zA-Z0-9 ]*$")) {
            System.out.println("Invalid username, please try again. Only use letters, numbers and whitespaces. Max length: 25 characters.");
            return newPlayerUsername(playerNumber, players);
        }
        // ensure PLAYER 2 username is different from PLAYER 1's
        else if (playerNumber == 2 && username.equals(players[0].name)) {
            System.out.println("PLAYER 2 username can not be the same as PLAYER 1. Please try another.");
            return newPlayerUsername(playerNumber, players);
        }
        else {
            return username;
        }
    }

}
