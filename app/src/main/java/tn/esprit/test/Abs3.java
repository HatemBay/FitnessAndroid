package tn.esprit.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.test.entity.User;

public class Abs3 extends AppCompatActivity {


    User user;
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs3);
        user = (User) getIntent().getSerializableExtra("user");

        ImageView ForwardButton;
        ForwardButton = findViewById(R.id.ForwardButton);

        ForwardButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Abs4.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageView BackButton;
        BackButton = findViewById(R.id.BackButton);

        BackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Abs2.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }
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
                        Intent intent = new Intent(Abs3.this, Abs4.class);
                        if (getIntent().getSerializableExtra("user") != null) {
                            intent.putExtra("user", user);
                            startActivity(intent);
                        }                    }
                }.start();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }else {
            Intent logoutIntent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}