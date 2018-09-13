package com.loosu.acamera.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loosu.acamera.R;
import com.loosu.acamera.contract.CameraSettingMenuContract;
import com.loosu.acamera.presenter.CameraSettingMenuPresenter;
import com.loosu.acamera.view.base.BaseFragment;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class CameraSettingMenuFragment extends BaseFragment<CameraSettingMenuContract.Presenter> {

    @Override
    protected CameraSettingMenuContract.Presenter createPresenter() {
        return new CameraSettingMenuPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera_setting_menu, container, false);
    }
}
