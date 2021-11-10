package tn.esprit.test;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;


public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + tn.esprit.test.CalendarUtils.formattedDate(tn.esprit.test.CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + tn.esprit.test.CalendarUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        tn.esprit.test.Event newEvent = new tn.esprit.test.Event(eventName, tn.esprit.test.CalendarUtils.selectedDate, time);
        tn.esprit.test.Event.eventsList.add(newEvent);
        finish();
    }


}