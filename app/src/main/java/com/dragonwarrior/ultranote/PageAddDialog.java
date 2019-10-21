package com.dragonwarrior.ultranote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dragonwarrior.ultranote.db.Page;

public class PageAddDialog extends AppCompatActivity {

    private RadioButton radRed,radBlue,radGreen;
    private Button btnSure,btnCancel;
    private EditText editText;
    private int pageColor;
    private String pageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_add_dialog);

        radRed = findViewById(R.id.radio_btn_red);
        radGreen = findViewById(R.id.radio_btn_green);
        radBlue = findViewById(R.id.radio_btn_blue);

        editText = findViewById(R.id.page_name);

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
                pageName = editText.getText().toString();
                if(radRed.isChecked()){
                    pageColor=R.color.Red;
                }else if(radGreen.isChecked()){
                    pageColor=R.color.Green;
                }else{
                    pageColor=R.color.Blue;
                }
                Page page = new Page();
                page.setPageColor(pageColor);
                page.setPageName(pageName);
                page.save();
                finish();
            }
        });






    }
}
