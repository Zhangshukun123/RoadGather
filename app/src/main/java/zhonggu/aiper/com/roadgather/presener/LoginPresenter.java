package zhonggu.aiper.com.roadgather.presener;

import zhonggu.aiper.com.roadgather.Model.UserModel;
import zhonggu.aiper.com.roadgather.view.LoginView;

public class LoginPresenter extends IPresenter<LoginView> {

    public void login(String username, String password) {
        invoke(apiDiModel.login(username, password), new CallBack<UserModel>() {
            @Override
            public void callBackResult(UserModel userModel) {
                getMvpView().OnSuccess(userModel);
            }
            @Override
            public void OnFailure() {
                getMvpView().OnFailure();
            }
        });
    }


}
