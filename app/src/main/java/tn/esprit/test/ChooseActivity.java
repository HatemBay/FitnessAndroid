package tn.esprit.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import tn.esprit.test.entity.User;

public class ChooseActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ImageView BellyFat;
        BellyFat = findViewById(R.id.BellyFat);

        user = (User) getIntent().getSerializableExtra("user");

        BellyFat.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    BellyFat1.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }

        });

        ImageView Abs;
        Abs = findViewById(R.id.Abs);

        Abs.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Abs1.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }
            startActivity(intent);
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