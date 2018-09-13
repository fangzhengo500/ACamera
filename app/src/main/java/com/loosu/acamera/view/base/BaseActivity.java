package com.loosu.acamera.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.loosu.mvpframewor.presenter.MvpPresenter;

public abstract class BaseActivity<P extends MvpPresenter> extends AppCompatActivity {

    protected final P mPresenter;

    public BaseActivity() {
        mPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter.onAttach(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
