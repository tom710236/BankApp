package com.example.tom.bankapp;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by TOM on 2017/9/18.
 */

public class Delay extends Service {

    private final static int GOHNSON_ID = 1000;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //灰色喚醒
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GOHNSON_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, GohnsonInnerService.class);
            startService(innerIntent);
            startForeground(GOHNSON_ID, new Notification());
        }

        return super.onStartCommand(intent, flags, startId);


    }
    public static class GohnsonInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GOHNSON_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }


}
