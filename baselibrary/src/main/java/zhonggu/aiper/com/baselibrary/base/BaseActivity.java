package zhonggu.aiper.com.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import zhonggu.aiper.com.baselibrary.Presenter.RxMvpPresenter;
import zhonggu.aiper.com.baselibrary.Utils.ToastUtils;
import zhonggu.aiper.com.baselibrary.view.MvpView;

public abstract class BaseActivity<T extends RxMvpPresenter<V>, V extends MvpView> extends AppCompatActivity implements MvpView {


    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = CreatePresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        doThing();

    }


    protected abstract void doThing();

    protected abstract int getLayoutId();

    protected abstract T CreatePresenter();


    protected void toast(String mes) {
        ToastUtils.showToast(mes);
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
