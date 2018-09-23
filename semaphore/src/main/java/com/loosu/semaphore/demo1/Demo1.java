package com.loosu.semaphore.demo1;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.loosu.semaphore.R;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class Demo1 extends AppCompatActivity {
    private static final String TAG = "Demo1";

    private Semaphore mSemaphore = new Semaphore(3);

    private Handler mBackgroundHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        HandlerThread handlerThread = new HandlerThread("semaphore");
        handlerThread.start();
        mBackgroundHandler = new Handler(handlerThread.getLooper());
    }

    public void acquireSemaphore(View view) {

        mBackgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "acquire");
                    mSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void releaseSemaphore(View view) {
        Log.d(TAG, "release");
        mSemaphore.release();
    }
}
