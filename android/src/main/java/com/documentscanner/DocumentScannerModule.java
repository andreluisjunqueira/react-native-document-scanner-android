package com.documentscanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.documentscanner.views.OpenNoteCameraView;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by andre on 28/11/2017.
 */

public class DocumentScannerModule extends ReactContextBaseJavaModule{

    public DocumentScannerModule(ReactApplicationContext reactContext){
        super(reactContext);
    }


    @Override
    public String getName() {
        return "DocumentScanner";
    }
}
