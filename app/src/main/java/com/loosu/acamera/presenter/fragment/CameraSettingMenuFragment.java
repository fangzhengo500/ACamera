package com.loosu.acamera.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.loosu.acamera.R;
import com.loosu.acamera.delegate.CameraSettingMenuDelegate;
import com.loosu.acamera.presenter.activity.FaceBackImageSizeConfigActivity;
import com.loosu.acamera.utils.Log;
import com.loosu.themvpframework_lib.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class CameraSettingMenuFragment extends FragmentPresenter<CameraSettingMenuDelegate> implements View.OnClickListener {
    private static final String TAG = "CameraSettingMenuFragme";

    @Override
    protected Class<CameraSettingMenuDelegate> getDelegateClass() {
        return CameraSettingMenuDelegate.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDelegate.getViewById(R.id.item_face_back_image_size).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Context context = getContext();
        CameraManager cm = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        if (cm == null) {
            Log.e(TAG, "CameraManager is null return");
            return;
        }
        try {
            String[] cameraIdList = cm.getCameraIdList();
            for (int i = 0; i < cameraIdList.length; i++) {
                String cameraId = cameraIdList[i];
                CameraCharacteristics characteristics = cm.getCameraCharacteristics(cameraId);
                Integer lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (lensFacing == CameraCharacteristics.LENS_FACING_FRONT) {
                    mViewDelegate.updateCameraFacingFrontConfig(characteristics);

                } else if (lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    mViewDelegate.updateCameraFacingBackConfig(characteristics);

                } else {
                    // do noting
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_face_back_image_size:
                onClickItemFaceBackImageSize();
                break;
        }
    }

    private void onClickItemFaceBackImageSize() {
        Intent intent = new Intent(getContext(), FaceBackImageSizeConfigActivity.class);
        startActivity(intent);
    }
}
