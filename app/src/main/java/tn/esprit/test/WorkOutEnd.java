package tn.esprit.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tn.esprit.test.entity.User;

public class WorkOutEnd extends AppCompatActivity {

    private Button btNotification;
    Button Button;
    Button Calender;
    Button Notes;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        user = (User) getIntent().getSerializableExtra("user");

        Button = findViewById(R.id.button);
        Calender = findViewById(R.id.Calender);
        Notes = findViewById(R.id.Notes);

        Button.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    ChooseActivity.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }

        });

        Calender.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    MainCalendarActivity.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        Notes.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    NoteActivity.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }

        });


    createNotificationChannel();

    btNotification = findViewById(R.id.bt_notification);

        btNotification.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String message = "make sure you exercise regularly ";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    WorkOutEnd.this, "100"
            )
                    .setSmallIcon(R.drawable.notification)
                    .setContentTitle("Esprit Fitness")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            Intent intent = new Intent(WorkOutEnd.this,
                    ChooseActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getActivity(WorkOutEnd.this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE
            );
            notificationManager.notify(0, builder.build());
        }
    });

}
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("100", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Intent logoutIntent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}