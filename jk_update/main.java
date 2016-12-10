public String time = ""        // start time
public String invul = "Green"; // present color: Green, Yelloe
public int k = 0;              // monsters
public int x = 0;              // lives
public int ticks = 0;          // ticks left
public bool FoE = False;       // Fell off left edge, out of bounds
public int Ax, Ay, Bx, By, Cx, Cy, Dx, Dy, Ex, Ey, Fx, Fy, Gx, Gy, Hx, Hy;  // positioning
Ax = Bx = Cx = Cy = Dx = Dy = Ex = Ey = Fx = Fy = Gx = Gy = Hx = Hy =0;
public Object[][][][][][][] Monsters;   // Array: k, time, Color, xCoord, yCoord, x, ticks
                                     //                 1=Green, 2=Yellow

public static void main()

   // What's our screen size?
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int dpHeight = (int)(displayMetrics.heightPixels / displayMetrics.density + 0.5);
        int dpWidth = (int)(displayMetrics.widthPixels / displayMetrics.density + 0.5);
        int n=9;
        int m=1;
        System.out.println("(dpHeight*dpWidth)/(n*m):"+(dpHeight*dpWidth)/(n*m));
   //ex. 576h x 320w    **Flip phone sideways?  if time permits <------------------TODO

   /** Dot diameter */
   public static final int DOT_DIAMETER = 10;

   // Select value for xCoord and yCoord  == current position

   BuildBox();   //Build Box around X of valid positions. If x or y = 0 ==invalid position
   CheckFoE();   //Check if Fell off Edge, out of bounds
   PopBoard();   //Populate Board (array) & check clocks
   DrawBoard();


/* Android graphics and touch events
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
              addDot( mDots, evt.getHistoricalX(idx, j), evt.getHistoricalY(idx, j),
              evt.getHistoricalPressure(idx, j), evt.getHistoricalSize(idx, j));
           }
        }
        break;

default:
        return false;
        }
*/


