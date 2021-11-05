package tn.esprit.test;

import android.content.Context;
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

public class FragmentRegister extends Fragment {

    EditText etUsername, etEmail, etPassword, etConfirm;
    Button btnLogin, btnRegister;
    String username, email, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        etEmail = view.findViewById(R.id.etEmail);
        etConfirm = view.findViewById(R.id.etConfirm);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(view1 -> {
            username = etUsername.getText().toString();
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();

            editor.putString("username", username);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();
            Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();
        });

        btnRegister.setOnClickListener(view1 -> {

        });

        return view;
    }
}
