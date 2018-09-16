package com.loosu.acamera.presenter.activity;

import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Size;
import android.view.View;

import com.loosu.acamera.R;
import com.loosu.acamera.adapter.ImageOutputSizeAdapter;
import com.loosu.acamera.adapter.base.recyclerview.IRecyclerItemClickListener;
import com.loosu.acamera.delegate.FaceBackImageSizeConfigDelegate;
import com.loosu.themvpframework_lib.presenter.ActivityPresenter;

import java.util.Arrays;

public class FaceBackImageSizeConfigActivity extends ActivityPresenter<FaceBackImageSizeConfigDelegate> implements IRecyclerItemClickListener {

    private ImageOutputSizeAdapter mOutputSizeAdapter = new ImageOutputSizeAdapter();

    @Override
    protected Class<FaceBackImageSizeConfigDelegate> getDelegateClass() {
        return FaceBackImageSizeConfigDelegate.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView viewList = mViewDelegate.getViewById(R.id.view_list);
        viewList.setAdapter(mOutputSizeAdapter);
        mOutputSizeAdapter.setItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CameraManager cm = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = cm.getCameraCharacteristics(String.valueOf(CameraCharacteristics.LENS_FACING_FRONT));
            StreamConfigurationMap streamConfigurationMap = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(ImageFormat.JPEG);
            mOutputSizeAdapter.setDatas(Arrays.asList(outputSizes));
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(RecyclerView parent, int position, RecyclerView.ViewHolder holder, View view) {
        mOutputSizeAdapter.setItemSelected(position);
    }
}
