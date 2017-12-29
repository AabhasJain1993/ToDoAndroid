package com.express.todoandroidapp;

import android.app.Application;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.amitshekhar.DebugDB;
import com.express.todoandroidapp.db.AppDataBase;
import com.express.todoandroidapp.db.DatabaseManager;

/**
 * Created by root on 28/12/17.
 */

public class ToDoAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        DatabaseManager.initializeInstance(AppDataBase.getInstance(getApplicationContext()));
       // Log.d("Debug Database Address" ,DebugDB.getAddressLog());


    }
}
