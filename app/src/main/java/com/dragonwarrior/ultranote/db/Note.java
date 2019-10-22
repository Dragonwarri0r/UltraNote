package com.dragonwarrior.ultranote.db;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Note extends DataSupport {
    private long id;
    private long pageId;
    private String noteTitle;
    private String noteBody;
    private int noteColor;
    private Date noteOutDate;

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    public Date getNoteOutDate() {
        return noteOutDate;
    }

    public void setNoteOutDate(Date noteOutDate) {
        this.noteOutDate = noteOutDate;
    }
}
