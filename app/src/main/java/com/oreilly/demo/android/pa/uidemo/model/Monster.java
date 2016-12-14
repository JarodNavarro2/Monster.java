package com.oreilly.demo.android.pa.uidemo.model;


import android.graphics.Color;
import android.os.Handler;
import android.provider.Settings;

/** A dot: the coordinates, color and size. */
public final class Monster implements ClockListener {
    private float x, y;
    private int color;
    private final int diameter;
    private int numLives;
    private boolean vulnerable;
    private boolean moving;
    private Monsters monsters;
    private long startTime;
    private int track_sec;
    /**
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @param color the color.
     * @param diameter dot diameter.
     */
    public Monster(final float x, final float y, final int color, final int diameter,
                   final int numLives, Monsters monsters) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.diameter = diameter;
        this.numLives = numLives;
        this.monsters = monsters;
    }

    /** @return the horizontal coordinate. */
    public float getX() { return x; }

    /** @return the vertical coordinate. */
    public float getY() { return y; }

    /** @return the color. */
    public int getColor() { return color; }

    /** @return the dot diameter. */
    public int getDiameter() { return diameter; }

    /** @return the number of lives. */
    public int getNumLives() { return numLives; }

    public void setX(float x) { this.x = x; }

    // setter method for y-coordinate
    public void setY(float y) { this.y = y; }

    // setter method for color
    public void setColor(int color) { this.color = color; }

    //setter method for number of lives
    public void setNumLives(int numLives) { this.numLives = numLives; }


    /**
     * Handles the touch events for the monster
     */
    public void handleTouchEvent() { //TODO: Set vulnerability based on color NEEDED
        if(getColor() == Color.GREEN){ vulnerable = true;}
        else {vulnerable = false;}

        if (vulnerable) { //vulnerable or not
            numLives--;

        }
        if (numLives == 0) {
            monsters.removeMonster(this);
        }
    }

    public void handleClockEvent() { //TODO: after random said ticks, if monster isnt moving, move it.
                                     //TODO: If it is moving, set it to false. then repeat NEEDED
        if (!moving) {
            moving = true;

        }

        System.out.println("tick");
    }

    public boolean update (int seconds)
    {
        if (seconds >= track_sec)
        {
            vulnerable = true;
            setColor(Color.GREEN);
            return true;
        }
        return false;
    }

}