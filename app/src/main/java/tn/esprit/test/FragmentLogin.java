package tn.esprit.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.User;

public class FragmentLogin extends Fragment {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    CallbackFragment callbackFragment;
    String username, password;
    AppDataBase dataBase;

    @Override
    public void onAttach(@NonNull Context context) {
        dataBase = AppDataBase.getAppDatabase(context);

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(FragmentLogin.this.getActivity(), HomeActivity.class);
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();

            List<User> users = dataBase.userDao().getAll();

            for (User user:users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    intent.putExtra("user", user);
                    Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
//            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

//            if (username.equals(uName) && password.equals(uPass)) {
//                Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            } else {
//                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//            }
        });

        btnRegister.setOnClickListener(view1 -> {
            if (callbackFragment != null) {
                callbackFragment.changeFragment();
            }
        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }

}
