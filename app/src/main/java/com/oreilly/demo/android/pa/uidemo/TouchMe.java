package com.oreilly.demo.android.pa.uidemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import com.oreilly.demo.android.pa.uidemo.model.Monster;
import com.oreilly.demo.android.pa.uidemo.model.Monsters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/** Android UI demo program */
public class TouchMe extends Activity {
    private Board board = null;
    private int[] idList = { R.id.cell11, R.id.cell12, R.id.cell13, R.id.cell14, R.id.cell21,
            R.id.cell22, R.id.cell23,R.id.cell24, R.id.cell31, R.id.cell32, R.id.cell33, R.id.cell34, R.id.cell41, R.id.cell42, R.id.cell43, R.id.cell44 };
    private int x = 0, y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        board = new Board();
        board.populateBoard(); // populates the board with Monsters

        printBoard();
    }

    public void printBoard() {
        Monster[][] monsters = board.getBoard();
        int k = 0;
        for(int i = 0; i < monsters.length; ++i){
            for(int j = 0; j < monsters[i].length; ++j){
                TextView textView = (TextView)findViewById(idList[k]);
                if(monsters[i][j] != null){
                    textView.setText("*");
                }
                else {
                    textView.setText(null);
                }
                k++;
            }
        }
    }

    public void onClick(View v) {
        //Get the id of the clicked object and assign it to a Textview variable
        TextView cell = (TextView)v;
        //Check the content and make sure the cell is empty and that the game isn't over
        String content = (String) cell.getText();
        if (content != null) {
            //Find the X Y location values of the particular cell that was clicked
            switch (cell.getId()) {
                case R.id.cell11:
                    x = 0;
                    y = 0;
                    break;
                case R.id.cell12:
                    x = 0;
                    y = 1;
                    break;
                case R.id.cell13:
                    x = 0;
                    y = 2;
                    break;
                case R.id.cell14:
                    x = 0;
                    y = 3;
                    break;
                case R.id.cell21:
                    x = 1;
                    y = 0;
                    break;
                case R.id.cell22:
                    x = 1;
                    y = 1;
                    break;
                case R.id.cell23:
                    x = 1;
                    y = 2;
                    break;
                case R.id.cell24:
                    x = 1;
                    y = 3;
                    break;
                case R.id.cell31:
                    x = 2;
                    y = 0;
                    break;
                case R.id.cell32:
                    x = 2;
                    y = 1;
                    break;
                case R.id.cell33:
                    x = 2;
                    y = 2;
                    break;
                case R.id.cell34:
                    x = 2;
                    y = 3;
                    break;
                case R.id.cell41:
                    x = 3;
                    y = 0;
                    break;
                case R.id.cell42:
                    x = 3;
                    y = 1;
                    break;
                case R.id.cell43:
                    x = 3;
                    y = 2;
                    break;
                case R.id.cell44:
                    x = 3;
                    y = 3;
                    break;
            }

            board.Attack(x,y);
        }

    }

    private void clear() {
        TextView cell;
        //For each cell clear the text with an empty string
        for (int item : idList) {
            cell = (TextView) findViewById(item);
            cell.setText(null);
        }
        board.clear();
    }
}