package com.dragonwarrior.ultranote.db;

import org.litepal.crud.DataSupport;

public class Page extends DataSupport {
    private long id;
    private String pageName;
    private int pageColor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getPageColor() {
        return pageColor;
    }

    public void setPageColor(int pageColor) {
        this.pageColor = pageColor;
    }
}
