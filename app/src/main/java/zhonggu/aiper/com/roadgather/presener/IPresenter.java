package zhonggu.aiper.com.roadgather.presener;

import zhonggu.aiper.com.baselibrary.Presenter.RxMvpPresenter;
import zhonggu.aiper.com.baselibrary.view.MvpView;
import zhonggu.aiper.com.roadgather.APIDiModel;

class IPresenter<V extends MvpView> extends RxMvpPresenter<V> {
    APIDiModel apiDiModel;

    IPresenter() {
        apiDiModel = APIDiModel.getInstance();
    }

}
