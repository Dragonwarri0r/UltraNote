package com.dragonwarrior.ultranote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dragonwarrior.ultranote.db.Page;

public class SavePageNum extends AppCompatActivity {

    private long pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNum = this.getIntent().getExtras().getLong("Pagenum");
        //此Activity用于设置全局的当前的页面选择值
        MyApplication myApplication = (MyApplication)getApplication();
        myApplication.setPageNowId(pageNum);
        finish();
    }
}
