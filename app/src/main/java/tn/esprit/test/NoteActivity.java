package tn.esprit.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.test.entity.User;

public class NoteActivity extends AppCompatActivity
{
    private ListView noteListView;
    Button NoteBack;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);

        user = (User) getIntent().getSerializableExtra("user");
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();

        NoteBack = findViewById(R.id.NoteBack);

        NoteBack.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    WorkOutEnd.class);
            if (getIntent().getSerializableExtra("user") != null) {
                intent.putExtra("user", user);
                startActivity(intent);
            }

        });


    }


    private void initWidgets()
    {
        noteListView = findViewById(R.id.noteListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter()
    {
        tn.esprit.test.NoteAdapter noteAdapter = new tn.esprit.test.NoteAdapter(getApplicationContext(), tn.esprit.test.Note.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }


    private void setOnClickListener()
    {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                tn.esprit.test.Note selectedNote = (tn.esprit.test.Note) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), tn.esprit.test.NoteDetailActivity.class);
                editNoteIntent.putExtra(tn.esprit.test.Note.NOTE_EDIT_EXTRA, selectedNote.getId());
                if (getIntent().getSerializableExtra("user") != null) {
                    editNoteIntent.putExtra("user", user);
                    startActivity(editNoteIntent);
                }
            }
        });
    }


    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(this, tn.esprit.test.NoteDetailActivity.class);
        if (getIntent().getSerializableExtra("user") != null) {
            newNoteIntent.putExtra("user", user);
            startActivity(newNoteIntent);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
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