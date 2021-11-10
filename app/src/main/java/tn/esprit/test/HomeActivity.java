package tn.esprit.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.test.entity.User;

public class HomeActivity extends AppCompatActivity {


    Button btnTraining, btnBMI;

    TextView tvWelcomeUser;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnTraining = findViewById(R.id.btnTraining);
        btnBMI = findViewById(R.id.btnBMI);
        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);

        user = (User) getIntent().getSerializableExtra("user");

        tvWelcomeUser.setText(String.format("Welcome %s", user.getUsername()));

        btnTraining.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChooseActivity.class);

            startActivity(intent);
        });

        btnBMI.setOnClickListener(view -> {
            Intent intent = new Intent(this, BMIActivity.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

}