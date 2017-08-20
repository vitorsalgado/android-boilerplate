package com.example.utils.components.swipecards;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.utils.DimensionUtils;

import io.reactivex.functions.Action;

public class SwipeCardListener implements View.OnTouchListener {
	private static final String TAG = SwipeCardListener.class.getSimpleName();

	private static final int INVALID_POINTER_ID = -1;
	private static final int TOUCH_ABOVE = 0;
	private static final int TOUCH_BELOW = 1;

	private final float mObjectX;
	private final float mObjectY;
	private final int mObjectH;
	private final int mObjectW;
	private final int mParentWidth;
	private final SwipeListener mSwipeListener;
	private final Object mDataObject;
	private final float mHalfWidth;
	private float mBaseRotationDegrees;

	private float mAPosX;
	private float mAPosY;
	private float mADownTouchX;
	private float mADownTouchY;

	private int mActivePointerId = INVALID_POINTER_ID;
	private View mFrame = null;

	private int mTouchPosition;
	private boolean mIsAnimationRunning = false;
	private float mMaxCos = (float) Math.cos(Math.toRadians(45));

	private final int mBottomLimit;
	private final int mLeftRightMarginLimit;

	public SwipeCardListener(View frame, Object itemAtPosition, float rotationDegrees, SwipeListener swipeListener) {
		super();

		mFrame = frame;
		mObjectX = frame.getX();
		mObjectY = frame.getY();
		mObjectH = frame.getHeight();
		mObjectW = frame.getWidth();
		mHalfWidth = mObjectW / 2f;
		mDataObject = itemAtPosition;
		mParentWidth = ((ViewGroup) frame.getParent()).getWidth();
		mBaseRotationDegrees = rotationDegrees;
		mSwipeListener = swipeListener;

		mBottomLimit = DimensionUtils.dpToPx(frame.getContext(), 85);
		mLeftRightMarginLimit = DimensionUtils.dpToPx(frame.getContext(), 20);
	}

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View view, MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				mSwipeListener.onTouchDown();

				// from http://android-developers.blogspot.com/2010/06/making-sense-of-multitouch.html
				// Save the ID of this pointer

				mActivePointerId = event.getPointerId(0);

				if (mFrame.getY() > 300) {

					break;
				}

				float x = 0;
				float y = 0;
				boolean success = false;

				try {
					x = event.getX(mActivePointerId);
					y = event.getY(mActivePointerId);

					if (y >= mObjectH - mBottomLimit || x <= mLeftRightMarginLimit || x >= mObjectW - mLeftRightMarginLimit) {
						mActivePointerId = INVALID_POINTER_ID;
						view.getParent().requestDisallowInterceptTouchEvent(false);
						mSwipeListener.onTouchUp();
						break;
					}

					success = true;
				} catch (IllegalArgumentException e) {
					Log.w(TAG, "Exception in onTouch(view, event) : " + mActivePointerId, e);
					mSwipeListener.onTouchUp();
				}

				if (success) {
					// Remember where we started
					mADownTouchX = x;
					mADownTouchY = y;
					//to prevent an initial jump of the magnifier, aposX and mAPosY must
					//have the values from the magnifier mFrame
					if (mAPosX == 0) {
						mAPosX = mFrame.getX();
					}
					if (mAPosY == 0) {
						mAPosY = mFrame.getY();
					}

					if (y < mObjectH / 2) {
						mTouchPosition = TOUCH_ABOVE;
					} else {
						mTouchPosition = TOUCH_BELOW;
					}
				}

				view.getParent().requestDisallowInterceptTouchEvent(true);
				break;

			case MotionEvent.ACTION_UP:
				float endX = event.getX();
				float endY = event.getY();

