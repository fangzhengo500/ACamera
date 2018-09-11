package com.loosu.acamera.ui.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.loosu.acamera.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mMainDrawerLayout;
    private View mMainDrawerMenu;
    private View mBtnSettings;

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
