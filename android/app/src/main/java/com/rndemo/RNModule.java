package com.rndemo;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2019-10-06<br/>
 * Describe:<br/>
 */
public class RNModule extends ReactContextBaseJavaModule {
    private static final String DURAION_SHORT_KEY = "SHORT";
    private static final String DURAION_LONG_KEY = "LONG";
    public RNModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "RNModule";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURAION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURAION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void showToast(String msg,int duration) {
        Toast.makeText(getReactApplicationContext(), msg,duration).show();
    }
    @ReactMethod
    public void showBoolean(String msg, Callback callback){
        if (msg.equals("1")||msg.equals("true")){
            callback.invoke(true);
        }else {
            callback.invoke(false);
        }
    }
}
