package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_date,editText_note;
    Button button_add, button_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_date = findViewById(R.id.edittext_name);
        editText_note = findViewById(R.id.edittext_email);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringDate = editText_date.getText().toString();
                String stringNote = editText_note.getText().toString();

                if (stringDate.length() <= 0 || stringNote.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Enter All required field!", Toast.LENGTH_SHORT).show();
                } else {
                   DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                   NoteClass noteClass = new NoteClass(stringDate,stringNote);
                   databaseHelper.addNote(noteClass);
                    Toast.makeText(MainActivity.this, "Note Added Successfully!", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewNote.class);
                startActivity(intent);
            }
        });
    }
}