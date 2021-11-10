package tn.esprit.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.BMI;
import tn.esprit.test.entity.User;

public class BMICalcFragment extends Fragment {

    TextView tvBMI, tvWeightRes;
    int height, weight, index;
    double result, heightCm;
    EditText etHeight, etWeight;
    Button btnCalculate, btnHistory, btnAddHistory, btnNext;
    CallbackFragment callbackFragment;
    User user;
    AppDataBase dataBase;

    public static BMICalcFragment newInstance(User user) {
        BMICalcFragment fragment = new BMICalcFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataBase = AppDataBase.getAppDatabase(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_i_calc, container, false);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }

        tvBMI = view.findViewById(R.id.tvBMI);
        tvWeightRes = view.findViewById(R.id.tvWeightRes);

        etHeight = view.findViewById(R.id.etHeight);
        etWeight = view.findViewById(R.id.etWeight);

        btnCalculate = view.findViewById(R.id.btnCalculate);
        btnHistory = view.findViewById(R.id.btnHistory);
        btnAddHistory = view.findViewById(R.id.btnAddHistory);
        btnNext = view.findViewById(R.id.btnNext);

        btnCalculate.setOnClickListener(view1 -> {
            height = Integer.parseInt(etHeight.getText().toString());
            weight = Integer.parseInt(etWeight.getText().toString());

            if ((etHeight == null) || (etWeight == null) || (height == 0) || (weight == 0)) {
                Toast.makeText(getContext(), "Please Provide Valid Data", Toast.LENGTH_LONG).show();
            } else {
                heightCm = (double) height / 100;
                result = weight / Math.pow(heightCm, 2);
                result = Double.parseDouble(new DecimalFormat("##.#").format(result));
                tvBMI.setText(String.valueOf(result));

                if (result < 18.6) {
                    tvWeightRes.setText("Category: Underweight");
                    index = 0;
                } else if (result < 25) {
                    tvWeightRes.setText("Category: Normal weight");
                    index = 1;
                } else if (result < 30) {
                    tvWeightRes.setText("Category: Overweight");
                    index = 2;
                } else {
                    tvWeightRes.setText("Category: Obese");
                    index = 2;
                }

                tvBMI.setVisibility(View.VISIBLE);
                tvWeightRes.setVisibility(View.VISIBLE);
                btnAddHistory.setVisibility(View.VISIBLE);
                btnAddHistory.setClickable(true);
                btnNext.setVisibility(View.VISIBLE);
            }
        });

        btnAddHistory.setOnClickListener(view1 -> {
            String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date());
            dataBase.bmiDao().addBMI(user.getUid(), result, date);
            Toast.makeText(getContext(), "BMI successfully added to history", Toast.LENGTH_SHORT).show();
            btnAddHistory.setClickable(false);
            btnAddHistory.setBackgroundColor(Color.GRAY);

        });

        btnHistory.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, BMIHistoryFragment.newInstance(user)).commit();
        });

        btnNext.setOnClickListener(view1 -> {
            if (index == 0) {
                requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, BMIUnderweightFragment.newInstance(user)).commit();
            }
            if (index == 1) {
                requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, BMINormalFragment.newInstance(user)).commit();
            }
            if (index == 2) {
                requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, BMIObeseFragment.newInstance(user)).commit();
            }
        });

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
            Intent intent = new Intent(BMICalcFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(BMICalcFragment.this.getActivity(), MainActivity.class);
            Toast.makeText(BMICalcFragment.this.getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}