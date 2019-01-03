package zhonggu.aiper.com.baselibrary.view;

import android.support.annotation.UiThread;

public interface MvpPresenter<V extends MvpView> {
    /**
     * Set or attach the view to this presenter
     */
    @UiThread
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    @UiThread
    void detachView();
}
