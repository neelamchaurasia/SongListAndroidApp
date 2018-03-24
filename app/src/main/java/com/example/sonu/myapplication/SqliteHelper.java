package com.example.sonu.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonu on 24-03-2018.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public static final int DATABSE_VERSION=1;
    public static final String DATABASE_NAME= "song.db";
    public String Create_Table="CREATE TABLE "+Constants.SONG_NAME_TABLE+"("+ Constants.SONG_NAME+" VARCHAR , "
            + Constants.ARTIST_NAME+" VARCHAR , "+Constants.COVER_IMAGEe+" VARCHAR )";
    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( Create_Table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void insertSong(Song song){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.SONG_NAME_TABLE + " WHERE " + Constants.SONG_NAME + " = '" + song.getSong() + "'", null);

        if(cursor.getCount()==0){

            ContentValues values = new ContentValues();
            values.put(Constants.SONG_NAME,song.getSong());
            values.put(Constants.ARTIST_NAME,song.getArtist());


            db.insert(Constants.SONG_NAME_TABLE,null,values);
        }

        cursor.close();
    }


    public List<Song> getAllSongs(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.SONG_NAME_TABLE ,null);
        List<Song> songList= new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Song song= new Song();
            song.setSong(cursor.getString(cursor.getColumnIndex(Constants.SONG_NAME)));
            song.setArtist(cursor.getString(cursor.getColumnIndex(Constants.ARTIST_NAME)));
            songList.add(song);
        }
        cursor.close();
        return songList;
    }







}
