package com.example.malcoln.prototipoinpal.controller;


import android.os.Handler;

import com.example.malcoln.prototipoinpal.util.ProcessButton;

import java.util.Random;

/**
 * Created by Malcoln on 04/08/2017.
 */

public class ProgressGenerator {
    private interface OnCompleteListener {
        void onComplete();
    }
    private OnCompleteListener mListener;
    private int mProgress;

    public ProgressGenerator(OnCompleteListener listener) {
        this.mListener = listener;
    }
    public void start(final ProcessButton button){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100){
                    handler.postDelayed(this, generateDelay());
                } else  {
                    mListener.onComplete();
                }
            }
        }, generateDelay());
    }
    private Random random = new Random();

    private int generateDelay(){
        return random.nextInt(1000);
    }
}
