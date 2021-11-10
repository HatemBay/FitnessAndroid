package tn.esprit.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.User;

public class BMIActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
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