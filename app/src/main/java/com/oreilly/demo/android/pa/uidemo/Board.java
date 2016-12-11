package com.oreilly.demo.android.pa.uidemo;

/**
 * Created by kyleerchinger on 12/11/16.
 */

// so this will/ may get more complex once monster is created.

public class Board {
    private String[][] theBoard = new String[4][4];

        Board() { // places no monster in each board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                theBoard[i][j] = "";
            }
        }
    }

    //places Monster in board
    public void placeMonster(int x, int y, String mark) {
        if (theBoard[x][y] == "")
            theBoard[x][y] = mark;
    }


    // clears the board of monsters
    public void clear() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                theBoard[i][j] = "";
            }
        }
    }

    //returns the virtual board
    public String[][] getBoard() {
        return theBoard;
    }
}
