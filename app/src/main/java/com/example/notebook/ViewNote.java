package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ViewNote extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

         recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<NoteClass> noteClasses = databaseHelper.getNoteList();

        if(noteClasses.size()>0){
            NoteAdapterClass noteAdapterClass = new NoteAdapterClass(noteClasses,ViewNote.this);
            recyclerView.setAdapter(noteAdapterClass);
        }
        else {
            Toast.makeText(this, "Note List empty!", Toast.LENGTH_SHORT).show();
        }

    }
}