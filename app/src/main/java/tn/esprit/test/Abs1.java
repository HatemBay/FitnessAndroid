package tn.esprit.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Abs1 extends AppCompatActivity {


    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs1);


        ImageView ForwardButton;
        ForwardButton = findViewById(R.id.BellyFat);

        ForwardButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Abs2.class);


            startActivity(intent);


        });



        Button ButtonStart;

        ButtonStart = findViewById(R.id.ButtonStart);

        ButtonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
                mProgressBar.setProgress(i);
                mCountDownTimer= new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                        i++;
                        mProgressBar.setProgress((int)i*100/(30000/1000));

                    }

                    @Override
                    public void onFinish() {
                        //Do what you want
                        i++;
                        mProgressBar.setProgress(100);
                    }
                };
                mCountDownTimer.start();
                ForwardButton.setEnabled(false);
                ButtonStart.setEnabled(false);
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {

                        TextView mTextField = findViewById(R.id.mTextField2);
                        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        TextView mTextField = findViewById(R.id.mTextField2);
                        mTextField.setText("done!");
                        Intent intent = new Intent(Abs1.this, Abs2.class);
                        startActivity(intent);
                    }
                }.start();


            }
        });
    }}