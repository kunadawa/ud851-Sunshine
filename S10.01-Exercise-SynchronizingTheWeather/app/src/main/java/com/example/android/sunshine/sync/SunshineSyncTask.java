package com.example.android.sunshine.sync;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class SunshineSyncTask {
//  DO (1) Create a class called SunshineSyncTask
//  DO (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    private static String tag = SunshineSyncTask.class.getSimpleName();
    synchronized public static void syncWeather (Context context) {
//      DO (3) Within syncWeather, fetch new weather data
        URL weatherRequestUrl = NetworkUtils.getUrl(context);
        ContentValues[] contentValues = null;
        try {
            String jsonWeatherResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherRequestUrl);
            contentValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeatherResponse);

        } catch (Exception e) {
            Log.e(tag, e.getMessage());
        }
        if (contentValues != null) {
            int result = context.getContentResolver().delete(WeatherContract.WeatherEntry.CONTENT_URI, null, null);
            Log.d(tag, "deleted rows: " + result);
            Uri bulkInsert = WeatherContract.WeatherEntry.CONTENT_URI;
            result = context.getContentResolver().bulkInsert(bulkInsert, contentValues);
            Log.d(tag, "inserted rows: " + result);
        }
//      DO (4) If we have valid results, delete the old data and insert the new
    }
}