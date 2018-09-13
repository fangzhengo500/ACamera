package com.loosu.acamera.view.activity;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.loosu.acamera.R;
import com.loosu.acamera.contract.MainContract;
import com.loosu.acamera.presenter.MainPresenter;
import com.loosu.acamera.view.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> {

    private DrawerLayout mMainDrawerLayout;
    private View mMainDrawerMenu;
    private View mBtnSettings;

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
