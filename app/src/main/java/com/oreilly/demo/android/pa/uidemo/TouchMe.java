package com.oreilly.demo.android.pa.uidemo;

import android.app.Activity;
import android.content.res.Resources;
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

import com.oreilly.demo.android.pa.uidemo.model.Dot;
import com.oreilly.demo.android.pa.uidemo.model.Dots;
import com.oreilly.demo.android.pa.uidemo.view.DotView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/** Android UI demo program */
public class TouchMe extends Activity {
    /** Dot diameter */
    public static final int DOT_DIAMETER = 5;//min size must be 5 or so...
    public static final int NUM_MONSTERS = 15;
    public String time = "";        // start time
    public String invul = "Green"; // present color: Green, Yelloe
    public int k = 0;              // monsters
    public int x = 0;              // lives
    public int ticks = 0;          // ticks left
    public boolean FoE = false;       // Fell off left edge, out of bounds
    //public int Ax, Ay, Bx, By, Cx, Cy, Dx, Dy, Ex, Ey, Fx, Fy, Gx, Gy, Hx, Hy;  // positioning
    //Ax =0; Ay=0; Bx = 0; By=0; Cx =0; Cy =0; Dx =0; Dy =0; Ex =0; Ey =0; Fx =0; Fy =0; Gx =0; Gy =0; Hx =0; Hy =0;
    public Object[][][][][][][] Monsters;   // Array: k, time, Color, xCoord, yCoord, x, ticks //TODO this could be the end result object
    //this could contain the monster ID  (number used to find the correct monster), xCoord, yCoord, num of lives, color (stored as 0 or 1 for yellow, green),
    // refresh rate (how often monster moves, changes state), and when the monster was originally created (labeled startTime).
    // startTime is a long converted to an integer (originally System.currentTimeMillis() for when the monster was created)
    // that is used to compare against the current Time (using System.currentTimeMillis()). if currentTime-(startTime)%(mod by the) refresh rate is 0, move the monster.

    /** Listen for taps. */
    private static final class TrackingTouchListener implements View.OnTouchListener {
        private final Dots mDots;
        private List<Integer> tracks = new ArrayList<>();
        public static final ArrayList<Integer> RectArray= new ArrayList<Integer>(); //TODO each 4 vals contains edge vals of boxes.
        TrackingTouchListener(final Dots dots) { mDots = dots; }

        @Override public boolean onTouch(final View v, final MotionEvent evt) {
            final int action = evt.getAction();
            switch (action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    final int idx1 = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.add(evt.getPointerId(idx1));
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    final int idx2 = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.remove(evt.getPointerId(idx2));
                    break;

                case MotionEvent.ACTION_MOVE:
                    final int n = evt.getHistorySize();
                    for (Integer i: tracks) {
                        final int idx = evt.findPointerIndex(i);
                        for (int j = 0; j < n; j++) {
                            addDot(
                                mDots,
                                evt.getHistoricalX(idx, j),
                                evt.getHistoricalY(idx, j),
                                evt.getHistoricalPressure(idx, j),
                                evt.getHistoricalSize(idx, j));
                        }
                    }
                    break;


                default:
                    return false;
            }

            for (final Integer i: tracks) {
                final int idx = evt.findPointerIndex(i);
                addDot(
                    mDots,
                    evt.getX(idx),
                    evt.getY(idx),
                    evt.getPressure(idx),
                    evt.getSize(idx));
            }

            return true;
        }

        private void addDot(
                final Dots dots,
                final float x,
                final float y,
                final float p,
                final float s) {
            dots.addDot(x, y, Color.CYAN, (int) ((p + 0.5) * (s + 0.5) * DOT_DIAMETER));
            Monsters
        }
    }

    private final Random rand = new Random();

    /** The application model */
    private final Dots dotModel = new Dots();

    /** The application view */
    private DotView dotView;

    /** The dot generator */
    private Timer dotGenerator;

    /** Called when the activity is first created. */
    @Override public void onCreate(final Bundle state) {
        super.onCreate(state);

        // install the view
        setContentView(R.layout.main);
        /*DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int dpHeight = (int)(displayMetrics.heightPixels / displayMetrics.density + 0.5);
        int dpWidth = (int)(displayMetrics.widthPixels / displayMetrics.density + 0.5);
        int n=9;
        int m=1;
        System.out.println("(dpHeight*dpWidth)/(n*m):"+(dpHeight*dpWidth)/(n*m)+ "\ndpHeight"+dpHeight+"\ndpWidth"+dpWidth);
        System.out.println("each square should fill: 1/"+(n*m)+" of the screen.");*/


        // find the dots view
        dotView = (DotView) findViewById(R.id.dots);
        dotView.setDots(dotModel);

        dotView.setOnCreateContextMenuListener(this);
        dotView.setOnTouchListener(new TrackingTouchListener(dotModel));

        dotModel.setDotsChangeListener((final Dots dots) -> dotView.invalidate());

        //create monsters
        runOnUiThread(() -> {
            for (int i = 0; i < NUM_MONSTERS; i++) {
                makeDot(dotModel, dotView, Color.GREEN);
                //Monsters.add(); TODO this is where we could add the monster...
            }
        });

        /*dotView.setOnKeyListener((final View v, final int keyCode, final KeyEvent event) -> {
            if (KeyEvent.ACTION_DOWN != event.getAction()) {
                return false;
            }

            int color;
            switch (keyCode) {
                case KeyEvent.KEYCODE_SPACE:
                    color = Color.MAGENTA;
                    break;
                case KeyEvent.KEYCODE_ENTER:
                    color = Color.BLUE;
                    break;
                default:
                    return false;
            }

            makeDot(dotModel, dotView, color);

            return true;
        });*/



        // wire up the controller
        /*findViewById(R.id.button1).setOnClickListener((final View v) ->
            makeDot(dotModel, dotView, Color.RED)
        );
        findViewById(R.id.button2).setOnClickListener((final View v) ->
            makeDot(dotModel, dotView, Color.GREEN)
        );*/

        final EditText tb1 = (EditText) findViewById(R.id.text1);
        final EditText tb2 = (EditText) findViewById(R.id.text2);
        dotModel.setDotsChangeListener((final Dots dots) -> {
            final Dot d = dots.getLastDot();
            tb1.setText((null == d) ? "" : String.valueOf(d.getX()));
            tb2.setText((null == d) ? "" : String.valueOf(d.getY()));
            dotView.invalidate();
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
                        moveDots(dotModel, dotView);
                        makeDot(dotModel, dotView, Color.GREEN);
                        changeColors(dotModel);
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
                dotModel.clearDots();
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
                dotModel.clearDots();
                return true;
            default:
                return false;
        }
    }

    /**
     * @param dots the dots we're drawing
     * @param view the view in which we're drawing dots
     * @param color the color of the dot
     */
    void makeDot(final Dots dots, final DotView view, final int color) { //// TODO: making of "Monsters" v1
        final int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(
            DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
            DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
            color,
            DOT_DIAMETER);
    }
    void moveDots(final Dots dots, final DotView view) {
        final int pad = (DOT_DIAMETER + 2) * 2;
        for (Dot dot : dots.getDots()) {
            dot.setX(DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)));
            dot.setY(DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)));
        }
    }

    void changeColors(final Dots dots) {
        for (Dot dot : dots.getDots()) {
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                dot.setColor(Color.YELLOW);
            } else {
                dot.setColor(Color.GREEN);
            }
        }
    }
}