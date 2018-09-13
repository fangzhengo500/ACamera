package com.loosu.acamera.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.loosu.mvpframewor.presenter.MvpPresenter;

public abstract class BaseFragment<P extends MvpPresenter> extends Fragment {
    protected final P mPresenter;

    public BaseFragment() {
        mPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter.onAttach(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
