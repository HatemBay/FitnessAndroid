package tn.esprit.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.BMI;
import tn.esprit.test.entity.User;

public class BMIHistoryFragment extends Fragment {

    AppDataBase db;
    LineChart lcBMI;
    Button btnNext;
    User user;
    List<BMI> BMIs;

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
        db = AppDataBase.getAppDatabase(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b_m_i_history, container, false);

        setHasOptionsMenu(true);

        lcBMI = view.findViewById(R.id.lcBMI);
        btnNext = view.findViewById(R.id.btnNext);


        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }
        BMIs = db.bmiDao().getAllBMIs(user.getUid());
//        tvBMIList.setText(dataBase.bmiDao().getAllBMIs(user.getUid()).get(0).getDate());

        List<String> xAXES = new ArrayList<>();
        List<Entry> yAXES = new ArrayList<>();

        int i = 0;

        for (BMI bmi : BMIs) {
            float bmiFloat = Float.parseFloat(String.valueOf(bmi.getValue()));
            yAXES.add(new Entry(bmiFloat, i));
            xAXES.add(i, bmi.getDate());
            i++;
        }

        String[] xAxes = new String[xAXES.size()];

        for (int j = 0; j < xAXES.size(); j++) {
            xAxes[j] = xAXES.get(j);
        }

        List<ILineDataSet> lineDataSet = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(yAXES, "BMI");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setColor(Color.BLUE);

        lineDataSet.add(lineDataSet1);

        lcBMI.setData(new LineData(xAxes, lineDataSet));
        lcBMI.setVisibleXRangeMaximum(65f);
        lcBMI.setTouchEnabled(true);

//        lcBMI.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
//        {
//
//
//            @Override
//            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//                String x=e.g().toString();
//                System.out.println(x);
//            }
//
//
//            @Override
//            public void onNothingSelected()
//            {
//
//            }
//        });



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
            Intent intent = new Intent(BMIHistoryFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(BMIHistoryFragment.this.getActivity(), MainActivity.class);
            Toast.makeText(BMIHistoryFragment.this.getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}