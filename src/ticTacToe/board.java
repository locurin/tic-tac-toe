package ticTacToe;

import java.util.Scanner;
import java.lang.*;

public class board {

    // display the graphic representation of the game board
    char[][] board =
            {
                    {'\n', ' ', ' ', ' ', ' ', ' ', ' ', 'G', 'A', 'M', 'E', ':', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'A', 'V', 'A', 'I', 'L', 'A', 'B', 'L', 'E', ' ', 'S', 'L', 'O', 'T', 'S', ':'},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '1', ' ', ' ', '|', ' ', ' ', '2', ' ', ' ', '|', ' ', ' ', '3', ' ', ' '},
                    {'-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-'},
                    {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '4', ' ', ' ', '|', ' ', ' ', '5', ' ', ' ', '|', ' ', ' ', '6', ' ', ' '},
                    {'-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-', '+', '-', '-', '-', '-', '-'},
                    {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '7', ' ', ' ', '|', ' ', ' ', '8', ' ', ' ', '|', ' ', ' ', '9', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', '\n'},
            };

    // generate 9 slots array
    char[] availableSlots = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    // define a scanner
    Scanner input = new Scanner(System.in);

    // define a builder
    public ticTacToe.board newGameboard() {
        return new board();
    }

    // display current board
    public void displayCurrentBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    // request to prompt a slot and return it
    public char requestSlot(board thisBoard) {
        String unparsedSlot = input.next();
        return checkIfValid(unparsedSlot, thisBoard);
    }

    // check if a slot is valid and available, then return it, if not request another slot
    public char checkIfValid(String unparsedSlot, board thisBoard) {
        if (unparsedSlot.length() > 1 || !unparsedSlot.matches("^[1-9]*$")) {   // ensure slot is only one digit
            System.out.print("Invalid entry. Please choose a valid available SLOT from 1 to 9: ");
            return requestSlot(thisBoard);
        }
        char validSlot = unparsedSlot.charAt(0);
        if (checkIfAvailable(validSlot, thisBoard)) {   // check if slot is available and return it
            return validSlot;
        }
        System.out.print(validSlot + " is not available anymore. Please choose an available SLOT: ");
        return requestSlot(thisBoard);
    }

    // return true if a valid slot is available
    public boolean checkIfAvailable(char validSlot, board thisBoard) {
        for (int counter = 0; counter < thisBoard.availableSlots.length; counter++) {
            if (validSlot == thisBoard.availableSlots[counter]) { // if slot is available, remove from array for future turns
                thisBoard.availableSlots[counter] = ' ';
                return true;
            }
        }
        return false;
    }

    // update graphic board
    public void updateBoard(char[][] board, char slot, char symbol) {
        switch (slot) {
            case '1':   // update TOP-LEFT
                board[3][2] = symbol;
                board[3][24] = ' ';
                break;
            case '2':   // update TOP-MIDDLE
                board[3][8] = symbol;
                board[3][30] = ' ';
                break;
            case '3':   // update TOP-RIGHT
                board[3][14] = symbol;
                board[3][36] = ' ';
                break;

            case '4':   // update CENTER-LEFT
                board[5][2] = symbol;
                board[5][24] = ' ';
                break;
            case '5':   // update CENTER-MIDDLE
                board[5][8] = symbol;
                board[5][30] = ' ';
                break;
            case '6':   // update CENTER-RIGHT
                board[5][14] = symbol;
                board[5][36] = ' ';
                break;
            case '7':   // update BOTTOM-LEFT
                board[7][2] = symbol;
                board[7][24] = ' ';
                break;
            case '8':   // update BOTTOM-MIDDLE
                board[7][8] = symbol;
                board[7][30] = ' ';
                break;
            case '9':   // update BOTTOM-RIGHT
                board[7][14] = symbol;
                board[7][36] = ' ';
                break;
            default:    // terminate program if updating board fails
                System.out.println("oops. Something went wrong.");
                System.exit(-1);
        }
    }

    // check board looking for a winner line, if not return false
    public boolean checkCombinations(player player, char[][] board) {
        boolean rows = checkRows(player, board);
        boolean columns = checkColumns(player, board);
        boolean diagonals = checkDiagonals(player, board);
        return rows || columns || diagonals;
    }

    // check rows of the board looking for a winner line, if not return false
    public boolean checkRows(player player, char[][] board) {
        if (board[3][2] == player.symbol && board[3][8] == player.symbol && board[3][14]  == player.symbol) {  // TOP ROW
            return true;
        }
        if (board[5][2] == player.symbol && board[5][8] == player.symbol && board[5][14]  == player.symbol) {  // CENTER ROW
            return true;
        }
        return board[7][2] == player.symbol && board[7][8] == player.symbol && board[7][14] == player.symbol; // BOTTOM ROW
    }

    // check columns of the board looking for a winner line, if not return false
    public boolean checkColumns(player player, char[][] board) {
        if (board[3][2] == player.symbol && board[5][2] == player.symbol && board[7][2]  == player.symbol) {  // LEFT COLUMN
            return true;
        }
        if (board[3][8] == player.symbol && board[5][8] == player.symbol && board[7][8]  == player.symbol) {  // MIDDLE COLUMN
            return true;
        }
        return board[3][14] == player.symbol && board[5][14] == player.symbol && board[7][14] == player.symbol; // RIGHT COLUMN
    }

    //check diagonals of the board looking for a winner line, if not return false
    public boolean checkDiagonals(player player, char[][] board) {
        if (board[3][2] == player.symbol && board[5][8] == player.symbol && board[7][14]  == player.symbol) {  // TOP-LEFT to BOTTOM RIGHT diagonal
            return true;
        }
        return board[3][14] == player.symbol && board[5][8] == player.symbol && board[7][2] == player.symbol;   // TOP-RIGHT to BOTTOM LEFT diagonal
    }

}
