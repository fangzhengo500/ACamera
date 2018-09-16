package com.loosu.acamera.presenter.fragment;

import com.loosu.acamera.delegate.CameraPerviewDelegate;
import com.loosu.themvpframework_lib.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class CameraPerviewFragment extends FragmentPresenter<CameraPerviewDelegate> {


    @Override
    protected Class<CameraPerviewDelegate> getDelegateClass() {
        return CameraPerviewDelegate.class;
    }
}
