package com.jwhh.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private NoteRecycleAdapter recycleAdapter;

    //private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));

            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mAdapterNotes.notifyDataSetChanged();
        recycleAdapter.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
//        final ListView listNotes = findViewById(R.id.list_notes);
//
//        List<NoteInfo> notes = DataManager.getInstance().getNotes();
//        mAdapterNotes = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, notes);
//
//        listNotes.setAdapter(mAdapterNotes);
//
//        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
////                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
//                intent.putExtra(NoteActivity.NOTE_POSITION, position);
//                startActivity(intent);
//            }
//        });

        final RecyclerView recylerViewNotes = (RecyclerView) findViewById(R.id.list_notes);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recylerViewNotes.setLayoutManager(layoutManager);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();

//        for(int index = 0; index < 2000; index++) {
//            NoteInfo note = new NoteInfo(DataManager.getInstance().getCourse("android_async"), "title " + String.valueOf(index), "text "+ String.valueOf(index));
//            notes.add(note);
//        }

        recycleAdapter = new NoteRecycleAdapter(this, notes);
        recylerViewNotes.setAdapter(recycleAdapter);

    }
}



