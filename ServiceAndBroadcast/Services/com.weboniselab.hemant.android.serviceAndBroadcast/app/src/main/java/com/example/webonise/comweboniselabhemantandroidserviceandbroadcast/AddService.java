package com.example.webonise.comweboniselabhemantandroidserviceandbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class AddService extends Service {
    public AddService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("1-", "onCreate of service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("1-", "onStartCommand");
        Bundle bundleStore = new Bundle();
        bundleStore= intent.getExtras();
        int  input = bundleStore.getInt("first");
        Log.w("1-", getString(R.string.displaySum)+sumTill(input));
        return super.onStartCommand(intent, flags, startId);
    }

    private int sumTill(int n) {
        int result;

        if(n==1)
            return 1;
        result = sumTill(n - 1) + n;
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("1-", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w("1-", "onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
