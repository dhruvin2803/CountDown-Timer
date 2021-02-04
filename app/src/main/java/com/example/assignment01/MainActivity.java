package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long StartTime = 30000;
    private TextView displayTextView;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button incrementButton;
    private CountDownTimer mCountDownTimer;
    private Button decrementButton;
    private long timeStarted = StartTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTextView = findViewById(R.id.displaytimer);
        startButton = findViewById(R.id.StartButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);
        incrementButton = findViewById(R.id.incButton);
        decrementButton = findViewById(R.id.decButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTimer();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueUp();
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueDown();
            }
        });
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(timeStarted, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeStarted = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                mCountDownTimer.cancel();
                Toast.makeText(getApplicationContext(), //Context
                        "Maximum Range", // Message to display
                        Toast.LENGTH_SHORT
                ).show();
                resetButton.setVisibility(View.VISIBLE);
                incrementButton.setVisibility(View.VISIBLE);
                decrementButton.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.INVISIBLE);
            }
        }.start();
        resetButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        stopButton.setVisibility(View.VISIBLE);
        incrementButton.setVisibility(View.INVISIBLE);
        decrementButton.setVisibility(View.INVISIBLE);
    }
    private void stopTimer(){
        mCountDownTimer.cancel();
        resetButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        incrementButton.setVisibility(View.VISIBLE);
        decrementButton.setVisibility(View.VISIBLE);
    }
    private void resetTimer(){
        timeStarted = StartTime;
        resetButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        incrementButton.setVisibility(View.VISIBLE);
        decrementButton.setVisibility(View.VISIBLE);
        updateText();
    }
    private void updateText(){
        int sec = (int) (timeStarted / 1000);
        String timeDisplay = String.format(Locale.getDefault(),":%02d", sec);
        displayTextView.setText(timeDisplay);
    }

    private void valueUp(){
        timeStarted = timeStarted + 1000;
        if(timeStarted>= 99000){
            Toast.makeText(getApplicationContext(), //Context
                    "Maximum Range", // Message to display
                    Toast.LENGTH_SHORT
            ).show();
            timeStarted = 99000;
        }
        updateText();
    }

    private void valueDown(){
        timeStarted = timeStarted - 1000;
        if(timeStarted<=5000){
            Toast.makeText(getApplicationContext(), //Context
                    "Minimum Range", // Message to display
                    Toast.LENGTH_SHORT
            ).show();
            timeStarted = 5000;
        }
        updateText();
    }


}