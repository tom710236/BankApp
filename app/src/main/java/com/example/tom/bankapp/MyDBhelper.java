package com.example.tom.bankapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOM on 2017/9/19.
 */

public class MyDBhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;	//資料庫版本
    public MyDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String create =
                ("CREATE TABLE tblTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "cToken TEXT);");
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblTable");	//刪除舊有的資料表
        //db.execSQL("ALTER TABLE tblTask ADD COLUMN cCash VARCHAR");
        onCreate(db);
    }
}
