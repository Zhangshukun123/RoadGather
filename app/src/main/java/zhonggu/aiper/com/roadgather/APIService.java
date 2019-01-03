package zhonggu.aiper.com.roadgather;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import zhonggu.aiper.com.roadgather.Model.UserModel;


/**
 * 数据服务接口
 */
public interface APIService {
    /**
     * 登陆接口
     */
    @POST("login/login")
    Observable<UserModel> login(@QueryMap Map<String, String> urlMap, @Body RequestBody body);


}
