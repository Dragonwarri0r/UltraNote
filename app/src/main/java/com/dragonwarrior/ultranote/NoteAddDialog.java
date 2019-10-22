package com.dragonwarrior.ultranote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.dragonwarrior.ultranote.db.Note;
import com.dragonwarrior.ultranote.db.Page;

public class NoteAddDialog extends AppCompatActivity {

    private RadioButton radRed,radBlue,radGreen;
    private Button btnSure,btnCancel;
    private EditText noteTitle,noteBody;
    private int noteColor;
    private String noteName;
    private String noteB;
    private long pageId,noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add_dialog);

        MyApplication myApplication = (MyApplication)getApplication();
        pageId = myApplication.getPageNowId();

        radRed = findViewById(R.id.radio_btn_red);
        radGreen = findViewById(R.id.radio_btn_green);
        radBlue = findViewById(R.id.radio_btn_blue);

        noteTitle = findViewById(R.id.note_name);
        noteBody = findViewById(R.id.note_body);

        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSure = findViewById(R.id.btn_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteName = noteTitle.getText().toString();
                noteB = noteBody.getText().toString();
                if(radRed.isChecked()){
                    noteColor=R.color.Red;
                }else if(radGreen.isChecked()){
                    noteColor=R.color.Green;
                }else{
                    noteColor=R.color.Blue;
                }
                Note note = new Note();
                note.setPageId(pageId);
                note.setNoteTitle(noteName);
                note.setNoteColor(noteColor);
                note.setNoteBody(noteB);
                note.save();

                finish();


            }
        });



    }
}
