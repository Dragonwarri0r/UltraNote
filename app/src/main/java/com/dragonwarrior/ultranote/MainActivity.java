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

    Button btnPageAdd; //添加页面的按钮
    private List<Page> pageList = new ArrayList<>(); //存储Page的list
    RecyclerView recyclerViewPage; //RecycleView
    MyApplication myApplication; //全局变量
    PageAdapter adapter; //Page的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //取得全局变量，并且将全局变量设置为上次选择的Page
        myApplication = (MyApplication)getApplication();
        int mode = Activity.MODE_PRIVATE;
        //从SP里面取到存储的变量
        SharedPreferences pageMessage = getSharedPreferences("file",mode);
        myApplication.setPageNowId(pageMessage.getLong("pageNum",0));


        super.onCreate(savedInstanceState);
        //加载Litpal初始化
        LitePalApplication.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);


        //这个是MetalDesign的fab，添加一个Note的按钮
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteAddDialog.class);
                startActivity(intent);
            }
        });

        //这个是调试时的一键删除所有的内容的按钮，但想了想决定保留下来。
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

        //添加Page的按钮
        btnPageAdd = findViewById(R.id.btn_page_add);
        btnPageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PageAddDialog.class);
                startActivity(intent);
            }
        });

        //加载页面
        initPages();

    }

    @Override
    protected void onResume(){
        super.onResume();
        //在失去焦点之后重新加载页面，例如添加一个page或note之后
        initPages();

    }

    @Override
    protected void  onStop(){
        super.onStop();

        //停止Activity时保存当前选择的页面到SP里面
        long id = myApplication.getPageNowId();
        SharedPreferences pagSetting = getSharedPreferences("file", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pagSetting.edit();
        editor.putLong("pageNum",id);
        editor.commit();
    }

    public void initPages(){

        //加载页面的list
        pageList = DataSupport.findAll(Page.class);
        //Toast.makeText(MainActivity.this, pageList.get(0).getPageName(), Toast.LENGTH_SHORT).show();
        recyclerViewPage = findViewById(R.id.page_select);
        //使用横向布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewPage.setLayoutManager(layoutManager);
        adapter = new PageAdapter(pageList);
        recyclerViewPage.setAdapter(adapter);

        //note碎片文件
        NoteFragment noteFragment = new NoteFragment();
        //使用碎片管理器替换页面的碎片
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.choose_area_fragment,noteFragment);
        fragmentTransaction.commit();
    }


}
