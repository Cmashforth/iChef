package com.hci.team.ichef;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Chris on 25/11/2017.
 */

public class IChef extends AppCompatActivity {

    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private TextView timerText;
    private Long taskTime;
    private Long timeRemaining;
    private CountDownTimer timer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ichef);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        resumeButton = (Button) findViewById(R.id.resumeButton);
        timerText = (TextView) findViewById(R.id.timer);
        taskTime = 300000L;
        timeRemaining = 0L;
    }


    public void onClick(View view){
        if(view == startButton || view == resumeButton){
            view.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            createTimer(taskTime);
        }
        else if(view == pauseButton){
            view.setVisibility(View.INVISIBLE);
            resumeButton.setVisibility(View.VISIBLE);
            timer.cancel();
            taskTime = timeRemaining;
        }
    }


    public void createTimer(Long time){
        timer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Seconds Remaining: " + millisUntilFinished/1000);
                timeRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                timerText.setText("Finished");
            }
        }.start();
    }






}
