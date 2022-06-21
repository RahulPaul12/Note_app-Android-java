package com.example.notebook;

public class NoteClass {

    Integer id;
    String date;
    String note;

    public NoteClass(String date, String note) {
        this.date = date;
        this.note = note;
    }

    public NoteClass(Integer id, String date, String note) {
        this.id = id;
        this.date = date;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
