package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;

    Random random;
    Button textView0;
    TextView textView;
    Button textView1;
    Button textView2;
    Button textView3;
    TextView textView5;
    TextView timerText;
    TextView totalText;
    ConstraintLayout constraint1;
    ConstraintLayout constraint2;
    int correct=0;
    int total=0;
    int a;
    int b;
    int x;
    int y;
    int p;
    int q;
    int r;
    Button button3;
    Button button;
    Button button2;
    MediaPlayer firstmedia;
    MediaPlayer secondmedia;
    MediaPlayer thirdmedia;
    MediaPlayer fourthmedia;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String>arrayAdapter;

    ListView listView;
    public void leaderboard(View view) {
        try{SQLiteDatabase sqLiteDatabase=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,score INT(3)) ");
        //sqLiteDatabase.execSQL("INSERT INTO users(name,score) VALUES('score',16) ");
       // sqLiteDatabase.execSQL("INSERT INTO users(name,score) VALUES('high score',19)");
        Cursor c= sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE 's%' LIMIT 1",null);
        int Column= c.getColumnIndex("name");
        int columnscore=c.getColumnIndex("score");
        c.moveToFirst();
        while(!c.isAfterLast()){
            arrayList.add(c.getString(Column)+" - "+c.getString(columnscore));
           arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);
            c.moveToNext();
        }}catch (Exception e){
            e.printStackTrace();
        }

    }

    public void gameStart(View view){
        /*constraint1.setVisibility(View.INVISIBLE);*/
        fourthmedia.start();

        constraint2.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        textView0.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        /*timerText.setVisibility(View.VISIBLE);*/




    }


    public void startTimer(View view){

        correct=0;
        total=0;
        button3.setVisibility(View.INVISIBLE);
        button3.setEnabled(false);
        textView0.setEnabled(true);
        textView1.setEnabled(true);
        textView2.setEnabled(true);
        textView3.setEnabled(true);
        textView.setText(" ");
        totalText.setText(Integer.toString(correct-correct)+"/"+Integer.toString(total-total));
        textView0.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        timerText.setVisibility(View.VISIBLE);
        fourthmedia.start();




        timer = new CountDownTimer(30*1000+100,1000) {
            @Override
            public void onTick(long l) {

                timerText.setText(Objects.toString(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                button3.setEnabled(true);
                button3.setVisibility(View.VISIBLE);
                textView0.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
                textView5.setVisibility(View.INVISIBLE);
                timerText.setVisibility(View.INVISIBLE);
                textView0.setEnabled(false);
                textView1.setEnabled(false);
                textView2.setEnabled(false);
                textView3.setEnabled(false);
                textView.setText("your"+" "+"score"+" "+"is"+" "+Integer.toString(correct)+"/"+Integer.toString(total)+"!");
                thirdmedia.start();
                textView.setTranslationY(-1500);
                textView.animate().translationYBy(1500).setDuration(1000);
                textView.setBackground(getDrawable(R.color.black));
                textView.setTextColor(Color.WHITE);
                button2.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void answer(View view ){

         totalText.setText(Integer.toString(correct)+"/"+Integer.toString(total));
        Integer.parseInt(view.getTag().toString());
           if(Integer.toString(x).equals(view.getTag().toString())){
               textView.setText("correct!");
               textView.setTextColor(Color.GREEN);
               textView.setTranslationX(-1500);
               textView.animate().translationXBy(1500).setDuration(200);
               secondmedia.start();
               correct=correct+1;
           }
          else {
               textView.setText("incorrect!");
               textView.setTextColor(Color.RED);
               textView.setTranslationX(1500);

               textView.animate().translationXBy(-1500).setDuration(200);

               firstmedia.start();
           }
           total=total+1;
          newQuestion();


       }
       public void newQuestion(){
           Button[] array=  {textView0, textView1, textView2, textView3};

           random= new Random();
           a=random.nextInt(21);
           b=random.nextInt(21);

           x=random.nextInt(4);
           y=random.nextInt(41);
           p=random.nextInt(41);
           q=random.nextInt(41);
           r=random.nextInt(41);

           while(y==a+b){
               y=random.nextInt(41);
           }
           while(p==a+b){
               p=random.nextInt(41);
           }
           while(q==a+b){
               q=random.nextInt(41);
           }
           while(r==a+b){
               r=random.nextInt(41);
           }
           textView5.setText(Integer.toString(a)+" "+"+"+" "+Integer.toString(b));


           array[x].setText(Integer.toString(a+b));


           if (x == 0 && x != 1 && x != 2 && x != 3) {


               array[1].setText(Integer.toString(p));
               array[2].setText(Integer.toString(q));
               array[3].setText(Integer.toString(r));

           }
           if (x != 0 && x == 1 && x != 2 && x != 3) {
               array[0].setText(Integer.toString(y));
               array[2].setText(Integer.toString(q));
               array[3].setText(Integer.toString(r));
           }
           if (x != 0 && x != 1 && x == 2 && x != 3) {
               array[0].setText(Integer.toString(y));
               array[1].setText(Integer.toString(p));
               array[3].setText(Integer.toString(r));
           }
           if (x != 0 && x != 1 && x != 2 && x == 3) {

               array[0].setText(Integer.toString(y));
               array[1].setText(Integer.toString(p));
               array[2].setText(Integer.toString(q));


           }

       }
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstmedia=MediaPlayer.create( this ,R.raw.wrong);
        secondmedia=MediaPlayer.create( this ,R.raw.correct);
        thirdmedia=MediaPlayer.create( this ,R.raw.timeup);
        fourthmedia=MediaPlayer.create(this,R.raw.click);
        listView=( ListView)findViewById(R.id.listView );
        button3=(Button)findViewById(R.id.button3);
        textView5=(TextView)findViewById(R.id.textView5);
        timerText=(TextView)findViewById(R.id.timerText);
        textView0=(Button) findViewById(R.id.textView0);
        textView1=(Button) findViewById(R.id.textView1);
        textView2=(Button) findViewById(R.id.textView2);
        textView3=(Button) findViewById(R.id.textView3);
        textView=(TextView) findViewById(R.id.textView);
        totalText=(TextView) findViewById(R.id.totalText);
        constraint1=(ConstraintLayout) findViewById(R.id.constraint1);
        constraint2=(ConstraintLayout) findViewById(R.id.constraint2);
        button=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);


        newQuestion();






    }
}