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

import com.oreilly.demo.android.pa.uidemo.model.Dot;
import com.oreilly.demo.android.pa.uidemo.model.Dots;


/**
 * I see spots!
 *
 * @author <a href="mailto:android@callmeike.net">Blake Meike</a>
 */
public class DotView extends View {

    private volatile Dots dots;

    /**
     * @param context the rest of the application
     */
    public DotView(final Context context) {
        super(context);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     */
    public DotView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public DotView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
    }

    /**
     * @param dots
     */
    public void setDots(final Dots dots) { this.dots = dots; }

    /**
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override protected void onDraw(final Canvas canvas) {
        final Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(hasFocus() ? Color.BLUE : Color.GRAY);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() -1, paint);
        //
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int dpHeight = (int)(displayMetrics.heightPixels / displayMetrics.density + 0.5);
        int dpWidth = (int)(displayMetrics.widthPixels / displayMetrics.density + 0.5);
        int n=9;
        int m=1;
        //System.out.println("(dpHeight*dpWidth)/(n*m):"+(dpHeight*dpWidth)/(n*m));
        //System.out.println("each square should fill: 1/"+(n*m)+" of the screen.");
        System.out.println("center of the screen(formula): ("+(dpHeight/2)+", "+(dpWidth/2)+")");
        System.out.println("center of the screen(approx): ("+(getHeight()/2)+", "+(getWidth()/2)+")");
        paint.setColor(Color.BLUE);
        //double root = Math.sqrt(n*m);
        int ySize =getHeight()/3; //needs to be modified relative to dpHeight
        int xSize =getWidth()/3;  //needs to be modified relative to dpWidth
        System.out.println("xSize: "+xSize+"\nySize: "+ySize);
        canvas.drawRect(0, 0, xSize,ySize, paint);    //assume 9 boxes (8 surrounding 1 box)
        canvas.drawRect(xSize, 0, (xSize*2),(ySize*1), paint); //next one horizontally.
        canvas.drawRect((xSize*2),0, (xSize*3),(ySize*1), paint);//final one horizontally
        //repeat for next row.
        canvas.drawRect(0, (ySize), xSize,(ySize*2), paint);    //assume 9 boxes (8 surrounding 1 box)
        canvas.drawRect(xSize, (ySize), (xSize*2),(ySize*2), paint); //next one horizontally.
        canvas.drawRect((xSize*2),(ySize), (xSize*3),(ySize*2), paint);//final one horizontally
        //repeat for final row.
        canvas.drawRect(0, (ySize*2), xSize,(ySize*3), paint);    //assume 9 boxes (8 surrounding 1 box)
        canvas.drawRect(xSize, (ySize*2), (xSize*2),(ySize*3), paint); //next one horizontally.
        canvas.drawRect((xSize*2),(ySize*2), (xSize*3),(ySize*3), paint);//final one horizontally
        //
        //FORMULA: (getHeight()/(root))-(getHeight()/(root))  code to break down. //rect is 1/ sqrt(n*m) of the screen... if there are n*m squares.
        //
        paint.setStyle(Style.FILL);
        paint.setColor(Color.RED);
        //canvas.drawCircle((dpHeight/2),(dpWidth/2), 6, paint);
        canvas.drawCircle((getWidth()/2), (getHeight()/2), 6, paint);//center circle.
        if (null == dots) { return; }

        paint.setStyle(Style.FILL);
        for (final Dot dot : dots.getDots()) {
            paint.setColor(dot.getColor());
            canvas.drawCircle(
                dot.getX(),
                dot.getY(),
                dot.getDiameter(),
                paint);
        }
    }
}
