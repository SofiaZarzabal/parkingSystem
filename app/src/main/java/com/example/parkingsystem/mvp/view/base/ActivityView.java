package com.example.parkingsystem.mvp.view.base;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import java.lang.ref.WeakReference;

public class ActivityView {
    private WeakReference<Activity> activityRef;

    public ActivityView(Activity activity) {
        activityRef = new WeakReference<>(activity);
    }

    @Nullable
    public Activity getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

    @Nullable
    public FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        return (activity != null) ? ((AppCompatActivity) activity).getSupportFragmentManager() : null;
    }
}
