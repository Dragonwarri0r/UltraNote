package com.dragonwarrior.ultranote;

import android.content.Intent;
import android.os.Bundle;

import com.dragonwarrior.ultranote.adapter.PageAdapter;
import com.dragonwarrior.ultranote.db.Page;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPageAdd;
    private List<Page> pageList = new ArrayList<>();
    RecyclerView recyclerViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Page.class);
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

        initPages();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        initPages();
    }

    private void initPages(){
        pageList = DataSupport.findAll(Page.class);
        //Toast.makeText(MainActivity.this, pageList.get(0).getPageName(), Toast.LENGTH_SHORT).show();
        recyclerViewPage = findViewById(R.id.page_select);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewPage.setLayoutManager(layoutManager);
        PageAdapter adapter = new PageAdapter(pageList);
        recyclerViewPage.setAdapter(adapter);
    }


}
