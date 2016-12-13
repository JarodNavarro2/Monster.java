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
import android.widget.EditText;

import com.oreilly.demo.android.pa.uidemo.view.MonsterView;
import com.oreilly.demo.android.pa.uidemo.model.Monster;
import com.oreilly.demo.android.pa.uidemo.model.Monsters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/** Android UI demo program */
public class TouchMe extends Activity {
    /** Dot diameter */
    public static final int DOT_DIAMETER = 30;//min size must be 5 or so...

    public static Integer dpWidth =100;
    public static Integer dpHeight=100;


    /** Listen for taps. */
    private static final class TrackingTouchListener implements View.OnTouchListener {
        private static MonsterView monsterView;
        private final Monsters mMonsters;
        private List<Integer> tracks = new ArrayList<>();
        TrackingTouchListener(final Monsters monsters) { mMonsters = monsters; }

        @Override public boolean onTouch(final View v, final MotionEvent evt) {
            int action = evt.getAction();
             float touchX = evt.getX();
             float touchY = evt.getY();
             //getRowColumnIndex()

            switch(action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    final int idx1 = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                            >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.add(evt.getPointerId(idx1));
                    break;

            //Android graphics and touch events, also getX,getY for touched area     TODO <------------
            /*switch (action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    //TODO this is where we could define the monster being clicked...
                    for (int i = 0; i < Monsters.length; i++) {
                        if ((((touchX + 10 >= Monsters[i][2]) && (Monsters[i][2] >= (touchX - 10)))
                                && ((touchY + 10 >= Monsters[i][3])
                                && (Monsters[i][3] >= (touchY - 10))) && Monsters[i][5] == 2)) {
                            Monsters[i][4]--;
                            System.out.println("HERE");
                            /*TODO get bounds of the grid to determine the distance that the point
                            is "good" which means that the click event is within a grid square
                            we need to see how far away the monster is from the top, left, right,
                            and bottom of the square. that value will replace the constant 10*/
                       /* }
                        if (Monsters[i][4] == 0) {
                            System.out.println("POINTER HIT");
                            //TODO no more lives left--monster should not appear
                        }
                    }

*/
                default:
                    return false;
            }
            return true;
        }
    }

    private final Random rand = new Random();

    /** The application model */
    private final Monsters monsterModel = new Monsters();

    /** The application view */
    private MonsterView monsterView;

    /** The dot generator */
    private Timer dotGenerator;

    /** Called when the activity is first created. */
    @Override public void onCreate(final Bundle state) {
        super.onCreate(state);

        // install the view

        monsterView = (MonsterView) findViewById(R.id.monsters);
        monsterView.setDots(monsterModel);

        monsterView.setOnCreateContextMenuListener(this);
        monsterView.setOnTouchListener(new TrackingTouchListener(monsterModel));

        monsterModel.setMonstersChangeListener((final Monsters monsters) -> monsterView.invalidate());

        runOnUiThread(() -> {
            for (int i = 0; i < 3; i++) {
                makeDot(monsterModel, monsterView, Color.GREEN);
            }
        });

        // wire up the controller
        /*findViewById(R.id.button1).setOnClickListener((final View v) ->
            makeDot(dotModel, dotView, Color.RED)
        );
        findViewById(R.id.button2).setOnClickListener((final View v) ->
            makeDot(dotModel, dotView, Color.GREEN)
        );*/

        //final EditText tb1 = (EditText) findViewById(R.id.text1);
        final EditText tb2 = (EditText) findViewById(R.id.text2);
        monsterModel.setMonstersChangeListener((final Monsters monsters) -> {
            final Monster d = monsters.getLastMonster();
            //tb1.setText((null == d) ? "" : String.valueOf(d.getX()));
            tb2.setText((null == d) ? "" : String.valueOf(d.getY()));
            monsterView.invalidate();
        });
    }

    @Override public void onResume() {
        super.onResume();
        if (dotGenerator == null) {
            dotGenerator = new Timer();
            // generate new dots, one every two seconds
            dotGenerator.schedule(new TimerTask() {
                @Override
                public void run() {
                    // must invoke makeDot on the UI thread to avoid
                    // ConcurrentModificationException on list of dots
                    runOnUiThread(() -> {
                        moveDots(monsterModel, monsterView);
                        //makeDot(monsterModel, monsterView, Color.GREEN);
                        changeColors(monsterModel);
                    });
                }
            }, /*initial delay*/ 0, /*periodic delay*/ 2000);
        }
    }

    @Override public void onPause() {
        super.onPause();
        if (dotGenerator != null) {
            dotGenerator.cancel();
            dotGenerator = null;
        }
    }

    /** Install an options menu. */
    @Override public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    /** Respond to an options menu selection. */
    @Override public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                monsterModel.clearMonsters();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Install a context menu. */
    @Override public void onCreateContextMenu(
            final ContextMenu menu,
            final View v,
            final ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, 1, Menu.NONE, "Clear").setAlphabeticShortcut('x');
    }

    /** Respond to a context menu selection. */
    @Override public boolean onContextItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                monsterModel.clearMonsters();
                return true;
            default:
                return false;
        }
    }

    /**
     * @param monsters the dots we're drawing
     * @param view the view in which we're drawing dots
     * @param color the color of the dot
     */
    void makeDot(final Monsters monsters, final MonsterView view, final int color) { //// TODO: making of "Monsters" v1
        final int pad = (DOT_DIAMETER + 2) * 2;
        monsters.addMonster(
                DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
                DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
                color,
                DOT_DIAMETER, 1, 2, 3);
    }
    void moveDots(final Monsters monsters, final MonsterView view) {
        final int pad = (DOT_DIAMETER + 2) * 2;
        for (Monster monster : monsters.getMonsters()) {
            monster.setX(DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)));
            monster.setY(DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)));
        }
    }

    void changeColors(final Monsters monsters) {
        for (Monster monster : monsters.getMonsters()) {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                monster.setColor(Color.YELLOW);
            } else {
                monster.setColor(Color.GREEN);
            }
        }
    }
}