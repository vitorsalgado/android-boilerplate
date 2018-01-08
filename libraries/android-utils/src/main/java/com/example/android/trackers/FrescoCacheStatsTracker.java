package com.example.android.trackers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;

import com.example.logger.CLog;

public class FrescoCacheStatsTracker implements ImageCacheStatsTracker {
	@Override
	public void onBitmapCachePut() {

	}

	@Override
	public void onBitmapCacheHit(CacheKey cacheKey) {
		CLog.d("[ Fresco Bitmap Cache Hit ] " + cacheKey);
	}

	@Override
	public void onBitmapCacheMiss() {

	}

	@Override
	public void onMemoryCachePut() {

	}

	@Override
	public void onMemoryCacheHit(CacheKey cacheKey) {
		CLog.d("[ Fresco Memory Cache Hit ] " + cacheKey);
	}

	@Override
	public void onMemoryCacheMiss() {

	}

	@Override
	public void onStagingAreaHit(CacheKey cacheKey) {
		CLog.d("[ Fresco Staging Area Hit ] " + cacheKey);

	}

	@Override
	public void onStagingAreaMiss() {

	}

	@Override
	public void onDiskCacheHit() {
		CLog.d("[ Fresco Disk Cache Hit ]");
	}

	@Override
	public void onDiskCacheMiss() {

	}

	@Override
	public void onDiskCacheGetFail() {

	}

	@Override
	public void registerBitmapMemoryCache(CountingMemoryCache<?, ?> bitmapMemoryCache) {

	}

	@Override
	public void registerEncodedMemoryCache(CountingMemoryCache<?, ?> encodedMemoryCache) {

	}
}
