package zhonggu.aiper.com.roadgather;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import zhonggu.aiper.com.baselibrary.Utils.AESOperator;
import zhonggu.aiper.com.baselibrary.Utils.UrLMdUtlis;
import zhonggu.aiper.com.baselibrary.service.RetrofitService;
import zhonggu.aiper.com.roadgather.Model.UserModel;

public class APIDiModel {

    private APIService api;

    private static final APIDiModel ourInstance = new APIDiModel(RetrofitService.getRetrofit().create(APIService.class));

    public static APIDiModel getInstance() {
        return ourInstance;
    }

    private APIDiModel(APIService api) {
        this.api = api;
    }

    private RequestBody getBody(String parameter) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), parameter);
    }


    /***
     * 不想加密修改这个方法
     * @param parameter
     * @return
     */
    private Map<String, String> getUrlMap(String parameter) {
        Map<String, String> map = new HashMap<>();
        long time = System.currentTimeMillis();
        String lol = "\"\"";
        if (!TextUtils.isEmpty(parameter)) {
            lol = UrLMdUtlis.digest(parameter, "MD5");
        }
        map.put("time", String.valueOf(time));
        map.put("context", parameter);
        return map;
    }

    /**
     * 登录
     *
     * @param phone    账号
     * @param password 密码
     */
    public Observable<UserModel> login(String phone, String password) {
        JSONObject bodyParams = new JSONObject();
        try {
            if (!TextUtils.isEmpty(phone)) {
                bodyParams.put("phone", phone);
            }
            if (!TextUtils.isEmpty(password)) {
                bodyParams.put("password", password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encryptParams = AESOperator.getInstance().encrypt(bodyParams.toString());
        RequestBody body = getBody(bodyParams.toString());
//        Map<String, String> queryMap =;
//        Log.i(TAG, "login: " + queryMap.toString());
        return api.login(getUrlMap(encryptParams), body);
    }


}
