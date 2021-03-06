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
    private final ClockModel clock = new ClockModel();

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
     * @param x1
     * @param v
     * @param x dot horizontal coordinate.
     * @param y dot vertical coordinate.
     * @param color dot color.
     * @param diameter dot size.
     * @param numLives the number of lives per monster
     */
    public void addMonster(float x1, float v, float x, float y, int color, int diameter,
                           int numLives) {
        Monster monster = new Monster(x, y, color, diameter, numLives, this);
        clock.addListener(monster);
        monsters.add(monster);
        notifyListener();
    }

    /** Remove all dots. */
    //might not need this method
    public void clearMonsters() {
        monsters.clear();
        clock.clear();
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
