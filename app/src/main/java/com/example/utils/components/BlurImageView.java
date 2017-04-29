package com.example.utils.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.example.R;

public class BlurImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint mPaint;
    private int mBlurColor;

    public BlurImageView(Context context) {
        this(context, null);
    }

    public BlurImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mBlurColor = ContextCompat.getColor(context, R.color.image_blur);
    }

    public BlurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBlurColor);

        invalidate();
    }

    public void setBlurColor(int mBlurColor) {
        this.mBlurColor = mBlurColor;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(getLeft(), 0, getRight(), getHeight(), mPaint);
    }
}
