package com.oreilly.demo.android.pa.uidemo;

import android.graphics.Color;

import com.oreilly.demo.android.pa.uidemo.model.Monster;

/**
 * Created by kyleerchinger on 12/11/16.
 */

// so this will/ may get more complex once monster is created.

public class Board {
    private Monster[][] theBoard = new Monster[4][4];

    Board() { // places no monster in each board
        clear();
    }

    //places Monster in board
    public void placeMonster(int x, int y, Monster monster) {
        if (theBoard[x][y] == null)
            theBoard[x][y] = monster;
    }

    public void MonsterMovement(){
    }

    public void populateBoard(){
        placeMonster(0, 1, new Monster(0, 1, Color.YELLOW, 6, 4, 0, 0));
        placeMonster(2, 3, new Monster(2, 3, Color.YELLOW, 6, 4, 0, 0));
        placeMonster(3, 1, new Monster(3, 1, Color.YELLOW, 6, 4, 0, 0));
    }

    public void Attack(int x, int y){
        Monster monster = theBoard[x][y];
        if (monster != null){
            monster.setNumLives(monster.getNumLives() - 1);
        }
    }

    // clears the board of monsters
    public void clear() {
        for (int i = 0; i < theBoard.length; i++) {
            for (int j = 0; j < theBoard[i].length; j++) {
                theBoard[i][j] = null;
            }
        }
    }

    //returns the virtual board
    public Monster[][] getBoard() {
        return theBoard;
    }
}
