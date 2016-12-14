package com.oreilly.demo.android.pa.uidemo.model;

/**
 * Sets board with n rows and m columns
 *
 * Created by Grazyna on 12/3/2016.
 */

public class Board {
    private int n;
    private int m;

    public Board(int n, int m){
        this.n = n;
        this.m = m;
    }

    public int getN(){
        return n;
    }

    public int getM(){
        return m;
    }
}
