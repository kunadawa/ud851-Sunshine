package com.example.android.sunshine.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

// DO (5) Create a new class called SunshineSyncIntentService that extends IntentService
//  DO (6) Create a constructor that calls super and passes the name of this class
//  DO (7) Override onHandleIntent, and within it, call SunshineSyncTask.syncWeather
public class SunshineSyncIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SunshineSyncIntentService() {
        super(SunshineSyncIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SunshineSyncTask.syncWeather(getApplicationContext());
    }
}