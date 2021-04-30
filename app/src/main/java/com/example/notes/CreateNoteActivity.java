package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.data.DatabaseHelper;
import com.example.notes.model.Note;

public class CreateNoteActivity extends AppCompatActivity {

    EditText noteContentsEditText;
    Button saveNoteButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        setUp();
        setUpWidgets();

    }

    private void setUp()
    {
        databaseHelper = new DatabaseHelper(this);
    }

    private void setUpWidgets()
    {
        noteContentsEditText = findViewById(R.id.noteContentsEditText);
        saveNoteButton = findViewById(R.id.saveNoteButton);
    }

    public void saveNote(View view)
    {
        String noteContents = noteContentsEditText.getText().toString();

        if (noteContents.isEmpty())
        {
            Toast.makeText(CreateNoteActivity.this, "Cannot save empty note", Toast.LENGTH_LONG).show();
        }
        else
        {
            Note note = new Note(noteContents);
            long result = databaseHelper.insertNote(note);

            // If successful
            if (result > -1)
            {
                // Note added successfully
                Toast.makeText(CreateNoteActivity.this, "Success!", Toast.LENGTH_LONG).show();

                // End activity
                finish();
            }
            else
            {
                // Error
                Toast.makeText(CreateNoteActivity.this, "There was an error saving the note", Toast.LENGTH_LONG).show();
            }
        }
    }
}