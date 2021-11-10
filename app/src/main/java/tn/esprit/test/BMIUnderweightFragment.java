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
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.test.entity.User;

public class BMIUnderweightFragment extends Fragment {

    TextView tvText;
    User user;

    public static BMIUnderweightFragment newInstance(User user) {
        BMIUnderweightFragment fragment = new BMIUnderweightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putSerializable("user", user);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_i_underweight, container, false);

        setHasOptionsMenu(true);

        tvText = view.findViewById(R.id.tvText);

        tvText.setText("For breakfast, porridge made with whole (full-fat) milk with chopped fruit or raisins sprinkled on top; or eggs on toast.\n" +
                "Milkshakes are a great snack (make them at home and take them to work or college). Fortify them with milk powder for extra protein and calories.\n" +
                "For a healthier lunch, try a jacket potato with baked beans or tuna on top, which contains energy-giving starchy carbohydrates and protein.\n" +
                "Peanut butter on toast makes a great high-energy snack.\n" +
                "Yoghurts and milky puddings, such as rice pudding, are high in energy.\n" +
                "Unsalted nuts.\n" +
                "Although fruit and vegetable juices and smoothies count towards your 5 A Day, remember to limit these to no more than a combined total of 150ml a day.");

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
            Intent intent = new Intent(BMIUnderweightFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(BMIUnderweightFragment.this.getActivity(), MainActivity.class);
            Toast.makeText(BMIUnderweightFragment.this.getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}