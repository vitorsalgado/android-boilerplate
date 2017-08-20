package com.example.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;

import static com.example.utils.Preconditions.checkNotNull;

public class FileUtils {
	private final Context mContext;

	public FileUtils(@NonNull Context context) {
		mContext = checkNotNull(context, "context cannot be null.");
	}

	public boolean exists(@NonNull String name) {
		return new File(mContext.getFilesDir(), name).exists();
	}

	public boolean delete(@NonNull String name) {
		return new File(mContext.getFilesDir(), name).delete();
	}

	public void storeImageIfNotExists(@NonNull Bitmap photo, @NonNull Bitmap.CompressFormat compressFormat, int quality, @NonNull String name) {
		File file = new File(mContext.getFilesDir(), name);

		if (file.exists()) {
			return;
		}

		storeImage(photo, compressFormat, quality, name);
	}

	public void storeImage(@NonNull Bitmap photo, @NonNull Bitmap.CompressFormat compressFormat, int quality, @NonNull String name) {
		FileOutputStream outputStream;

		try {
			outputStream = mContext.openFileOutput(name, Context.MODE_PRIVATE);
			photo.compress(compressFormat, quality, outputStream);

			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
