package tn.esprit.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button ButtonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButtonNext.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    ChooseActivity.class);


            startActivity(intent);


        });
    }
}