public void PopBoard(){   
//Populate Board (array) & check clocks            //<----------- ***///
        Currtime = System.currentTimeMillis();       // grabs current time to compare to ticks/ ticks+time.//<----------- ***///
        for i = 0 to *(Monsters.length){
        //k = Monsters[i][0]; //Monster ID
        time = Monsters[i][1];
        ticks = Monsters[i][6];
        Color = Monsters[i][2];   // Color Flip?   1=Green, 2=Yellow
        x = Monsters[i][5];       //x (lives)
        //Monsters[i][3], Monsters[i][4] store the xCoord, yCoord of a monster, respectively.

        if ticks > 0 {
           ticks --;
        } else {
           ticks= new Random().nextInt(61-5)+5;
        };
        if (time == 0)
        {
            time = int()Currtime;
        }


        Monsters[i][2] = new Random().nextInt(3-1)+1;    // Color Flip?   1=Green, 2=Yellow
        Monsters[i][3] = new Random().nextInt(dpWidth - DOT_DIAMETER) + DOT_DIAMETER;  // xCoord
        Monsters[i][4] = new Random().nextInt(dpHeight - DOT_DIAMETER) + DOT_DIAMETER;  // yCoord
        Monsters[i][5] =  3;   //x (Lives) ...temporarily set to 3.
        Monsters[i][6] = ticks;
        // TODO: for Monsters[i][3], Monsters[i][4], make sure monster appears within the limits of the display,
        //and if possible, limit so that only one monster can be in a display at any time.
        };

public void DrawBoard(){
        // Clear Board()   <------------------------------------------------------------------
   if( [xCoord] && [yCoord] > -1) {
        int Color = 0;
        // read data (coordinates, Color, etc.) from monster Array
        // int[][][][][][][] Monsters;   // Array: k, time, Color, xCoord, yCoord, x, ticks
        //                                                  1=Green, 2=Yellow
        for i = 0 to *(Monsters.length){
           //k = Monsters[i][0];
           //time = Monsters[i][1];
           Color  = Monsters[i][2];
           xCoord  = Monsters[i][3];
           yCoord  = Monsters[i][4];
           //x     = Monsters[i][5];
           //ticks = Monsters[i][6];

        switch (keyCode){
           case Color==2:
              invul=Yellow;
           case Color==1:
              invul=Green;
           default:
              invul=Black;
        }
        // actual board plot
        // at xCoord, yCoord using (invul) paint dot  //"M"+right(Integer.toString((100+k)),2)
        paint.setColor(Color);
        //canvas.drawCircle( xCoord, YCoord, DOT_DIAMETER, paint);
        canvas.drawRect((xCoord - (DOT_DIAMETER/2)),(xCoord - (DOT_DIAMETER/2)), (xCoord + (DOT_DIAMETER/2)), (yCoord - (DOT_DIAMETER/2)), paint);

        i++;
        }

      //for(int r = -1; r <= 1; r++)
      //     for(int c = -1; c <= 1; c++)
      //     cellPicked(row + r, col + c);
        }
   };


public void CheckFoE(){
   // Edge of Board positioning  [A, C, F, H]   *Do postioning calc 1st
   FoE=.F.
   if((yCoord-DOT_DIAMETER)< 0)    // Fell off left edge
   {
      Ax=Ay=Dx=Dy=Fx=Fy=-1;
      FoE=.T.
      }else{
         if((xCoord-DOT_DIAMETER)< 0)    // Fell off bottom edge
         {
         Ax=Ay=Bx=By=Cx=Cy=-1;
         FoE=.T.
         }else{
            if((yCoord+DOT_DIAMETER)>dpWidth)    // Fell off right edge
            {
            Cx=Cy=Ex=Ey=Hx=Hy=-1;
            FoE=.T.
            }else{
               if((xCoord+DOT_DIAMETER)>dpHeight)    // Fell off top edge
               {
               Fx=Fy=Gx=Gy=Hx=Hy=-1;
               FoE=.T.
               }
            }
         }
      }
   }
};   // FoE flag set ?


   // monster names  (array: No+k)


public void BuildBox() {
   /*Position(s)around 'x'
   A B C
   D x E
   F G H
   xCoord = current position
   yCoord = current position
   rLimit = Right Edge
   bLimit = Bottom Edge
   FoE = Logical Fell off Edge

   Ax = (xCoord-1); Ay = (yCoord-1);
   Bx = (xCoord-1); By = (yCoord);
   Cx = (xCoord-1); Cy = (yCoord+1);
   Dx = (xCoord);   Dy = (yCoord-1);
   Ex = (xCoord);   Ey = (yCoord+1);
   Fx = (xCoord+1); Fy = (yCoord-1);
   Gx = (xCoord+1); Gy = (yCoord);
   Hx = (xCoord+1); Hy = (yCoord+1);  */

   Dx = Ex = (xCoord);
   By = Gy = (yCoord);
   Ax = Bx = Cx = (xCoord-DOT_DIAMETER);
   Fx = Gx = Hx = (xCoord+DOT_DIAMETER);
   Ay = Dy = Fy = (yCoord-DOT_DIAMETER);
   Cy = Ey = Hy = (yCoord+DOT_DIAMETER);
}




/*  https://www.cs.utexas.edu/~scottm/cs307/javacode/codeSamples/MineSweeper.java
public class MineSweeper
{	private int[][] myTruth;
	private boolean[][] myShow;

	public void cellPicked(int row, int col)
	{	if( inBounds(row, col) && !myShow[row][col] )
		{	myShow[row][col] = true;

			if( myTruth[row][col] == 0)
			{	for(int r = -1; r <= 1; r++)
					for(int c = -1; c <= 1; c++)
						cellPicked(row + r, col + c);
			}
		}
	}

	public boolean inBounds(int row, int col)
	{	return 0 <= row && row < myTruth.length && 0 <= col && col < myTruth[0].length;
	}
}
*/
