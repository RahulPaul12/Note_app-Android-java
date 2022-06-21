package com.example.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "note_database";
    //Database Table name
    private static final String TABLE_NAME = "NOTE_DATA";
    //Table columns
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String NOTE = "note";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT NOT NULL,"+NOTE+" TEXT NOT NULL);";

    public DatabaseHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addNote(NoteClass noteClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DATE, noteClass.getDate());
        contentValues.put(DatabaseHelper.NOTE,noteClass.getNote());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null,contentValues);
    }

    public List<NoteClass> getNoteList(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<NoteClass> storeNote = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String date = cursor.getString(1);
                String note = cursor.getString(2);
                storeNote.add(new NoteClass(id,date,note));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeNote;
    }

    public  void  updateNote(NoteClass noteClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DATE,noteClass.getDate());
        contentValues.put(DatabaseHelper.NOTE, noteClass.getNote());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?", new  String[]
                {String.valueOf(noteClass.getId())});


    }

    public void deleteNote(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,ID+" = ?", new String[]{
              String.valueOf(id)});
    }

}
