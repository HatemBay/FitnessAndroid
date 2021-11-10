package tn.esprit.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_i_history, container, false);
        lcBMI = view.findViewById(R.id.lcBMI);


        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }
        BMIs = db.bmiDao().getAllBMIs(user.getUid());
//        tvBMIList.setText(dataBase.bmiDao().getAllBMIs(user.getUid()).get(0).getDate());

        List<String> xAXES = new ArrayList<>();
        List<Entry> yAXES = new ArrayList<>();
        List<Entry> yAxesCos = new ArrayList<>();

        double x = 0;
        int i = 0;

        for (BMI bmi:BMIs) {
            float bmiFloat = Float.parseFloat(String.valueOf(bmi.getValue()));
            yAXES.add(new Entry(bmiFloat, i));
            xAXES.add(i, bmi.getDate());
            i++;
        }

        String[] xaxes = new String[xAXES.size()];

        for (int j=0; j<xAXES.size(); j++) {
            xaxes[j] = xAXES.get(j).toString();
        }

        List<ILineDataSet> lineDataSet = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(yAXES, "BMI");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        lineDataSet.add(lineDataSet1);

        lcBMI.setData(new LineData(xaxes, lineDataSet));
        lcBMI.setVisibleXRangeMaximum(65f);



        return view;
    }
}