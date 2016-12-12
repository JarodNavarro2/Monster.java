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

import com.oreilly.demo.android.pa.uidemo.model.Monster;
import com.oreilly.demo.android.pa.uidemo.model.Monsters;

//import static com.oreilly.demo.android.pa.uidemo.TouchMe.dpHeight;
//import static com.oreilly.demo.android.pa.uidemo.TouchMe.dpWidth;


/**
 * I see spots!
 *
 * @author <a href="mailto:android@callmeike.net">Blake Meike</a>
 */
public class DotView extends View {

    private volatile Monsters monsters;
    public final boolean row1=false; public final boolean row2=false;public final boolean row3=false;
    public final boolean col1=false; public final boolean col2=false;public final boolean col3=false;

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
     * @param monsters
     */
    public void setMonsters(final Monsters monsters) { this.monsters = monsters; }

    /**
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */

    @Override protected void onDraw(final Canvas canvas) {
        final Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(hasFocus() ? Color.BLUE : Color.GRAY);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() -1, paint);

        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        //dpHeight = (int)(displayMetrics.heightPixels / displayMetrics.density + 0.5);
        //dpWidth = (int)(displayMetrics.widthPixels / displayMetrics.density + 0.5);
        //dpHeight =getWidth() ;
        //dpWidth = getHeight() ;
        //int n=9;
        //int m=1;
        //System.out.println("(dpHeight*dpWidth)/(n*m):"+(dpHeight*dpWidth)/(n*m));
        //System.out.println("each square should fill: 1/"+(n*m)+" of the screen.");
        //System.out.println("center of the screen(formula): ("+(dpHeight/2)+", "+(dpWidth/2)+")");
        //System.out.println("center of the screen(approx): ("+(getHeight()/2)+", "+(getWidth()/2)+")");
        paint.setColor(Color.BLUE);
        int xSize =getWidth()/3;  //needs to be modified relative to dpWidth
        int ySize =getHeight()/3; //needs to be modified relative to dpHeight
        //System.out.println("xSize: "+xSize+"\nySize: "+ySize);
        //System.out.println("find point for box A, (x,y) format: ("+((getWidth()/2)-xSize)+", "+((getHeight()/2)-ySize)+")" );
        paint.setStyle(Style.STROKE);
        //paint.setStyle(Style.FILL_AND_STROKE);
        //paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, xSize,ySize, paint);    //assume 9 boxes (8 surrounding 1 box)
        //paint.setColor(Color.YELLOW);
        canvas.drawRect(xSize, 0, (xSize*2),(ySize*1), paint); //next one horizontally.
        //paint.setColor(Color.GREEN);
        canvas.drawRect((xSize*2),0, (xSize*3),(ySize*1), paint);//final one horizontally
        //repeat for next row.
        //paint.setColor(Color.YELLOW);
        canvas.drawRect(0, (ySize), xSize,(ySize*2), paint);    //assume 9 boxes (8 surrounding 1 box)
        //paint.setColor(Color.GREEN);
        canvas.drawRect(xSize, (ySize), (xSize*2),(ySize*2), paint); //next one horizontally.
        //paint.setColor(Color.YELLOW);
        canvas.drawRect((xSize*2),(ySize), (xSize*3),(ySize*2), paint);//final one horizontally
        //repeat for final row.
        //paint.setColor(Color.GREEN);
        canvas.drawRect(0, (ySize*2), xSize,(ySize*3), paint);    //assume 9 boxes (8 surrounding 1 box)
        //paint.setColor(Color.YELLOW);
        canvas.drawRect(xSize, (ySize*2), (xSize*2),(ySize*3), paint); //next one horizontally.
        //paint.setColor(Color.GREEN);
        canvas.drawRect((xSize*2),(ySize*2), (xSize*3),(ySize*3), paint);//final one horizontally
        //
        //FORMULA: (getHeight()/(root))-(getHeight()/(root))  code to break down. //rect is 1/ sqrt(n*m) of the screen... if there are n*m squares.
        //
        paint.setStyle(Style.FILL);
        paint.setColor(Color.RED);
        //canvas.drawCircle((dpHeight/2),(dpWidth/2), 6, paint);
        canvas.drawCircle((getWidth()/2), (getHeight()/2), 6, paint);//center circle.
        System.out.println("Seeding Coord[][] with 0's.");
        Integer [][] Coord = new Integer [9][6];//9 rows, 6 columns. each column contains start, end, mid x,y pairs.
        int i=0; int j=0; int placeX=0; int placeY=0; //placeholders for x and y.
        int midX = (getWidth()/2); int midY = (getHeight()/2);
        int RectNum=0;//number of rectangle being stored.
        System.out.println("midX: "+midX+"\nmidY: "+midY);
        for (j=0; j<9; j++) {
            System.out.println("RectNum: "+RectNum);
            //System.out.println("xSize:"+xSize+"\nySize:"+ySize);
            switch (RectNum) { //Adjusts placeY, placeX. suppposed to run in outer loop.
                case 0:  placeY=(midY)-ySize; //middle coordinates.
                    placeX =(midX)-xSize;
                    break;
                case 1:  placeX =(midX);//placeY is the same; placeX incrementing.
                    break;
                case 2:  placeX =(midX)+xSize;//placeY is the same; placeX incrementing.
                    break;
                case 3:  placeY=(midY);
                    placeX =(midX)-xSize;//reset placeX
                    break;
                case 4:  placeX =(midX);//placeY is the same; placeX incrementing.
                    break;
                case 5:  placeX =(midX)+xSize;//placeY is the same; placeX incrementing.
                    break;
                case 6:  placeY=(midY)+ySize;//final row...
                    placeX =(midX)-xSize;//reset placeX.
                    break;
                case 7:placeX =(midX);//placeY is the same; placeX incrementing.
                    break;
                case 8:placeX =(midX)+xSize;//placeY is the same; placeX incrementing.
                    break;
                default: RectNum = 0; System.out.println("Error...");
                    break;
            }
            //System.out.println("placeX:"+placeX+"\nplaceY:"+placeY);//checks how big a box is, and relative positioning.
            //stores start val for Rect....
            Coord[j][0]=placeX-(xSize/2);
            Coord[j][1]=placeY-(ySize/2);
            //print starts
            System.out.println("start pair of Rect: "+Coord[j][0]+","+Coord[j][1]);
            //stores middle coord-middle point in rect.
            Coord[j][4]=placeX;
            Coord[j][5]=placeY;
            //print middles
            //canvas.drawRect((placeX-25),(placeY-25), (placeX+25),(placeY+25), paint);//TODO a"Monster" positioning example...(Read notes just below)
            //make the monster appear based on placeX, placeY positioning. for the start points,
            // -(1/2) of the monster's width and height. For the end points, + (1/2) of monster's width, height.
            //this positions the center of the omnster in the center of the rectangle.

            System.out.println("middle pair of Rect: "+Coord[j][4]+","+Coord[j][5]);
            //stores bottom, rightmost coord pair.
            Coord[j][2]=placeX+(xSize/2);
            Coord[j][3]=placeY+(ySize/2);
            //print ends
            System.out.println("end pair of Rect: "+Coord[j][2]+","+Coord[j][3]);
            /*for (i = 0; i < 6; i++) {
                Coord[j][i] = 0;//seed with 0...THEN modify in if's below.
                //System.out.println("Coord[" + j + "][" + i + "] =" + Coord[j][i]);
                System.out.println("RectNum: "+RectNum+"\ni:"+i);
                //stores top, leftmost coord pair.
                /*if (i == 0)//|| i == 1) only need to do this step once...
                {
                    //System.out.println("Coord[" + j + "][" + i + "] = is a start val....");
                    Coord[j][0]=placeX-xSize;
                    Coord[j][1]=placeY-ySize;//stores top, leftmost coord pair.
                }
                if (i == 4)//|| i == 5) only need to do this step once...
                {
                    //System.out.println("Coord[" + j + "][" + i + "] = is a center val....");
                    Coord[j][4]=placeX;
                    Coord[j][5]=placeY;//stores middle coord-middle point in rect.
                    System.out.println("Coord[" + j + "][" + i + "] = "+Coord[j][4]+","+Coord[j][5]);
                }
                if (i == 2 )//|| i == 3) only need to do this step once...
                {
                    //System.out.println("Coord[" + j + "][" + i + "] = is an end val....");
                    Coord[j][2]=placeX+xSize;
                    Coord[j][3]=placeY+ySize;//stores bottom, rightmost coord pair.
                }
                //else{System.out.println("Error...i != 0-5....");}
            }*/
            RectNum++;//increment rect num in outer loop.
        }
        /*Coord[4][4]=(getWidth()/2);
        Coord[4][5]=(getHeight()/2);*/
        if (null == monsters) { return; }

        paint.setStyle(Style.FILL);
        //TODO put popBoard  here.?
        for (final Monster monster : monsters.getMonsters()) {
            paint.setColor(monster.getColor());
            canvas.drawCircle(
                    monster.getX(),
                    monster.getY(),
                    monster.getDiameter(),
                    paint);
        }
    }
}