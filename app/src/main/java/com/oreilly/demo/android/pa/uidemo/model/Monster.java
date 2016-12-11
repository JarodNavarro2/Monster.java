package com.oreilly.demo.android.pa.uidemo.model;


/** A dot: the coordinates, color and size. */
public final class Monster {
    private float x, y;
    private int color;
    private final int diameter;
    private int numLives;
    private int ID;
    private long startTime;

    /**
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @param color the color.
     * @param diameter dot diameter.
     */
    public Monster(final float x, final float y, final int color, final int diameter,
                   final int numLives, final int ID, final long startTime) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.diameter = diameter;
        this.numLives = numLives;
        this.ID = ID;
        this.startTime = startTime;
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

    /** @return the ID of the monster. */
    public int getID() { return ID; }

    /** @return the start time of monster creation.*/
    public long getStartTime() { return startTime; }

    public void setX(float x) { this.x = x; }

    // setter method for y-coordinate
    public void setY(float y) { this.y = y; }

    // setter method for color
    public void setColor(int color) { this.color = color; }

    //setter method for number of lives
    public void setNumLives(int numLives) { this.numLives = numLives; }

    //setter method for the monster ID
    public void setID(int ID) { this.ID = ID; }

    //setter method for the start time
    public void setStartTime(long startTime) { this.startTime = startTime; }


}