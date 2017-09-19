package com.example.tom.bankapp;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String token = "null";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //啟動service 讓7.0的手機可以常駐後台
        Intent it = new Intent(MainActivity.this,Delay.class);
        startService(it);

        getToken();
        Log.e("TOKEN",Application.FCMToken);
        //跳出dialog
        MyNotice.getInstance().setOnMessageReceivedListener(new MyNotice.OnMessageReceivedListener() {
            @Override
            public void onMessageReceived(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog(s);
                    }
                });
            }
        });
    }

    private void mDialog(String message) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("您有一則新訊息");
        alertDlg.setCancelable(false);
        alertDlg.setMessage(message);
        alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.show();

    }
    //假設收到雲端通知時APP是在裝置的背景，此時不會在onMessageReceived中收到通知，Android系統會自動將收到的通知顯示在裝置上方，下拉可顯示更多資訊
    //目前好像沒用
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        String msg = bundle.getString("msg");
        if (msg!=null){
            Log.e("FCM", "msg:"+msg);
        }

    }
    private String getToken(){
        MyDBhelper MyDB = new MyDBhelper(this,"tblTable",null,1);
        db=MyDB.getWritableDatabase();
        //Cursor c=db2.rawQuery("SELECT * FROM "+"tblTable2", null);   //查詢全部欄位
        Cursor c = db.query("tblTable",                          // 資料表名字
                null,                                              // 要取出的欄位資料
                null,                                              // 查詢條件式(WHERE)
                null,                                              // 查詢條件值字串陣列(若查詢條件式有問號 對應其問號的值)
                null,                                              // Group By字串語法
                null,                                              // Having字串法
                null);                                             // Order By字串語法(排序)
        //往下一個 收尋
        while(c.moveToNext()) {
            token = c.getString(c.getColumnIndex("cToken"));
        }
        Application.FCMToken = token;
        return token;
    }
    private void unLock() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //获取电源管理器对象
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "bright");
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        wl.acquire();
        //点亮屏幕
        wl.release();
        //释放
        // 處理 bData 內含的訊息
        // 在本例中, 我的 server 端程式 gcm_send.php 傳來了 message, campaigndate, title, description 四項資料
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        //得到鍵盤鎖管理器對象
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //參數是LogCat裡用的Tag
        kl.disableKeyguard();
        //解鎖

        //收到推播會震動
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(1500);

    }
}
