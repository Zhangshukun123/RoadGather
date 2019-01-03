package zhonggu.aiper.com.baselibrary.service;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhonggu.aiper.com.baselibrary.IApplication;
import zhonggu.aiper.com.baselibrary.Utils.NetUtil;
import zhonggu.aiper.com.baselibrary.base.AppConfig;

public class RetrofitService {


    private static Retrofit retrofit;

    private static Map<String, String> map = new HashMap<>();

    //设缓存有效期为5分钟
    private static final long CACHE_STALE_SEC = 60 * 5;
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * 公共参数
     */
    private static Interceptor addQueryParameterInterceptor = chain -> {
        Request originalRequest = chain.request();
        Request request;
        String method = originalRequest.method();
//            Headers headers = originalRequest.headers();
        Headers.Builder builder = originalRequest.headers().newBuilder();
        builder.add("Content-Type", "application/json; charset=utf-8");
        builder.add("Accept", "application/json");
        builder.add("Connection", "close");


        //添加token 根据需求来定
//        String token = SPEngine.getSPEngine().getUserModel().data.token;
//        Log.d("请求Body", "token--->" + token);
//        if (!TextUtils.isEmpty(token)) {
//            builder.add("X-Token", token);
//        }
        Headers headers = builder.build();

        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
//                    .addQueryParameter("platform", platform)
//                    .addQueryParameter("client", client)
//                    .addQueryParameter("version", "1.0.0")
                .build();
        request = originalRequest.newBuilder().url(modifiedUrl).headers(headers).build();

        return chain.proceed(request);
    };

    /**
     * 响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (!NetUtil.isNetworkAvailable(IApplication.getsContext())) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//            Logger.e("no network");
        }
        Response originalResponse = chain.proceed(request);

        if (NetUtil.isNetworkAvailable(IApplication.getsContext())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                    .removeHeader("Pragma")
                    .build();
        }
    };

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 初始化网络通信服务
     */
    static void init() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        File httpCacheDirectory = new File(IApplication.getsContext().getCacheDir(), "com.zhong.gu.cache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        httpClientBuilder
                .addInterceptor(addQueryParameterInterceptor)
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .cache(cache)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConfig.base_url)
                .build();
    }
}
