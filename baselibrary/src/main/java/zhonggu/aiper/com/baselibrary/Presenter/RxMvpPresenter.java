package zhonggu.aiper.com.baselibrary.Presenter;

import android.app.Application;
import android.content.Context;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import zhonggu.aiper.com.baselibrary.view.MvpView;

public class RxMvpPresenter<V extends MvpView> extends BaseMvpPresenter<V> {


    public RxMvpPresenter() {
    }

    private CompositeSubscription mCompositeSubscription;

    protected <T> void invoke(Observable<T> observable, final CallBack<T> result) {
        Subscription subscription =
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<T>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                result.OnFailure();
//                                Throwable throwable = HttpErrorToast.unifiedError(e, mCx);
//                                ToastUtils.showToast(throwable.getMessage());
                            }

                            @Override
                            public void onNext(T t) {
                                result.callBackResult(t);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(V mvpView) {
        super.attachView(mvpView);
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override
    public void detachView() {
        super.detachView();
        mCompositeSubscription.clear();
        mCompositeSubscription = null;
    }

    public interface CallBack<T> {
        void callBackResult(T t);

        void OnFailure();
    }

}
