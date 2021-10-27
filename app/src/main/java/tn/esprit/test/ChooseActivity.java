package tn.esprit.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ImageView BellyFat;
        BellyFat = findViewById(R.id.BellyFat);

        BellyFat.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    BellyFat1.class);


            startActivity(intent);


        });

        ImageView Abs;
        Abs = findViewById(R.id.Abs);

        Abs.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    SecondActivity.class);


            startActivity(intent);


        });
    }
}