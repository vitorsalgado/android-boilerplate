package com.example.configurers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.BuildConfig;
import com.example.android.utils.LogUtility;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public final class FrescoPipelines {
	public static ImagePipelineConfig.Builder setupFresco(@NonNull Context context) {
		return ImagePipelineConfig.newBuilder(context)
			.setBitmapsConfig(Bitmap.Config.RGB_565)
			.setMainDiskCacheConfig(
				DiskCacheConfig.newBuilder(context)
					.setCacheErrorLogger((category, clazz, message, throwable) -> LogUtility.e(message, throwable))
					.setMaxCacheSize(100 * 1000 * 1000)
					.setVersion(BuildConfig.VERSION_CODE)
					.build()
			)
			.setBitmapMemoryCacheParamsSupplier(() -> new MemoryCacheParams(32 * 1000 * 1000, 100, 5 * 1000 * 1000, 10, 250 * 1000));
	}
}
