package zhonggu.aiper.com.roadgather;


import zhonggu.aiper.com.baselibrary.base.BaseActivity;
import zhonggu.aiper.com.roadgather.Model.UserModel;
import zhonggu.aiper.com.roadgather.presener.LoginPresenter;
import zhonggu.aiper.com.roadgather.view.LoginView;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {

    @Override
    protected void doThing() {
        mPresenter.login("123", "456");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter CreatePresenter() {
        return new LoginPresenter();
    }

    @Override
    public void OnSuccess(UserModel user) {

    }

    @Override
    public void OnFailure() {

    }
}
