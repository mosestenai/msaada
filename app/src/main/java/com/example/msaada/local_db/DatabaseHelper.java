package com.example.msaada.local_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.msaada.api.APIListener;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "database";
    static int version = 1;
    //create table if it does not exist
    String createTableUser = "CREATE TABLE if not exists 'contributions'" +
            " ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'heading' TEXT,"+
            "'description' TEXT, 'ref1' TEXT, 'ref2' TEXT,'phone1' TEXT,'phone2' TEXT,'amount' INTEGER," +
            "'amountt' INTEGER,'name' TEXT)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);

    }

    public long Getlocaldata(String name){
        String sql = "Select count(*) from contributions WHERE name='"+name+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);

        long response = statement.simpleQueryForLong();
        statement.close();

        return response;


    }

    public void insertContribution(ContentValues contentValues){
        getWritableDatabase().insert("contributions","",contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
