package com.loosu.acamera.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.loosu.acamera.presenter.base.MvpPresenter;

public abstract class BaseActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView {

    protected final P mPresenter;

    public BaseActivity() {
        mPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
