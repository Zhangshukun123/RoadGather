package zhonggu.aiper.com.baselibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import zhonggu.aiper.com.baselibrary.service.InitializeService;

public class IApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        InitializeService.start(this);
    }

    public static Context getsContext() {
        return sContext;
    }
}
