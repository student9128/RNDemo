package com.rndemo;

import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextView;

/**
 * Created by Kevin on 2019-10-20<br/>
 * Describe:<br/>
 */
public class TextViewManager extends SimpleViewManager<TextView> {
    public static final String REACT_CLASS = "TextView";
    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TextView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TextView(reactContext);
    }

    @ReactProp(name ="color")
    public void setTextColor(TextView view,String color){
        view.setTextColor(Color.parseColor(color));

    }
    @ReactProp(name = "text")
    public void setText(TextView view,String text){
        view.setText(text);
    }
}
