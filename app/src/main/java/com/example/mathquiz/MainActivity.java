package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout firstcl , seccl;
    TextView qtextView , stextView , atextView, ttextView;
    int correctAn;
    ArrayList<Integer> answers;
    Button button0, button1, button2, button3, playagain;
    int score, numq;

    public void onStartClick(View view)
    {
        firstcl.setVisibility(View.INVISIBLE);
        seccl.setVisibility(View.VISIBLE);
        play();
    }

    private void play() {
        //set q
        getNewQ();

        //set s
        score=0;
        numq =0 ;
        setScore();

        //set empty value is ans (initial)
        atextView.setText(" ");

        //set timer
        setTimer();

    }

    private void setTimer() {
        //mili
        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                ttextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                atextView.setText("GAME OVER!");
                playagain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void setScore() {
        stextView.setText(Integer.toString(score)+"/" + Integer.toString(numq));
    }

    private void getNewQ() {
        Random random = new Random();
        int a = random.nextInt(31);
        int b = random.nextInt(31);

        qtextView.setText(Integer.toString(a) + " +" +Integer.toString(b) + "= ?");
        correctAn = random.nextInt(4);
        answers.clear();

        for(int i= 0 ; i<4 ; i++)
        {
            if(i  == correctAn) //bet 0 3
            {
                answers.add(a+b);
            }
            else {
                int wrongAn = random.nextInt(61);
                while(wrongAn == a+b)
                {
                    wrongAn = random.nextInt(61);
                }
                answers.add(wrongAn);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void onOptionsClick(View view)
    {
        if(Integer.toString(correctAn).equals(view.getTag().toString()))
        {
            score++;
            atextView.setText("Correct!");

        }
        else {
            atextView.setText("Wrong!");
        }
        numq++;
        setScore();
        getNewQ();
    }

    public void onPlayClick(View view)
    {
        playagain.setVisibility(View.INVISIBLE);
        play();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstcl = findViewById(R.id.fistctl);
        seccl = findViewById(R.id.secondcl);
        qtextView = findViewById(R.id.quetv);
        answers= new ArrayList<>();
        button0 = findViewById(R.id.bt0);
        button1 = findViewById(R.id.bt1);
        button2 = findViewById(R.id.bt2);
        button3 = findViewById(R.id.bt3);
        stextView = findViewById(R.id.numq);
        atextView = findViewById(R.id.answer);
        ttextView = findViewById(R.id.timertv);
        playagain = findViewById(R.id.playAgain);
    }
}