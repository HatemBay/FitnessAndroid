package tn.esprit.test;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView clickableTv;


    public LoginActivity(TextView clickableTv) {
        this.clickableTv = clickableTv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        clickableTv = findViewById(R.id.clickableTv);
        clickableTv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
