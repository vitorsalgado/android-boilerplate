package com.example.utils.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import rx.functions.Action1;

public final class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private static final String TAG = "EndlessRecyclerOnScroll";

    private int mPreviousTotal = 0;
    private boolean mLoading = true;
    private int mVisibleThreshold = 10;

    int mFirstVisibleItem;
    int mVisibleItemCount;
    int mTotalItemCount;

    private int mNextPage;

    private final LinearLayoutManager mLinearLayoutManager;
    private final Action1<Integer> mLoader;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, Action1<Integer> loader) {
        mLinearLayoutManager = linearLayoutManager;
        mLoader = loader;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mVisibleItemCount = recyclerView.getChildCount();
        mTotalItemCount = mLinearLayoutManager.getItemCount();
        mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (mLoading && mTotalItemCount > mPreviousTotal) {
            mLoading = false;
            mPreviousTotal = mTotalItemCount;
        }

        if (!mLoading && (mTotalItemCount - mVisibleItemCount) <= (mFirstVisibleItem + mVisibleThreshold)) {
            load();
        }
    }

    public void load() {
        mLoader.call(mNextPage++);
        mLoading = true;
    }

    public EndlessRecyclerOnScrollListener setVisibleThreshold(int mVisibleThreshold) {
        this.mVisibleThreshold = mVisibleThreshold;
        return this;
    }

    public EndlessRecyclerOnScrollListener setNextPage(int mNextPage) {
        Log.d(TAG, "setNextPage() called with: mNextPage = [" + mNextPage + "]");

        this.mNextPage = mNextPage;

        return this;
    }

    public int getNextPage() {
        Log.d(TAG, "getNextPage() returned: " + mNextPage);

        return mNextPage;
    }

    public void loadFirstIfNeeded() {
        if (mNextPage == 1) {
            load();
        }
    }
}
