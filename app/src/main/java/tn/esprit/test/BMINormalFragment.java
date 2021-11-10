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

public class BMINormalFragment extends Fragment {
    TextView tvText;
    User user;

    public static BMINormalFragment newInstance(User user) {
        BMINormalFragment fragment = new BMINormalFragment();
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
        View view = inflater.inflate(R.layout.fragment_b_m_i_normal, container, false);

        setHasOptionsMenu(true);

        tvText = view.findViewById(R.id.tvText);

        tvText.setText("+Eat at least 5 portions of a variety of fruit and vegetables every day (see 5 A Day)\n" +
                "+Base meals on higher fibre starchy foods like potatoes, bread, rice or pasta\n" +
                "+Have some dairy or dairy alternatives (such as soya drinks)\n" +
                "+Eat some beans, pulses, fish, eggs, meat and other protein\n" +
                "+Choose unsaturated oils and spreads, and eat them in small amounts\n" +
                "+Drink plenty of fluids (at least 6 to 8 glasses a day)\n" +
                "If you're having foods and drinks that are high in fat, salt and sugar, have these less often and in small amounts.\n" +
                "\n" +
                "Try to choose a variety of different foods from the 5 main food groups to get a wide range of nutrients.\n" +
                "\n" +
                "Most people in the UK eat and drink too many calories, too much saturated fat, sugar and salt, and not enough fruit, vegetables, oily fish or fibre.\n" +
                "\n" +
                "The Eatwell Guide does not apply to children under the age of 2 because they have different nutritional needs.\n" +
                "\n" +
                "Between the ages of 2 and 5 years, children should gradually move to eating the same foods as the rest of the family in the proportions shown in the Eatwell Guide.");
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
            Intent intent = new Intent(BMINormalFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(BMINormalFragment.this.getActivity(), MainActivity.class);
            Toast.makeText(BMINormalFragment.this.getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}