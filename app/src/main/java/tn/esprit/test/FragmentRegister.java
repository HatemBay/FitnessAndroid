package tn.esprit.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tn.esprit.test.database.AppDataBase;
import tn.esprit.test.entity.User;

public class FragmentRegister extends Fragment {

    EditText etUsername, etEmail, etPassword, etConfirm;
    Button btnRegister;
    String username, email, password, confirm;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AppDataBase db;

    @Override
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        db = AppDataBase.getAppDatabase(context);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        etEmail = view.findViewById(R.id.etEmail);
        etConfirm = view.findViewById(R.id.etConfirm);

//        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(view1 -> {
            Intent intent = new Intent(FragmentRegister.this.getActivity(), HomeActivity.class);

            username = etUsername.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString();
            confirm = etConfirm.getText().toString();

            if(TextUtils.isEmpty(username)){
                etUsername.setError("Username is required");
            } else if(TextUtils.isEmpty(email)){
                etEmail.setError("Email is required");
            } else if(TextUtils.isEmpty(password)){
                etPassword.setError("Password is required");
            } else if(TextUtils.isEmpty(confirm)){
                etConfirm.setError("Please confirm your password");
            } else if(!password.equals(confirm)){
                etPassword.setError("Your passwords don't match");
            } else {
                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("pass", password);
                editor.apply();
                User user = new User();
                user.setUsername(etUsername.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setPassword(etPassword.getText().toString());
                db.userDao().addUser(user);

                user = db.userDao().getUser(etUsername.getText().toString());

                intent.putExtra("user", user);
                Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        return view;
    }
}
