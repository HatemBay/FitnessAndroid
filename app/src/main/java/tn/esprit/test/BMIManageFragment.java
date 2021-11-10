package tn.esprit.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.BMI;
import tn.esprit.test.entity.User;


public class BMIManageFragment extends Fragment {

    User user;

    public static BMIManageFragment newInstance(User user) {
        BMIManageFragment fragment = new BMIManageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putSerializable("user", user);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_i_manage, container, false);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.example_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(BMIManageFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(BMIManageFragment.this.getActivity(), MainActivity.class);
            Toast.makeText(BMIManageFragment.this.getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}