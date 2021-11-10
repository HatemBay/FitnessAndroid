package tn.esprit.test;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BMIHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIHistoryFragment extends Fragment {

    AppDataBase dataBase;
    TextView tvBMIList;
    User user;

    public static BMIHistoryFragment newInstance(User user) {
        BMIHistoryFragment fragment = new BMIHistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putSerializable("user", user);
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
        View view = inflater.inflate(R.layout.fragment_b_m_i_history, container, false);
        tvBMIList = view.findViewById(R.id.tvBMIList);

        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
            tvBMIList.setText(dataBase.bmiDao().getAllBMIs(user.getUid()).get(0).getDate());
        }



        return view;
    }
}