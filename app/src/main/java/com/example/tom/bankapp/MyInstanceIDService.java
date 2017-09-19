package com.example.tom.bankapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by TOM on 2017/9/18.
 */

public class MyInstanceIDService extends FirebaseInstanceIdService {
    SQLiteDatabase db;

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("FCM", "Token:"+token);
        Application.FCMToken = token;
        setToken(token);

    }
    private void setToken(String Token){
        MyDBhelper myDB3 = new MyDBhelper(this,"tblTable",null,1);
        db=myDB3.getWritableDatabase();
        db.delete("tblTable", null, null);
        ContentValues addbase = new ContentValues();
        addbase.put("cToken",Token);
        db.insert("tblTable",null,addbase);
        db.close();

    }
}
