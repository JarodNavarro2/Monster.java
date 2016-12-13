package com.oreilly.demo.android.pa.uidemo.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/** A list of monsters. */
public class Monsters {
    private final LinkedList<Monster> monsters = new LinkedList<>();
    private final List<Monster> safeMonsters = Collections.unmodifiableList(monsters);
    private final Random rand = new Random();

    /** DotChangeListener. */
    public interface MonstersChangeListener {
        /** @param monsters the dots that changed. */
        void onDotsChange(Monsters monsters);
    }



    private MonstersChangeListener monstersChangeListener;

    /** @param l set the change listener. */
    public void setMonstersChangeListener(final MonstersChangeListener l) {
        monstersChangeListener = l;
    }

    /** @return the most recently added dot. */
    public Monster getLastMonster() {
        return (monsters.size() <= 0) ? null : monsters.getLast();
    }

    /** @return immutable list of dots. */
    public List<Monster> getMonsters() { return safeMonsters; }

    /**
     * @param x dot horizontal coordinate.
     * @param y dot vertical coordinate.
     * @param color dot color.
     * @param diameter dot size.
     * @param numLives the number of lives per monster
     * @param ID the id of each monster
     * @param startTime the time each monster was created
     */
    public void addMonster(float x, float y, int color, int diameter,
                           int numLives) {
        monsters.add(new Monster(x, y, color, diameter, numLives, this));
        notifyListener();
    }

    /** Remove all dots. */
    //might not need this method
    public void clearMonsters() {
        monsters.clear();
        notifyListener();
    }

    public void removeMonster(Monster m) {
        monsters.remove(m);
        notifyListener();
    }

    private void notifyListener() {
        if (null != monstersChangeListener) {
            monstersChangeListener.onDotsChange(this);
        }
    }
}
