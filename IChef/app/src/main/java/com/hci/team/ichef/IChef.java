package com.hci.team.ichef;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private Button currentView;
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
        currentView = startButton;
        recipeTime = 1140000L;
        taskTimeRemaining = 0L;
        taskTime = 300000L;
        outputSpeech = new TextToSpeech(this, this);

        SensorManager senseManage = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = senseManage.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null){
            senseManage.registerListener(LightSensorListener,lightSensor,senseManage.SENSOR_DELAY_NORMAL);
        } else{

        }

    }

    private final SensorEventListener LightSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                if(event.values[0] < 5){
                    onClick(currentView);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    public void onClick(View view){
        if(view == startButton){
            view.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            currentView = pauseButton;
            createTimer(taskTime);
        } else if(view == pauseButton){
            outputSpeech.speak("Paused",TextToSpeech.QUEUE_FLUSH,null,null);
            view.setVisibility(View.INVISIBLE);
            resumeButton.setVisibility(View.VISIBLE);
            currentView = resumeButton;
            timer.cancel();
            recipeTime = recipeTime - (taskTime - taskTimeRemaining);
            taskTime = taskTimeRemaining;
        } else if(view == resumeButton){
            view.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            currentView = pauseButton;
            createTimer(taskTime);
        }
    }


    public void createTimer(Long time){
        setText(recipeTime);
        timer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("" + (millisUntilFinished/1000)/60 + ":" + (millisUntilFinished/1000)%60);
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
            instruction.setText("Add Pasta to a pan of Boiling Water. Heat oil in a frying pan and add the pepper");
            outputSpeech.speak("Add Pasta to a pan of Boiling Water. Heat oil in a frying pan and add the pepper",TextToSpeech.QUEUE_FLUSH,null,null);
        } else if(time > 240000L && time <= 840000L){
            instruction.setText("Take the pepper out of the frying pan and add the Salmon to it");
            outputSpeech.speak("Take the pepper out of the frying pan and add the Salmon to it",TextToSpeech.QUEUE_FLUSH,null,null);
        } else if(time > 120000L && time <= 240000L){
            instruction.setText("Mix the lemon zest and juice in a bowl with garlic,shallot,capers and olives");
            outputSpeech.speak("Mix the lemon zest and juice in a bowl with garlic,shallot,capers and olives",TextToSpeech.QUEUE_FLUSH,null,null);
        } else if(time < 120000L){
            instruction.setText("Add the cooked pepper and salmon to the bowl. Drain the pasta and add it to the bowl. Toss with black pepper, olive oil and rocket");
            outputSpeech.speak("Add the cooked pepper and salmon to the bowl. Drain the pasta and add it to the bowl. Toss with black pepper, olive oil and rocket",TextToSpeech.QUEUE_FLUSH,null,null);
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
