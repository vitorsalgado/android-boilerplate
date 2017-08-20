package com.example.utils.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import static com.example.utils.Preconditions.checkNotNull;

public class CircleTransformation implements com.squareup.picasso.Transformation {
	@Override
	public Bitmap transform(Bitmap source) {
		checkNotNull(source, "source cannot be null.");

		final int size = Math.min(source.getWidth(), source.getHeight());
		final int x = (source.getWidth() - size) / 2;
		final int y = (source.getHeight() - size) / 2;

		Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
		Bitmap.Config sourceConfig = source.getConfig();

		if (squaredBitmap != source) {
			source.recycle();
		}

		Bitmap bitmap = Bitmap.createBitmap(size, size, sourceConfig);

		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

		paint.setShader(shader);
		paint.setAntiAlias(true);

		float r = size / 2f;

		canvas.drawCircle(r, r, r, paint);
		squaredBitmap.recycle();

		return bitmap;
	}

	@Override
	public String key() {
		return "circle";
	}
}