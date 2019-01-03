package zhonggu.aiper.com.roadgather.view;

import zhonggu.aiper.com.baselibrary.view.MvpView;
import zhonggu.aiper.com.roadgather.Model.UserModel;

public interface LoginView extends MvpView {
    void OnSuccess(UserModel user);
    void OnFailure();
}
