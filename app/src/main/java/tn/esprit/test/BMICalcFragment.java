package tn.esprit.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
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
    int height, weight;
    double result, heightCm;
    EditText etHeight, etWeight;
    Button btnCalculate, btnHistory, btnAddHistory, btnNext;
    CallbackFragment callbackFragment;
    SharedPreferences sharedPreferences, bmiSharedPreferences;
    SharedPreferences.Editor editor;
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
        View view =  inflater.inflate(R.layout.fragment_b_m_i_calc, container, false);

        if (getArguments() != null){
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

            if ((etHeight == null)||(etWeight == null)||(height == 0)||(weight == 0)) {
                Toast.makeText(getContext(), "Please Provide Valid Data", Toast.LENGTH_LONG).show();
            } else {
                heightCm = (double) height/100;
                result = weight/Math.pow(heightCm,2);
                result = Double.parseDouble(new DecimalFormat("##.#").format(result));
                tvBMI.setText(String.valueOf(result));

                if (result < 18.6){
                    tvWeightRes.setText("Category: Underweight");
                } else if (result < 25){
                    tvWeightRes.setText("Category: Normal weight");
                } else if (result < 30){
                    tvWeightRes.setText("Category: Overweight");
                } else {
                    tvWeightRes.setText("Category: Obese");
                }

                tvBMI.setVisibility(View.VISIBLE);
                tvWeightRes.setVisibility(View.VISIBLE);
                btnAddHistory.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
            }
        });

        btnAddHistory.setOnClickListener(view1 -> {
            String date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.getDefault()).format(new Date());
            dataBase.bmiDao().addBMI(user.getUid(), result, date);
            Toast.makeText(getContext(), user.getUsername(), Toast.LENGTH_SHORT).show();

        });

        btnHistory.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, BMIHistoryFragment.newInstance(user)).commit();
        });

        btnNext.setOnClickListener(view1 -> {
        //

        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }
}