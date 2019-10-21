package com.dragonwarrior.ultranote.db;

import org.litepal.crud.DataSupport;

public class PageItem extends DataSupport {
    private long pageId;
    private long noteId;

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
