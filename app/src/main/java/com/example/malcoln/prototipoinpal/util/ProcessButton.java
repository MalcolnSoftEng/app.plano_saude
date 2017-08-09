package com.example.malcoln.prototipoinpal.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;



/**
 * Created by Malcoln on 04/08/2017.
 */

public abstract class ProcessButton extends FlatButton{
    private int mProgress;
    private int mMaxProgress;
    private int mMinProgress;

    private GradientDrawable mProgressDrawable;
    private GradientDrawable mCompleteDrawable;
    private GradientDrawable mErrorDrawable;

    private CharSequence mLoadingText;
    private CharSequence mCompleteText;
    private CharSequence mErrorText;

    public ProcessButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ProcessButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProcessButton(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        mMinProgress = 0;
        mMaxProgress = 100;

        mProgressDrawable = (GradientDrawable) getDrawable(com.dd.processbutton.R.drawable.rect_progress).mutate();
        mProgressDrawable.setCornerRadius(getCornerRadius());

        mCompleteDrawable = (GradientDrawable) getDrawable(com.dd.processbutton.R.drawable.rect_complete).mutate();
        mCompleteDrawable.setCornerRadius(getCornerRadius());

        mErrorDrawable = (GradientDrawable) getDrawable(com.dd.processbutton.R.drawable.rect_error).mutate();
        mErrorDrawable.setCornerRadius(getCornerRadius());

        if (attrs != null) {
            initAttributes(context, attrs);
        }
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, com.dd.processbutton.R.styleable.ProcessButton);

        if (attr == null) {
            return;
        }

        try {
            mLoadingText = attr.getString(com.dd.processbutton.R.styleable.ProcessButton_pb_textProgress);
            mCompleteText = attr.getString(com.dd.processbutton.R.styleable.ProcessButton_pb_textComplete);
            mErrorText = attr.getString(com.dd.processbutton.R.styleable.ProcessButton_pb_textError);

            int purple = getColor(com.dd.processbutton.R.color.purple_progress);
            int colorProgress = attr.getColor(com.dd.processbutton.R.styleable.ProcessButton_pb_colorProgress, purple);
            mProgressDrawable.setColor(colorProgress);

            int green = getColor(com.dd.processbutton.R.color.green_complete);
            int colorComplete = attr.getColor(com.dd.processbutton.R.styleable.ProcessButton_pb_colorComplete, green);
            mCompleteDrawable.setColor(colorComplete);

            int red = getColor(com.dd.processbutton.R.color.red_error);
            int colorError = attr.getColor(com.dd.processbutton.R.styleable.ProcessButton_pb_colorError, red);
            mErrorDrawable.setColor(colorError);

        } finally {
            attr.recycle();
        }
    }

    public void setProgress(int progress) {
        mProgress = progress;

        if (mProgress == mMinProgress) {
            onNormalState();
        } else if (mProgress == mMaxProgress) {
            onCompleteState();
        } else if (mProgress < mMinProgress){
            onErrorState();
        } else {
            onProgress();
        }

        invalidate();
    }

    protected void onErrorState() {
        if(getErrorText() != null) {
            setText(getErrorText());
        }
        setBackgroundCompat(getErrorDrawable());
    }

    protected void onProgress() {
        if(getLoadingText() != null) {
            setText(getLoadingText());
        }
        setBackgroundCompat(getNormalDrawable());
    }

    protected void onCompleteState() {
        if(getCompleteText() != null) {
            setText(getCompleteText());
        }
        setBackgroundCompat(getCompleteDrawable());
    }

    protected void onNormalState() {
        if(getNormalText() != null) {
            setText(getNormalText());
        }
        setBackgroundCompat(getNormalDrawable());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // progress
        if(mProgress > mMinProgress && mProgress < mMaxProgress) {
            drawProgress(canvas);
        }

        super.onDraw(canvas);
    }

    public abstract void drawProgress(Canvas canvas);

    public int getProgress() {
        return mProgress;
    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public int getMinProgress() {
        return mMinProgress;
    }

    public GradientDrawable getProgressDrawable() {
        return mProgressDrawable;
    }

    public GradientDrawable getCompleteDrawable() {
        return mCompleteDrawable;
    }

    public CharSequence getLoadingText() {
        return mLoadingText;
    }

    public CharSequence getCompleteText() {
        return mCompleteText;
    }

    public void setProgressDrawable(GradientDrawable progressDrawable) {
        mProgressDrawable = progressDrawable;
    }

    public void setCompleteDrawable(GradientDrawable completeDrawable) {
        mCompleteDrawable = completeDrawable;
    }

    public void setLoadingText(CharSequence loadingText) {
        mLoadingText = loadingText;
    }

    public void setCompleteText(CharSequence completeText) {
        mCompleteText = completeText;
    }

    public GradientDrawable getErrorDrawable() {
        return mErrorDrawable;
    }

    public void setErrorDrawable(GradientDrawable errorDrawable) {
        mErrorDrawable = errorDrawable;
    }

    public CharSequence getErrorText() {
        return mErrorText;
    }

    public void setErrorText(CharSequence errorText) {
        mErrorText = errorText;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState savedState = new com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState(superState);
        savedState.mProgress = mProgress;

        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof com.dd.processbutton.ProcessButton.SavedState) {
            com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState savedState = (com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState) state;
            mProgress = savedState.mProgress;
            super.onRestoreInstanceState(savedState.getSuperState());
            setProgress(mProgress);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    /**
     * A {@link android.os.Parcelable} representing the {@link com.dd.processbutton.ProcessButton}'s
     * state.
     */
    public static class SavedState extends BaseSavedState {

        private int mProgress;

        public SavedState(Parcelable parcel) {
            super(parcel);
        }

        private SavedState(Parcel in) {
            super(in);
            mProgress = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(mProgress);
        }

        public static final Creator<com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState> CREATOR = new Creator<com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState>() {

            @Override
            public com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState createFromParcel(Parcel in) {
                return new com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState(in);
            }

            @Override
            public com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState[] newArray(int size) {
                return new com.example.malcoln.prototipoinpal.util.ProcessButton.SavedState[size];
            }
        };
    }
}

