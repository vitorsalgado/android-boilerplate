package com.example.trackers

import com.example.android.utils.LogUtility
import com.facebook.cache.common.CacheKey
import com.facebook.imagepipeline.cache.CountingMemoryCache
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker

class FrescoCacheStatsTracker : ImageCacheStatsTracker {
  override fun onBitmapCachePut() {

  }

  override fun onBitmapCacheHit(cacheKey: CacheKey) {
    LogUtility.d("[ Fresco Bitmap Cache Hit ] $cacheKey")
  }

  override fun onBitmapCacheMiss() {

  }

  override fun onMemoryCachePut() {

  }

  override fun onMemoryCacheHit(cacheKey: CacheKey) {
    LogUtility.d("[ Fresco Memory Cache Hit ] $cacheKey")
  }

  override fun onMemoryCacheMiss() {

  }

  override fun onStagingAreaHit(cacheKey: CacheKey) {
    LogUtility.d("[ Fresco Staging Area Hit ] $cacheKey")

  }

  override fun onStagingAreaMiss() {

  }

  override fun onDiskCacheHit() {
    LogUtility.d("[ Fresco Disk Cache Hit ]")
  }

  override fun onDiskCacheMiss() {

  }

  override fun onDiskCacheGetFail() {

  }

  override fun registerBitmapMemoryCache(bitmapMemoryCache: CountingMemoryCache<*, *>) {

  }

  override fun registerEncodedMemoryCache(encodedMemoryCache: CountingMemoryCache<*, *>) {

  }
}
