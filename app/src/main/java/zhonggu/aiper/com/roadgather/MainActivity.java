package zhonggu.aiper.com.roadgather;


import zhonggu.aiper.com.baselibrary.Presenter.RxMvpPresenter;
import zhonggu.aiper.com.baselibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void doThing() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected RxMvpPresenter CreatePresenter() {
        return null;
    }
}
