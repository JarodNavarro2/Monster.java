package com.oreilly.demo.android.pa.uidemo;

import com.oreilly.demo.android.pa.uidemo.model.Monster;

/**
 * Created by kyleerchinger on 12/11/16.
 */

// so this will/ may get more complex once monster is created.

public class Board {
    private Monster[][] theBoard = new Monster[4][4];
    Monster monster;
    Monster monster1;
    Monster monster2;

    Board() { // places no monster in each board
        clear();
    }

    //places Monster in board
    public void placeMonster(int x, int y, Monster monster1) {
        if (theBoard[x][y] == null)
            theBoard[x][y] = monster1;
    }

    public void MonsterMovement(){
    }

    public void populateBoard(){
        placeMonster(0, 1, monster1);
        placeMonster(2, 3, monster2);
    }

    public void Attack(int x, int y, Monster monsterattacked){
        // write a statement to find monster id, and then decremennt lives for that monster here
        monsterattacked.setNumLives(monsterattacked.getNumLives() - 1);
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
