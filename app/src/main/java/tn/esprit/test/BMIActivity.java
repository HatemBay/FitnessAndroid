package tn.esprit.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.User;

public class BMIActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDataBase dataBase = AppDataBase.getAppDatabase(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        user = (User) getIntent().getSerializableExtra("user");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, BMICalcFragment.newInstance(user)).commit();
    }
}