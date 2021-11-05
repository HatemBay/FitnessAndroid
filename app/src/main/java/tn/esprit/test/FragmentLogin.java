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

public class FragmentLogin extends Fragment {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    CallbackFragment callbackFragment;
    String username, password;
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

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(view1 -> {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();

            String uName, uPass;
            uName = sharedPreferences.getString("userName", null);
            uPass = sharedPreferences.getString("pass", null);

            if(username.equals(uName) && password.equals(uPass)){
                Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(view1 -> {
            if (callbackFragment != null){
                callbackFragment.changeFragment();
            }
        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

}
