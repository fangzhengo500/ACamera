package com.loosu.acamera.presenter.activity;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.loosu.acamera.R;
import com.loosu.acamera.delegate.MainDelegate;
import com.loosu.themvpframework_lib.presenter.ActivityPresenter;

public class MainActivity extends ActivityPresenter<MainDelegate> {

    private DrawerLayout mMainDrawerLayout;
    private View mMainDrawerMenu;
    private View mBtnSettings;

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        mMainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mMainDrawerMenu = findViewById(R.id.main_drawer_menu);
        mBtnSettings = findViewById(R.id.btn_settings);


        mBtnSettings.setOnClickListener(mOnClickListener);

        mMainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_settings:
                    if (!mMainDrawerLayout.isDrawerOpen(Gravity.END)) {
                        mMainDrawerLayout.openDrawer(Gravity.END);
                    }
                    break;
            }
        }
    };
}
