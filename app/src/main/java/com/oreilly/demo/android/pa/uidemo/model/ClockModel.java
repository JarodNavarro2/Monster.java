package com.oreilly.demo.android.pa.uidemo.model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Grazyna on 12/13/2016.
 */

public class ClockModel {
    private ArrayList<ClockListener> listeners = new ArrayList<>();
    private Timer recurring;

    public ClockModel() {
        recurring = new Timer();

        recurring.schedule(new TimerTask(){
            @Override
            public void run(){
                for (ClockListener listener: listeners) {
                    listener.handleClockEvent();
                }
            }
        }, 1000, 1000);
    }

    public void addListener(ClockListener listener){
        listeners.add(listener);
    }

    public void clear() {
        listeners.clear();
    }
}
