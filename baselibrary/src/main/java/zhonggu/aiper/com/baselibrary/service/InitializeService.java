package zhonggu.aiper.com.baselibrary.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

import zhonggu.aiper.com.baselibrary.IApplication;
import zhonggu.aiper.com.baselibrary.Utils.ToastUtils;
import zhonggu.aiper.com.baselibrary.base.AppConfig;
import zhonggu.aiper.com.baselibrary.loader.ImageLoader;

/**
 * 初始化服务
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "zhonggu.aiper.com.roadgather.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }
    /**
     * start service
     *
     * @param app
     */
    public static void start(IApplication app) {
        Intent intent = new Intent(app, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        app.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    /**
     * init (初始化)
     */
    private void performInit() {
        AppConfig.init();
        ToastUtils.init(getApplication());
        RetrofitService.init();
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                ImageLoader.loadWithCircle(getApplicationContext(), uri, imageView);
            }
        });
        CrashHandler.getInstance().init(getApplication());
    }
}
