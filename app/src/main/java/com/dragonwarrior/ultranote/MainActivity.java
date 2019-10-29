package com.dragonwarrior.ultranote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.dragonwarrior.ultranote.adapter.PageAdapter;
import com.dragonwarrior.ultranote.db.Note;
import com.dragonwarrior.ultranote.db.Page;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.util.Const;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPageAdd;
    private List<Page> pageList = new ArrayList<>();
    RecyclerView recyclerViewPage;
    MyApplication myApplication;
    PageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myApplication = (MyApplication)getApplication();
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences pageMessage = getSharedPreferences("file",mode);
        myApplication.setPageNowId(pageMessage.getLong("pageNum",0));


        super.onCreate(savedInstanceState);
        LitePalApplication.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteAddDialog.class);
                startActivity(intent);
            }
        });

        FloatingActionButton deleteAll = findViewById(R.id.delete_all);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Page.class);
                DataSupport.deleteAll(Note.class);
                Snackbar.make(view, "一键自毁成功", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnPageAdd = findViewById(R.id.btn_page_add);
        btnPageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PageAddDialog.class);
                startActivity(intent);
            }
        });

        NoteFragment noteFragment = new NoteFragment();

        initPages();

    }

    @Override
    protected void onResume(){
        super.onResume();
        initPages();

    }

    @Override
    protected void  onStop(){
        super.onStop();
        long id = myApplication.getPageNowId();
        SharedPreferences pagSetting = getSharedPreferences("file", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pagSetting.edit();
        editor.putLong("pageNum",id);
        editor.commit();
    }

    public void initPages(){
        pageList = DataSupport.findAll(Page.class);
        //Toast.makeText(MainActivity.this, pageList.get(0).getPageName(), Toast.LENGTH_SHORT).show();
        recyclerViewPage = findViewById(R.id.page_select);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewPage.setLayoutManager(layoutManager);
        adapter = new PageAdapter(pageList);
        recyclerViewPage.setAdapter(adapter);

        NoteFragment noteFragment = new NoteFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.choose_area_fragment,noteFragment);
        fragmentTransaction.commit();
    }


}
