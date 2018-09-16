package com.loosu.acamera.presenter.fragment;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.TextureView;
import android.view.View;

import com.loosu.acamera.R;
import com.loosu.acamera.delegate.CameraPreviewDelegate;
import com.loosu.acamera.utils.Log;
import com.loosu.themvpframework_lib.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class CameraPreviewFragment extends FragmentPresenter<CameraPreviewDelegate> {
    private static final String TAG = "CameraPreviewFragment";

    TextureView.SurfaceTextureListener mCameraPreviewListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            Log.i(TAG, "相机预览 available");
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            Log.i(TAG, "相机预览大小变化");
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            Log.e(TAG, "相机预览销毁");
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            Log.i(TAG, "相机预览更新");
        }
    };

    @Override
    protected Class<CameraPreviewDelegate> getDelegateClass() {
        return CameraPreviewDelegate.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        TextureView cameraPreview = mViewDelegate.getViewById(R.id.camera_preview);
        if (cameraPreview.isAvailable()) {
            openCamera();
        } else {
            cameraPreview.setSurfaceTextureListener(mCameraPreviewListener);
        }
    }

    private void openCamera() {
        CameraCharacteristics.LENS_FACING_FRONT;
    }
}
