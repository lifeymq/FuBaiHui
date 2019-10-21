package com.example.lenovo.fubaihui.frame;

import android.app.Application;
import android.content.Context;
import com.example.lenovo.fubaihui.safe.DeviceUuidFactory;
import java.util.UUID;


public class MyApplication extends Application{

    private static MyApplication sApplication;
    public String mToken;
    public UUID mUuid;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        mUuid = DeviceUuidFactory.getInstance(getApplicationContext()).getDeviceUuid();
    }

    public static MyApplication getApplication() {
        return sApplication;
    }

    public static Context getAppContext() {
        return sApplication.getApplicationContext();
    }
}
