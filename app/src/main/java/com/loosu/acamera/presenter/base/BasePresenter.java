package com.loosu.acamera.presenter.base;

import com.loosu.acamera.view.base.MvpView;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView = null;

    @Override
    public void onAttach(V mvpView) {
        mView = mvpView;
    }

    @Override
    public void onDetach() {
        mView = null;
    }
}
