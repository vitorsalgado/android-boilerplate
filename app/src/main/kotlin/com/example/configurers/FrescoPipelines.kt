package com.example.configurers

import android.content.Context
import android.graphics.Bitmap

import com.example.BuildConfig
import com.example.android.utils.LogUtility
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig

object FrescoPipelines {
  fun setup(context: Context): ImagePipelineConfig.Builder {
    return ImagePipelineConfig.newBuilder(context)
      .setBitmapsConfig(Bitmap.Config.RGB_565)
      .setMainDiskCacheConfig(
        DiskCacheConfig.newBuilder(context)
          .setCacheErrorLogger { _, _, message, throwable -> LogUtility.e(message, throwable!!) }
          .setMaxCacheSize((100 * 1000 * 1000).toLong())
          .setVersion(BuildConfig.VERSION_CODE)
          .build()
      )
      .setBitmapMemoryCacheParamsSupplier { MemoryCacheParams(32 * 1000 * 1000, 100, 5 * 1000 * 1000, 10, 250 * 1000) }
  }
}
