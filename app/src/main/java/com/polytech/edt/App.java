package com.polytech.edt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.polytech.edt.exceptions.CustomExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @SuppressLint("StaticFieldLeak")
    public static Activity currentActivity;
    public static List<String> activityHistory = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);

        // Store context
        context = this;

        // Define exception handler
        Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        currentActivity = activity;
        logHistory(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
        logHistory(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
        logHistory(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * Method to log activity history
     *
     * @param activity Activity
     */
    private void logHistory(Activity activity) {
        if (activityHistory.isEmpty() || !activityHistory.get(activityHistory.size() - 1).equals(activity.getClass().getName())) {
            activityHistory.add(activity.getClass().getName());
        }
    }
}
