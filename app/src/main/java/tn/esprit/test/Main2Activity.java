package tn.esprit.test;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.User;

public class Main2Activity extends AppCompatActivity implements CallbackFragment{

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView homeTv;
    TextView welcome2Tv;

    private AppDataBase database;
    private UsersAdapter usersAdapter;
    private List<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = AppDataBase.getAppDatabase(this);
        userList = database.userDao().getAll();
        usersAdapter = new UsersAdapter(getApplicationContext(), userList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addFragment();

        homeTv = findViewById(R.id.homeTv);
        welcome2Tv = findViewById(R.id.welcome2Tv);

    }

    public void addFragment(){
        FragmentLogin fragment = new FragmentLogin();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(){
        fragment = new FragmentRegister();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment() {
        replaceFragment();
    }
}
