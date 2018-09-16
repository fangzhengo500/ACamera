package com.loosu.acamera.delegate;

import android.graphics.ImageFormat;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;

import com.loosu.acamera.R;
import com.loosu.acamera.utils.Log;
import com.loosu.themvpframework_lib.view.AppDelegate;

public class CameraSettingMenuDelegate extends AppDelegate {
    private static final String TAG = "CameraSettingMenuDelega";

    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_camera_setting_menu;
    }

    public void updateCameraFacingFrontConfig(CameraCharacteristics characteristics) {
        Log.i(TAG, "updateCameraFacingFrontConfig");
    }

    public void updateCameraFacingBackConfig(CameraCharacteristics characteristics) {
        Log.i(TAG, "updateCameraFacingBackConfig");

        StreamConfigurationMap streamConfigurationMap = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        Size[] outputSizes = streamConfigurationMap.getOutputSizes(ImageFormat.JPEG);
        for (int i = 0; i < outputSizes.length; i++) {
            Log.i(TAG, outputSizes[i].toString());
        }
    }
}
