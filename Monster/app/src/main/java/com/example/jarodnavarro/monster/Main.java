package com.example.jarodnavarro.monster;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main extends AppCompatActivity
{
    TextView display;
    ImageView doge;
    boolean fight;
    int see = 3;
    int life, vul;
    Button multi;
    long startTime = 0; //used for 3 second timer
    Handler timerHandler = new Handler(); //Used as a hanlder for the first timer
    Runnable timerRunnable = new Runnable() { //run 3 second
        @Override
        public void run() {//run the timer
            long millaseconds = System.currentTimeMillis() - startTime;
            int seconds = (int) (millaseconds / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerHandler.postDelayed(this, 500);
            System.out.println(seconds + "seconds");
            if (seconds == vul)
            {
                doge.setVisibility(View.VISIBLE);
                fight = true;
                multi.setText("Attack!");
            }
            else if (seconds < see)
            {
                display.setVisibility(View.VISIBLE);
                display.setText(life + " Lives remain");
            }
            if (seconds > see)
            {
                display.setVisibility(View.INVISIBLE);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multi = (Button) findViewById(R.id.multi);
        doge = (ImageView) findViewById(R.id.doge);
        display= (TextView) findViewById(R.id.display);

        multi.setText("Start");
        vul = 0;
        multi.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v)
           {
               Button multi = (Button) v;

               if(multi.getText().equals("Start"))
               {
                   Random rand_life = new Random();
                   life = rand_life.nextInt(10);
                   if (life == 0) {life = 1;}
                   System.out.println(life + "Lives");
                   Random rand_vul = new Random(); vul = rand_vul.nextInt(30);
                   System.out.println(vul + "Time");
                   startTime=System.currentTimeMillis();
                   timerHandler.postDelayed(timerRunnable,0);
                   multi.setText("Wait!");
               }
               if (multi.getText().equals("Attack!") && fight == true)
               {
                  if(life != 0)
                  {
                      life--;
                      timerHandler.removeCallbacks(timerRunnable);
                      startTime = System.currentTimeMillis();
                      timerHandler.postDelayed(timerRunnable, 0);
                      fight = false;
                      doge.setVisibility(View.INVISIBLE);
                      multi.setText("Wait!");


                  }
                  else if (life == 0)
                  {
                      display.setText("You Win!");
                      display.setVisibility(View.VISIBLE);
                      timerHandler.removeCallbacks(timerRunnable);
                      multi.setEnabled(false);
                      doge.setVisibility(View.INVISIBLE);
                  }

               }

           }
        });
    }
    /*public final class Rand extends Main
    {
        final int life, vul, non_vul;

        public Rand(final int life, final int vul, final int non_vul)
        {
            this.life=life;
            this.vul=vul;
            this.non_vul=non_vul;
        }

        public int generate_rand()
        {
           Random rand = new Random();
            int x_life = rand.nextInt(20);
            int x_vul = rand.nextInt(20);
            int x_non_vul = rand.nextInt(20);
            x_life = life;
            x_vul = vul;
            x_non_vul= non_vul;
            return 1;
        }
        public int getLife() {return life;}
        public int getVul() {return vul;}
        public int getNon_vul(){return non_vul;}
    }*/

}
