package tn.esprit.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        btnBMI.setOnClickListener(view -> {
            Intent intent = new Intent(this, BMIActivity.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
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
        if (item.getItemId() == R.id.logout) {
            Intent logoutIntent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}