package com.floratechno.testapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Intent mServiceIntent;
    private MyService mMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyService = new MyService();
        mServiceIntent = new Intent(this, mMyService.getClass());
        if (!isCheckServiceRunning(mMyService.getClass())) {
            startService(mServiceIntent);
        }
    }

    private boolean isCheckServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("Service status", "Running");
                return true;
            }
        }
        Log.i ("Service status", "Not running");
        return false;
    }

/* For stop service and backgoound notification, when app is closed */
//    @Override
//    protected void onDestroy() {
//        stopService(mServiceIntent);
//        super.onDestroy();
//    }

}