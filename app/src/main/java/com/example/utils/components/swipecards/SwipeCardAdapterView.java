package com.example.utils.components.swipecards;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v13.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.example.R;

import rx.functions.Action0;

public class SwipeCardAdapterView extends AdapterView {
    private int mMaxVisible = 2;
    private int mMinAdapterStack = 6;
    private float mRotationDegrees = 15.f;

    private Adapter mAdapter;
    private int mFirstObjectInStack = 0;
    private OnSwipeListener mOnSwipeListener;
    private AdapterDataSetObserver mDataSetObserver;
    private boolean mInLayout = false;
    private View mActiveCard = null;
    private SwipeCardListener mFlingCardListener;
    private PointF mLastTouchPoint;

    private int mHeightMeasureSpec;
    private int mWidthMeasureSpec;

    public SwipeCardAdapterView(Context context) {
        this(context, null);
    }

    public SwipeCardAdapterView(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.SwipeCardStyle);
    }

    public SwipeCardAdapterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SwipeCardAdapterView, defStyle, 0);

        mMaxVisible = attributes.getInt(R.styleable.SwipeCardAdapterView_max_visible, mMaxVisible);
        mMinAdapterStack = attributes.getInt(R.styleable.SwipeCardAdapterView_min_adapter_stack, mMinAdapterStack);
        mRotationDegrees = attributes.getFloat(R.styleable.SwipeCardAdapterView_rotation_degrees, mRotationDegrees);

        attributes.recycle();
    }

    public void init(final Context context, Adapter mAdapter) {
        if (!(context instanceof OnSwipeListener)) {
            throw new RuntimeException("Activity does not implement SwipeFlingAdapterView.onSwipeListener");
        }

        mOnSwipeListener = (OnSwipeListener) context;

        setAdapter(mAdapter);
    }

    @Override
    public View getSelectedView() {
        return mActiveCard;
    }

    @Override
    public void requestLayout() {
        if (!mInLayout) {
            super.requestLayout();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (mAdapter == null) {
            return;
        }

        mInLayout = true;
        final int adapterCount = mAdapter.getCount();

        if (adapterCount == 0) {
            removeAllViewsInLayout();
        } else {
            View topCard = getChildAt(mFirstObjectInStack);
            if (mActiveCard != null && topCard != null && topCard == mActiveCard) {
                if (mFlingCardListener.isTouching()) {
                    PointF lastPoint = mFlingCardListener.getLastPoint();
                    if (mLastTouchPoint == null || !mLastTouchPoint.equals(lastPoint)) {
                        mLastTouchPoint = lastPoint;
                        removeViewsInLayout(0, mFirstObjectInStack);
                        layoutChildren(1, adapterCount);
                    }
                }
            } else {
                // Reset the UI and set top view listener
                removeAllViewsInLayout();
                layoutChildren(0, adapterCount);
                setTopView();
            }
        }

        mInLayout = false;

        if (adapterCount <= mMinAdapterStack) {
            mOnSwipeListener.onAdapterAboutToEmpty(adapterCount);
        }
    }

    private void layoutChildren(int startingIndex, int adapterCount) {
        while (startingIndex < Math.min(adapterCount, mMaxVisible)) {
            View newUnderChild = mAdapter.getView(startingIndex, null, this);

            if (newUnderChild.getVisibility() != GONE) {
                makeAndAddView(newUnderChild);
                mFirstObjectInStack = startingIndex;
            }

            startingIndex++;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void makeAndAddView(View child) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        addViewInLayout(child, 0, lp, true);

        final boolean needToMeasure = child.isLayoutRequested();

        if (needToMeasure) {
            int childWidthSpec = getChildMeasureSpec(mWidthMeasureSpec,
                    getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin,
                    lp.width);
            int childHeightSpec = getChildMeasureSpec(mHeightMeasureSpec,
                    getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin,
                    lp.height);
            child.measure(childWidthSpec, childHeightSpec);
        } else {
            cleanupLayoutState(child);
        }

        int w = child.getMeasuredWidth();
        int h = child.getMeasuredHeight();

        int gravity = lp.gravity;

        if (gravity == -1) {
            gravity = Gravity.TOP | Gravity.START;
        }

        int layoutDirection = getLayoutDirection();
        final int absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection);
        final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;

        int childLeft;
        int childTop;

        switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                childLeft = (getWidth() + getPaddingLeft() - getPaddingRight() - w) / 2 +
                        lp.leftMargin - lp.rightMargin;
                break;
            case Gravity.END:
                childLeft = getWidth() + getPaddingRight() - w - lp.rightMargin;
                break;
            case Gravity.START:
            default:
                childLeft = getPaddingLeft() + lp.leftMargin;
                break;
        }

        switch (verticalGravity) {
            case Gravity.CENTER_VERTICAL:
                childTop = (getHeight() + getPaddingTop() - getPaddingBottom() - h) / 2 +
                        lp.topMargin - lp.bottomMargin;
                break;
            case Gravity.BOTTOM:
                childTop = getHeight() - getPaddingBottom() - h - lp.bottomMargin;
                break;
            case Gravity.TOP:
            default:
                childTop = getPaddingTop() + lp.topMargin;
                break;
        }

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
        ViewCompat.setLayerType(child, android.support.v4.view.ViewCompat.LAYER_TYPE_HARDWARE, p);

        child.layout(childLeft, childTop, childLeft + w, childTop + h);
    }

    private void setTopView() {
        if (getChildCount() > 0) {
            mActiveCard = getChildAt(mFirstObjectInStack);

            if (mActiveCard != null) {
                mFlingCardListener = new SwipeCardListener(mActiveCard, mAdapter.getItem(0),
                        mRotationDegrees, new SwipeCardListener.SwipeListener() {

                    @Override
                    public void onTouchDown() {
                        mOnSwipeListener.onTouchDown();
                    }

                    @Override
                    public void onTouchUp() {
                        mOnSwipeListener.onTouchUp();
                    }

                    @Override
                    public void onCardExited() {
                        mActiveCard = null;
                        mOnSwipeListener.removeFirstObjectInAdapter();
                    }

                    @Override
                    public void onBeforeLeftCardExit(Action0 done) {
                        mOnSwipeListener.onBeforeLeftCardExit(done);
                    }

                    @Override
                    public void onBeforeRightCardExit(Action0 done) {
                        mOnSwipeListener.onBeforeRightCardExit(done);
                    }

                    @Override
                    public void leftExit(Object dataObject) {
                        mOnSwipeListener.onLeftCardExit(dataObject);
                    }

                    @Override
                    public void rightExit(Object dataObject) {
                        mOnSwipeListener.onRightCardExit(dataObject);
                    }

                    @Override
                    public void onClick(Object dataObject) {
                        mOnSwipeListener.onClick(dataObject);
                    }

                    @Override
                    public void onScroll(float scrollProgressPercent) {
                        mOnSwipeListener.onScroll(scrollProgressPercent);
                    }
                });

                mActiveCard.setOnTouchListener(mFlingCardListener);
            }
        }
    }

    @NonNull
    public SwipeCardListener getTopCardListener() {
        if (mFlingCardListener == null) {
            throw new NullPointerException();
        }

        return mFlingCardListener;
    }

    @Override
    public Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }

        mAdapter = adapter;

        if (mAdapter != null && mDataSetObserver == null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    public void setSwipeListener(OnSwipeListener onSwipeListener) {
        mOnSwipeListener = onSwipeListener;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;
    }

    private class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            requestLayout();
        }

        @Override
        public void onInvalidated() {
            requestLayout();
        }
    }

    public interface OnSwipeListener<T> {
        void onTouchDown();

        void onTouchUp();

        void removeFirstObjectInAdapter();

        void onBeforeLeftCardExit(Action0 done);

        void onBeforeRightCardExit(Action0 done);

        void onLeftCardExit(T model);

        void onRightCardExit(T model);

        void onAdapterAboutToEmpty(int itemsInAdapter);

        void onScroll(float scrollProgressPercent);

        void onClick(T model);
    }

}
