package com.reactnativedocumentscannerandroid;

import android.graphics.Color;
import android.view.View;
import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import javax.annotation.Nullable;

import com.reactnativedocumentscannerandroid.views.MainView;
import com.reactnativedocumentscannerandroid.views.OpenNoteCameraView;

public class DocumentScannerAndroidViewManager extends SimpleViewManager<View> {
    public static final String REACT_CLASS = "DocumentScannerAndroidView";
    private MainView view = null;

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public View createViewInstance(ThemedReactContext reactContext) {
      MainView.createInstance(reactContext,(Activity) reactContext.getBaseContext());

      view = MainView.getInstance();
      view.setOnProcessingListener(new OpenNoteCameraView.OnProcessingListener() {
        @Override
        public void onProcessingChange(WritableMap data) {
          dispatchEvent(reactContext, "onProcessingChange", data);
        }
      });

      view.setOnScannerListener(new OpenNoteCameraView.OnScannerListener() {
        @Override
        public void onPictureTaken(WritableMap data) {
          dispatchEvent(reactContext,"onPictureTaken", data);
        }
      });

      return view;
    }

//    @ReactProp(name = "color")
//    public void setColor(View view, String color) {
//        view.setBackgroundColor(Color.parseColor(color));
//    }

  private void dispatchEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
  }

  @ReactProp(name = "documentAnimation", defaultBoolean = false)
  public void setDocumentAnimation(MainView view, boolean animate){
    view.setDocumentAnimation(animate);
  }

  @ReactProp(name="overlayColor")
  public void setOverlayColor(MainView view, String rgbaColor){

  }

  @ReactProp(name = "detectionCountBeforeCapture", defaultInt = 15)
  public void setDetectionCountBeforeCapture(MainView view, int numberOfRectangles){
    view.setDetectionCountBeforeCapture(numberOfRectangles);
  }

  @ReactProp(name = "enableTorch", defaultBoolean = false)
  public void setEnableTorch(MainView view, Boolean enable){
    view.setEnableTorch(enable);
  }

  @ReactProp(name="manualOnly", defaultBoolean = false)
  public void setManualOnly(MainView view, Boolean manualOnly){
    view.setManualOnly(manualOnly);
  }

  @ReactProp(name="brightness", defaultDouble = 10)
  public void setBrightness(MainView view, double brightness){
    view.setBrightness(brightness);
  }

  @ReactProp(name="contrast", defaultDouble = 1)
  public void setContrast(MainView view, double contrast){
    view.setContrast(contrast);
  }

  @ReactProp(name="noGrayScale", defaultBoolean = false)
  public void setRemoveGrayScale(MainView view, boolean bw){
    view.setRemoveGrayScale(bw);
  }
}
