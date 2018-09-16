package com.loosu.acamera.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.loosu.acamera.R;
import com.loosu.themvpframework_lib.view.AppDelegate;

public class FaceBackImageSizeConfigDelegate extends AppDelegate {
    @Override
    protected int getRootLayoutId() {
        return R.layout.delegate_face_back_image_size_config;
    }

    @Override
    public void onCreate(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(inflater, container, savedInstanceState);
        Context context = getRootView().getContext();
        RecyclerView viewList = getViewById(R.id.view_list);
        viewList.setLayoutManager(new LinearLayoutManager(context));
    }
}
