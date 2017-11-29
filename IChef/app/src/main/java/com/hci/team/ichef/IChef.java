package com.hci.team.ichef;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;



/**
 * Created by Chris on 25/11/2017.
 */

public class IChef extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private TextView timerText;
    private TextView instruction;
    private Long recipeTime;
    private Long taskTimeRemaining;
    private CountDownTimer timer;
    private TextToSpeech outputSpeech;
    private Long taskTime;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ichef);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        resumeButton = (Button) findViewById(R.id.resumeButton);
        timerText = (TextView) findViewById(R.id.timer);
        instruction = (TextView) findViewById(R.id.instructions);
        recipeTime = 1140000L;
        taskTimeRemaining = 0L;
        taskTime = 300000L;
        outputSpeech = new TextToSpeech(this,this);
    }


    public void onClick(View view){
        if(view == startButton){
            view.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            createTimer(taskTime);
        } else if(view == pauseButton){
            view.setVisibility(View.INVISIBLE);
            resumeButton.setVisibility(View.VISIBLE);
            timer.cancel();
            taskTime = taskTimeRemaining;
            recipeTime = recipeTime - taskTime;
        } else if(view == resumeButton){
            view.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            createTimer(taskTime);
        }
    }


    public void createTimer(Long time){
        setText(recipeTime);
        timer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time Remaining: " + (millisUntilFinished/1000)/60 + ":" + (millisUntilFinished/1000)%60);
                taskTimeRemaining = millisUntilFinished;


            }

            @Override
            public void onFinish() {
                recipeTime = recipeTime - taskTime;
                if(recipeTime != 0L){
                    taskTime = calculateTaskLength(recipeTime);
                    createTimer(calculateTaskLength(taskTime));
                }
                else{
                    timerText.setText("Finished");
                }
            }
        }.start();
    }

    private Long calculateTaskLength(Long time){
        if(time > 240000){
            return 600000L;
        } else {
            return 120000L;
        }
    }


    private void setText(Long time){
        if(time > 840000L){
            instruction.setText("Cook Pasta and Fry pepper");
            outputSpeech.speak("Cook Pasta and Fry Pepper",TextToSpeech.QUEUE_FLUSH,null,null);
        } else if(time > 240000L && time <= 840000L){
            instruction.setText("Cook salmon");
            outputSpeech.speak("Cook Salmon",TextToSpeech.QUEUE_FLUSH,null,null);
        } else if(time > 120000L && time <= 240000L){
            instruction.setText("Dress");
            outputSpeech.speak("Dress",TextToSpeech.QUEUE_FLUSH,null,null);
        } else if(time < 120000L){
            instruction.setText("Add Everything");
            outputSpeech.speak("Add Everything",TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }



    @Override
    public void onDestroy(){
        if(outputSpeech != null){
            outputSpeech.stop();
            outputSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = outputSpeech.setLanguage(Locale.ENGLISH);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(IChef.this, "No Speech Output", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(IChef.this, "No Speech Output", Toast.LENGTH_SHORT).show();
        }
    }






}
