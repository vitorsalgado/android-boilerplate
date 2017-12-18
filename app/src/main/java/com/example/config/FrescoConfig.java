package com.example.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.BuildConfig;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import javax.annotation.Nullable;

public final class FrescoConfig {
	public static ImagePipelineConfig.Builder setupFresco(@NonNull Context context) {
		return ImagePipelineConfig.newBuilder(context)
			.setBitmapsConfig(Bitmap.Config.RGB_565)
			.setMainDiskCacheConfig(
				DiskCacheConfig.newBuilder(context)
					.setCacheErrorLogger(new CacheErrorLogger() {
						@Override
						public void logError(CacheErrorCategory category, Class<?> clazz, String message, @Nullable Throwable throwable) {
							Log.e(clazz.getSimpleName(), message, throwable);
						}
					})
					.setMaxCacheSize(100 * 1000 * 1000)
					.setVersion(BuildConfig.VERSION_CODE)
					.build()
			)
			.setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
				@Override
				public MemoryCacheParams get() {
					return new MemoryCacheParams(32 * 1000 * 1000, 100, 5 * 1000 * 1000, 10, 250 * 1000);
				}
			});
	}
}
