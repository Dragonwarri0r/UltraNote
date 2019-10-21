package com.dragonwarrior.ultranote;

import android.app.Application;

public class MyApplication extends Application {
    private long pageNowId;

    public long getPageNowId() {
        return pageNowId;
    }

    public void setPageNowId(long pageNowId) {
        this.pageNowId = pageNowId;
    }
}
