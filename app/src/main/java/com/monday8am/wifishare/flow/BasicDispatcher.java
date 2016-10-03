package com.monday8am.wifishare.flow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monday8am.wifishare.R;

import flow.Dispatcher;
import flow.Flow;
import flow.Traversal;
import flow.TraversalCallback;

/**
 * Created by anton on 03/10/16.
 */

public class BasicDispatcher implements Dispatcher {

    private final Activity activity;

    public BasicDispatcher(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Log.d ("Basic dispatching", "dispatching " + traversal);
        Object destKey = traversal.destination.top();

        ViewGroup frame = (ViewGroup) activity.findViewById(R.id.activity_main);

        // We're already showing something, clean it up.
        if (frame.getChildCount() > 0)
        {
            final View currentView = frame.getChildAt(0);

            // Save the outgoing view state.
            if (traversal.origin != null) {
                traversal.getState(traversal.origin.top()).save(currentView);
            }

            // Short circuit if we would just be showing the same view again.
            final Object currentKey = Flow.getKey(currentView);
            if (destKey.equals(currentKey)){
                callback.onTraversalCompleted();
                return;
            }

            frame.removeAllViews();
        }

        // Select layout based on key.
        @LayoutRes final int layout;
        if (destKey instanceof WifiScreen) {
            layout = R.layout.content_wifilist;
        } else if (destKey instanceof  ShareScreen) {
            layout = R.layout.content_share;
        } else {
            throw new AssertionError("Unrecognized screen " + destKey);
        }

        // Inflate, save state and add new view.
        View inconmingView = LayoutInflater.from(traversal.createContext(destKey, activity))
                .inflate(layout, frame, false);
        frame.addView(inconmingView);
        traversal.getState(traversal.destination.top()).restore(inconmingView);
        callback.onTraversalCompleted();
    }
}
