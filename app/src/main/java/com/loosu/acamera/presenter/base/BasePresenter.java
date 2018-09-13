package com.loosu.acamera.presenter.base;


import com.loosu.mvpframewor.presenter.MvpPresenter;

public class BasePresenter<V> implements MvpPresenter<V> {

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
