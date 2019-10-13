package com.rndemo;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * Created by Kevin on 2019-10-06<br/>
 * Describe:<br/>
 */
public class RNModule extends ReactContextBaseJavaModule {
    public RNModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "RNModule";
    }
}
