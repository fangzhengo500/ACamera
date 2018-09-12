package com.loosu.acamera.presenter.base;

import com.loosu.acamera.view.base.MvpView;

/**
 * Mvp 架构P层抽象
 * @param <V> 关联的V层抽象
 */
public interface MvpPresenter<V extends MvpView> {

    /**
     * 与View层关联
     * @param mvpView v层实例
     */
    public void onAttach(V mvpView);

    /**
     * 与View层解除关联
     */
    public void onDetach();
}
