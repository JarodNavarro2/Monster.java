package com.oreilly.demo.android.pa.uidemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.oreilly.demo.android.pa.uidemo.model.Board;
import com.oreilly.demo.android.pa.uidemo.model.Monster;
import com.oreilly.demo.android.pa.uidemo.model.Monsters;

import static com.oreilly.demo.android.pa.uidemo.TouchMe.dpHeight;
import static com.oreilly.demo.android.pa.uidemo.TouchMe.dpWidth;


/**
 * I see spots!
 *
 * @author <a href="mailto:android@callmeike.net">Blake Meike</a>
 */
public class MonsterView extends View {

    private volatile Monsters monsters;
    private volatile Board board;

    /**
     * @param context the rest of the application
     */
    public MonsterView(final Context context) {
        super(context);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     */
    public MonsterView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MonsterView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
    }

    /**
     * @param monsters
     */
    public void setDots(final Monsters monsters) { this.monsters = monsters; }

    public void setBoard(final Board board) { this.board = board; }

    public Board getBoard(final Board board) { return board;}

    /**
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */

    @Override protected void onDraw(final Canvas canvas) {
        //draw board
        float width = (float)canvas.getWidth() / board.getN();
        float height = (float)canvas.getHeight() / board.getM();
        float size = Math.min(width, height);
        //to center the board
        float offsetX = ((float)canvas.getWidth() - board.getN() * size) / 2;
        float offsetY = ((float)canvas.getHeight() - board.getM() * size) / 2;

        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        for(int i = 0; i < board.getN(); ++i){
            for(int j = 0; j < board.getM(); ++j){
                float left = i * size;
                float top = j * size;
                float right = (i + 1) * size;
                float bottom = (j + 1) * size;
                canvas.drawRect(left + offsetX, top + offsetY, right + offsetX, bottom + offsetY, paint);
            }
        }

        paint.setStyle(Style.STROKE);
        paint.setColor(hasFocus() ? Color.BLUE : Color.GRAY);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() -1, paint);

        if (monsters == null){
            return;
        }

        paint.setStyle(Style.FILL);
        for( Monster monster : monsters.getMonsters() ){
            paint.setColor(monster.getColor());
            canvas.drawCircle(monster.getX(), monster.getY(), monster.getDiameter(), paint);
        }
    }

}