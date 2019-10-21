package com.rndemo;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

/**
 * Created by Kevin on 2019-10-20<br/>
 * Describe:<br/>
 */
public class TextViewManager extends SimpleViewManager<TextView> {
    public static final String REACT_CLASS = "TextView";
    public static final String CLICK_EVENT = "tv_click_event";
    public static final int SET_COLOR_ID = 1;
    public static final String SET_COLOR_NAME = "set_text_color";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TextView createViewInstance(@NonNull ThemedReactContext reactContext) {
        TextView textView = new TextView(reactContext);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WritableMap map = Arguments.createMap();
//                map.putString("message","you clicked me");
//                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(textView.getId(),CLICK_EVENT,map);
//            }
//        });
        return textView;
    }

    @ReactProp(name = "color")
    public void setTextColor(TextView view, String color) {
        view.setTextColor(Color.parseColor(color));

    }

    @ReactProp(name = "text")
    public void setText(TextView view, String text) {
        view.setText(text);
    }

//    @Nullable
//    @Override
//    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
//        return MapBuilder.<String, Object>builder()
//                .put(CLICK_EVENT, MapBuilder.of("phasedRegistrationNames",
//                        MapBuilder.of("bubbled", "onClick"))).build();
//    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put(CLICK_EVENT, MapBuilder.of("registrationName", "onClick")).build();
    }


     // 不写改方法的时候就直接在 createViewInstance 添加点击事件，效果是一样的
    @Override
    protected void addEventEmitters(@NonNull ThemedReactContext reactContext, @NonNull TextView view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritableMap map = Arguments.createMap();
                map.putString("message", "okay,you clicked me with addEventEmitters");
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(), CLICK_EVENT, map);
            }
        });
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(SET_COLOR_NAME, SET_COLOR_ID);
    }

   // 这里一个情况就是，getCommandsMap接收的 id 是Integer类型，receiveCommand返回的 id 是String类型
    @Override
    public void receiveCommand(@NonNull TextView root, String commandId, @Nullable ReadableArray args) {
        switch (Integer.valueOf(commandId)) {
            case SET_COLOR_ID:
                if (args != null) {
                    String color = args.getString(0);
                    root.setTextColor(Color.parseColor(color));
                }
                break;
        }
    }
}
