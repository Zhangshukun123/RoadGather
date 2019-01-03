package zhonggu.aiper.com.baselibrary.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert cm != null;
            NetworkInfo info = cm.getActiveNetworkInfo();
            return null != info && info.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