				if ((!(mADownTouchY >= mObjectH - mBottomLimit || mADownTouchX <= mLeftRightMarginLimit || mADownTouchX >= mObjectW - mLeftRightMarginLimit))
						&& isClick(mADownTouchX, endX, mADownTouchY, endY) && event.getAction() != MotionEvent.ACTION_MOVE) {

					mActivePointerId = INVALID_POINTER_ID;
					mSwipeListener.onClick(mDataObject);
					view.getParent().requestDisallowInterceptTouchEvent(false);

					break;
				}

				mActivePointerId = INVALID_POINTER_ID;
				resetCardViewOnStack();
				view.getParent().requestDisallowInterceptTouchEvent(false);
				break;

			case MotionEvent.ACTION_POINTER_DOWN:
				break;

			case MotionEvent.ACTION_POINTER_UP:
				// Extract the index of the pointer that left the touch sensor
				final int pointerIndex = (event.getAction() &
						MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;

				final int pointerId = event.getPointerId(pointerIndex);

				if (pointerId == mActivePointerId) {
					// This was our active pointer going up. Choose a new
					// active pointer and adjust accordingly.
					final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
					mActivePointerId = event.getPointerId(newPointerIndex);
				}

				break;
			case MotionEvent.ACTION_MOVE:
				if (mActivePointerId == INVALID_POINTER_ID) {
					break;
				}

				float ex = event.getX();
				float ey = event.getY();

				if (isClick(mADownTouchX, ex, mADownTouchY, ey)) {
					break;
				}

				// Find the index of the active pointer and kinship its position
				final int pointerIndexMove = event.findPointerIndex(mActivePointerId);
				final float xMove = event.getX(pointerIndexMove);
				final float yMove = event.getY(pointerIndexMove);

				//from http://android-developers.blogspot.com/2010/06/making-sense-of-multitouch.html
				// Calculate the distance moved
				final float dx = xMove - mADownTouchX;
				final float dy = yMove - mADownTouchY;

				// Move the mFrame
				mAPosX += dx;
				mAPosY += dy;

				// calculate the rotation degrees
				float distobjectX = mAPosX - mObjectX;
				float rotation = mBaseRotationDegrees * 2.f * distobjectX / mParentWidth;
				if (mTouchPosition == TOUCH_BELOW) {
					rotation = -rotation;
				}

				//in this area would be code for doing something with the view as the mFrame moves.
				mFrame.setX(mAPosX);
				mFrame.setY(mAPosY);
				mFrame.setRotation(rotation);
				mSwipeListener.onScroll(getScrollProgressPercent());
				break;

			case MotionEvent.ACTION_CANCEL: {
				mActivePointerId = INVALID_POINTER_ID;
				view.getParent().requestDisallowInterceptTouchEvent(false);
				break;
			}
		}

