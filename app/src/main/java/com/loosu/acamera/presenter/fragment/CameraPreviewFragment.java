package com.loosu.acamera.presenter.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import com.loosu.acamera.R;
import com.loosu.acamera.delegate.CameraPreviewDelegate;
import com.loosu.acamera.utils.Log;
import com.loosu.themvpframework_lib.presenter.FragmentPresenter;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class CameraPreviewFragment extends FragmentPresenter<CameraPreviewDelegate> {
    private static final String TAG = "CameraPreviewFragment";

    private Handler mBackgroundHandler;
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);
    private CameraDevice mCameraDevice = null;

    TextureView.SurfaceTextureListener mCameraPreviewListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            Log.i(TAG, "相机预览 available");
            try {
                CameraManager cm = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
                String[] cameraIdList = cm.getCameraIdList();
                for (int i = 0; i < cameraIdList.length; i++) {
                    String cameraId = cameraIdList[i];
                    CameraCharacteristics characteristics = cm.getCameraCharacteristics(cameraId);
                    Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                    if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
                        openCamera(cameraId);
                        break;
                    }
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
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

    private CameraDevice.StateCallback mDeviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            Log.i(TAG, "相机已开启");
            mCameraOpenCloseLock.release();
            mCameraDevice = camera;
            createCameraPreviewSession(mCameraDevice);
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            Log.e(TAG, "相机会话断开");
            mCameraOpenCloseLock.release();
            mCameraDevice = null;
            camera.close();
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            Log.e(TAG, "相机错误：" + error);
            mCameraOpenCloseLock.release();
            mCameraDevice = null;
            camera.close();
        }
    };

    private CameraCaptureSession.StateCallback mSessionPreviewStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
            Log.i(TAG, "onConfigured：" + session);
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            Log.e(TAG, "onConfigureFailed：" + session);
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
        initLooper();
        TextureView cameraPreview = mViewDelegate.getViewById(R.id.camera_preview);
        if (cameraPreview.isAvailable()) {
            try {
                CameraManager cm = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
                String[] cameraIdList = cm.getCameraIdList();
                for (int i = 0; i < cameraIdList.length; i++) {
                    String cameraId = cameraIdList[i];
                    CameraCharacteristics characteristics = cm.getCameraCharacteristics(cameraId);
                    Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                    if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
                        openCamera(cameraId);
                        break;
                    }
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            cameraPreview.setSurfaceTextureListener(mCameraPreviewListener);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mCameraOpenCloseLock.release();
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    private void openCamera(String cameraId) {
        Context context = getContext();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
            return;
        }

        try {
            CameraManager cm = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            if (!mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("Time out waiting to lock camera opening.");
            }
            cm.openCamera(cameraId, mDeviceStateCallback, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera opening.", e);
        }
    }

    private void initLooper() {
        HandlerThread handlerThread = new HandlerThread("acamera");
        handlerThread.start();
        mBackgroundHandler = new Handler(handlerThread.getLooper());
    }

    private void createCameraPreviewSession(CameraDevice device) {
        try {
            TextureView preview = mViewDelegate.getViewById(R.id.camera_preview);
            Surface previewSurface = new Surface(preview.getSurfaceTexture());

            final CaptureRequest.Builder previewRequestBuilder = device.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            previewRequestBuilder.addTarget(previewSurface);
            //device.createCaptureSession(Arrays.asList(previewSurface), mSessionPreviewStateCallback, mBackgroundHandler);
            device.createCaptureSession(Arrays.asList(previewSurface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    try {
                        session.setRepeatingRequest(previewRequestBuilder.build(), null, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
