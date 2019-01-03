package zhonggu.aiper.com.baselibrary.Utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;
    private static String oldMsg;
    private static Toast toast = null;
    private static long oneTime = 0;

    private ToastUtils() {
        throw new RuntimeException("ToastUtils cannot be initialized!");
    }

    public static void init(Context context) {
        sContext = context;
    }

    public static void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(sContext, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            long twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
            oneTime = twoTime;
        }
    }

    public static void showToast(int resId) {
        showToast(sContext.getString(resId));
    }
}
