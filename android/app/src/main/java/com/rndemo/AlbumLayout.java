package com.rndemo;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/**
 * Created by Kevin on 2019-10-28<br/>
 * Describe:<br/>
 */
public class AlbumLayout extends ViewGroupManager<AlbumView> {
    public static final String REACT_CLASS = "Album";
    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected AlbumView createViewInstance(@NonNull ThemedReactContext reactContext) {
        FragmentActivity currentActivity = (FragmentActivity) reactContext.getCurrentActivity();
        AlbumView albumView = new AlbumView(reactContext, currentActivity.getSupportFragmentManager());
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(300, 500);
        albumView.setLayoutParams(ll);
        return albumView;
    }

}
