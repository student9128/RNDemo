package com.rndemo;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

/**
 * Created by Kevin on 2019-10-28<br/>
 * Describe:<br/>
 */
public class AlbumLayout extends ViewGroupManager<AlbumView> {
    public static final String REACT_CLASS = "Album";
    public static final String CLICK_EVENT = "AlbumLayout_click_event";

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
//        albumView.setOnPhotoItemClickListener(new AlbumView.PhotoItemClickListener() {
//            @Override
//            public void photoItemClick(AlbumEntity albumEntity) {
//                Log.d("AlbumLayout", albumEntity.toString());
//            }
//
//            @Override
//            public void photoItemChecked(AlbumEntity albumEntity) {
//
//            }
//        });
        return albumView;
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
                return MapBuilder.<String, Object>builder()
                .put(CLICK_EVENT, MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onItemClick"))).build();
    }

    @Override
    protected void addEventEmitters(@NonNull ThemedReactContext reactContext, @NonNull AlbumView view) {
        view.setOnPhotoItemClickListener(new AlbumView.PhotoItemClickListener() {
            @Override
            public void photoItemClick(AlbumEntity albumEntity) {
//                WritableMap map = Arguments.createMap();
//                map.putString("path",albumEntity.url);
//                map.putString("size",albumEntity.size);
//                map.putString("mimeType", albumEntity.mimeType);
//                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(),CLICK_EVENT,map);
            }

            @Override
            public void photoItemChecked(AlbumEntity albumEntity) {
                WritableMap map = Arguments.createMap();
                map.putString("path",albumEntity.url);
                map.putString("size",albumEntity.size);
                map.putString("mimeType", albumEntity.mimeType);
                map.putInt("isChecked", albumEntity.isSelect);
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(),CLICK_EVENT,map);
            }
        });
    }
}
