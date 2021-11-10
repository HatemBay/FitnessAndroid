package tn.esprit.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity
{
    private ListView noteListView;
    Button NoteBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();

        NoteBack = findViewById(R.id.NoteBack);

        NoteBack.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    WorkOutEnd.class);


            startActivity(intent);



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
                startActivity(editNoteIntent);
            }
        });
    }


    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(this, tn.esprit.test.NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
    }
}