		return true;
	}

	private boolean isClick(float startX, float endX, float startY, float endY) {
		float differenceX = Math.abs(startX - endX);
		float differenceY = Math.abs(startY - endY);

		return differenceX == 0 && differenceY == 0;
	}

	private float getScrollProgressPercent() {
		if (movedBeyondLeftBorder()) {
			return -1f;
		} else if (movedBeyondRightBorder()) {
			return 1f;
		} else {
			float zeroToOneValue = (mAPosX + mHalfWidth - leftBorder()) / (rightBorder() - leftBorder());
			return zeroToOneValue * 2f - 1f;
		}
	}

	private boolean resetCardViewOnStack() {
		if (movedBeyondLeftBorder()) {
			// Left Swipe
			onSelected(true, getExitPoint(-mObjectW), 100);
			mSwipeListener.onScroll(-1.0f);
		} else if (movedBeyondRightBorder()) {
			// Right Swipe
			onSelected(false, getExitPoint(mParentWidth), 100);
			mSwipeListener.onScroll(1.0f);
		} else {
			mAPosX = 0;
			mAPosY = 0;
			mADownTouchX = 0;
			mADownTouchY = 0;
			mFrame.animate()
					.setDuration(200)
					.setInterpolator(new OvershootInterpolator(1.5f))
					.x(mObjectX)
					.y(mObjectY)
					.rotation(0);

			mSwipeListener.onScroll(0.0f);
		}
		return false;
	}

	private boolean movedBeyondLeftBorder() {
		return mAPosX + mHalfWidth < leftBorder();
	}

	private boolean movedBeyondRightBorder() {
		return mAPosX + mHalfWidth > rightBorder();
	}

	public float leftBorder() {
		return mParentWidth / 4.f;
	}

	public float rightBorder() {
		return 3 * mParentWidth / 4.f;
	}

	public void onSelected(final boolean isLeft,
						   float exitY, long duration) {

		mIsAnimationRunning = true;

		float exitX;

		if (isLeft) {
			exitX = -mObjectW - getRotationWidthOffset();
		} else {
			exitX = mParentWidth + getRotationWidthOffset();
		}

		animate(duration, isLeft, exitX, exitY);
	}

	public void onSelectedByClick(final boolean isLeft,
								  float exitY, long duration) {
		mIsAnimationRunning = true;

		float exitX;

		if (isLeft) {
			exitX = -mObjectW - getRotationWidthOffset();
			mSwipeListener.onBeforeLeftCardExit(() -> animate(duration, true, exitX, exitY));
		} else {
			exitX = mParentWidth + getRotationWidthOffset();
			mSwipeListener.onBeforeRightCardExit(() -> animate(duration, false, exitX, exitY));
		}
	}

	private void animate(long duration, boolean isLeft, float exitX, float exitY) {
		this.mFrame.animate()
				.setDuration(duration)
				.setInterpolator(new AccelerateInterpolator())
				.x(exitX)
				.y(exitY)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						if (isLeft) {
							mSwipeListener.onCardExited();
							mSwipeListener.leftExit(mDataObject);
						} else {
							mSwipeListener.onCardExited();
							mSwipeListener.rightExit(mDataObject);
						}

						mSwipeListener.onTouchUp();

						mIsAnimationRunning = false;
					}
				})
				.rotation(getExitRotation(isLeft));
	}

	/**
	 * Starts a default left exit animation.
	 */
	public void selectLeft() {
		if (!mIsAnimationRunning) {
			onSelectedByClick(true, mObjectY, 200);
		}
	}

	/**
	 * Starts a default right exit animation.
	 */
	public void selectRight() {
		if (!mIsAnimationRunning) {
			onSelectedByClick(false, mObjectY, 200);
		}
	}

	private float getExitPoint(int exitXPoint) {
		float[] x = new float[2];
		x[0] = mObjectX;
		x[1] = mAPosX;

		float[] y = new float[2];
		y[0] = mObjectY;
		y[1] = mAPosY;

		LinearRegression regression = new LinearRegression(x, y);

		//Your typical y = ax+b linear regression
		return (float) regression.slope() * exitXPoint + (float) regression.intercept();
	}

	private float getExitRotation(boolean isLeft) {
		float rotation = mBaseRotationDegrees * 2.f * (mParentWidth - mObjectX) / mParentWidth;
		if (mTouchPosition == TOUCH_BELOW) {
			rotation = -rotation;
		}
		if (isLeft) {
			rotation = -rotation;
		}
		return rotation;
	}

	/**
	 * When the object rotates it's width becomes bigger.
	 * The maximum width is at 45 degrees.
	 * <p/>
	 * The below method calculates the width offset of the rotation.
	 */
	private float getRotationWidthOffset() {
		return mObjectW / mMaxCos - mObjectW;
	}

	public boolean isTouching() {
		return this.mActivePointerId != INVALID_POINTER_ID;
	}

	public PointF getLastPoint() {
		return new PointF(this.mAPosX, this.mAPosY);
	}

	protected interface SwipeListener {
		void onTouchDown();

		void onTouchUp();

		void onCardExited();

		void onBeforeLeftCardExit(Action done);

		void onBeforeRightCardExit(Action done);

		void leftExit(Object dataObject);

		void rightExit(Object dataObject);

		void onClick(Object dataObject);

		void onScroll(float scrollProgressPercent);
	}

}





