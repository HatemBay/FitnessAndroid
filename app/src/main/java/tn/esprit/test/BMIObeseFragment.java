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

public class BMIObeseFragment extends Fragment {

    TextView tvText,tvHl;
    User user;

    public static BMIObeseFragment newInstance(User user) {
        BMIObeseFragment fragment = new BMIObeseFragment();
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
        View view = inflater.inflate(R.layout.fragment_b_m_i_obese, container, false);

        setHasOptionsMenu(true);

        tvText = view.findViewById(R.id.tvText);
        tvHl = view.findViewById(R.id.tvHl);

        tvText.setText("+Eat a balanced calorie-controlled diet as recommended by a GP or weight loss management health professional (such as a dietitian)\n" +
                "+Join a local weight loss group\n" +
                "+Take up activities such as fast walking, jogging, swimming or tennis for 150 to 300 minutes (2.5 to 5 hours) a week\n" +
                "+Eat slowly and avoid situations where you know you could be tempted to overeat\n" +
                "You may also benefit from receiving psychological support from a trained healthcare professional to help change the way you think about food and eating.\n" +
                "\n" +
                "If lifestyle changes alone do not help you lose weight, a medicine called orlistat may be recommended.\n" +
                "\n" +
                "If taken correctly, this medicine works by reducing the amount of fat you absorb during digestion. Your GP will know whether orlistat is suitable for you.\n" +
                "\n" +
                "In some cases, weight loss surgery may be recommended.");

        tvHl.setText("The best way to treat obesity is to eat a healthy reduced-calorie diet and exercise regularly.\n" +
                "\n" +
                "To do this, you should:");

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
            Intent intent = new Intent(BMIObeseFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(BMIObeseFragment.this.getActivity(), MainActivity.class);
            Toast.makeText(BMIObeseFragment.this.getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}