package com.rndemo;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2019-10-06<br/>
 * Describe:<br/>
 */
public class RNModule extends ReactContextBaseJavaModule {
    private static final String DURAION_SHORT_KEY = "SHORT";
    private static final String DURAION_LONG_KEY = "LONG";
    private ReactContext reactContext;
    private Promise promise;

    public RNModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(activityEventListener);
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
    public void showToast(String msg, int duration) {
        Toast.makeText(getReactApplicationContext(), msg, duration).show();
    }

    @ReactMethod
    public void showBoolean(String msg, Callback callback) {
        if (msg.equals("1") || msg.equals("true")) {
            callback.invoke(true);
        } else {
            callback.invoke(false);
        }
    }

    @ReactMethod
    public void usePromise(String msg, Promise promise) {
        if (TextUtils.isEmpty(msg)) {
            WritableMap map = Arguments.createMap();
            map.putString("content", "收到的消息为空");
            promise.resolve(map);
        } else {
            WritableMap map = Arguments.createMap();
            map.putString("content", "收到的消息为：" + msg);
            promise.resolve(map);
        }

    }

    @ReactMethod
    public void sendEvent() {
        WritableMap map = Arguments.createMap();
        map.putString("content", "消息来自Native");
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("toRN", map);
    }

    @ReactMethod
    public void startActivity(String name, Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject("noActivity", "Activity doesn't exit");
            return;
        }
        this.promise = promise;
        try {
            Class toActivity = Class.forName(name);
            Intent intent = new Intent(currentActivity, toActivity);
            currentActivity.startActivityForResult(intent, 0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private final ActivityEventListener activityEventListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            super.onActivityResult(activity, requestCode, resultCode, data);
            if (requestCode == 0) {
                if (promise != null) {
                    if (resultCode == Activity.RESULT_OK) {
                        String result = data.getStringExtra("result");
                        promise.resolve(result);
                    } else {
                        promise.reject("failed", "failed");
                    }
                }
            }
        }
    };
}
