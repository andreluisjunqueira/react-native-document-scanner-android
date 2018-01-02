package com.documentscanner;

import android.app.Activity;
import android.util.Log;

import com.documentscanner.views.OpenNoteCameraView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nullable;

/**
 * Created by Andre on 29/11/2017.
 */

public class DocumentScannerViewManager extends SimpleViewManager<OpenNoteCameraView>{

    public static final String REACT_CLASS = "DocumentScanner";
    private Activity mActivity = null;

    public DocumentScannerViewManager(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected OpenNoteCameraView createViewInstance(final ThemedReactContext reactContext) {
        OpenNoteCameraView view = new OpenNoteCameraView(reactContext, -1, reactContext.getCurrentActivity());
        view.setOnScannerListener(new OpenNoteCameraView.OnScannerListener() {
            @Override
            public void onPictureTaken(WritableMap data) {
                Log.d("LISTENER", "O Listener--->"+data);
                dispatchEvent(reactContext,"onPictureTaken", data);
            }
        });
        return view;
    }

    private void dispatchEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }

    @ReactProp(name="documentAnimation", defaultBoolean = false)
    public void setDocumentAnimation(OpenNoteCameraView view, boolean animate){
        view.setDocumentAnimation(animate);
    }
}
