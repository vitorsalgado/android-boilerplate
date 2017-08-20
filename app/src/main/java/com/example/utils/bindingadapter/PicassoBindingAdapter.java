package com.example.utils.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.utils.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

public class PicassoBindingAdapter {
	@BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
	public static void setImageUrl(ImageView view, String url, int placeHolder) {
		if (url == null) {
			view.setImageDrawable(null);
			return;
		}

		RequestCreator requestCreator = Picasso.with(view.getContext()).load(url);

		if (placeHolder != 0) {
			requestCreator.placeholder(placeHolder);
		}

		requestCreator.into(view);
	}

	@BindingAdapter(value = {"imagePath", "placeHolder"}, requireAll = false)
	public static void setImagePath(ImageView view, String path, int placeHolder) {
		if (path == null) {
			view.setImageDrawable(null);
			return;
		}

		RequestCreator requestCreator = Picasso.with(view.getContext()).load(new File(view.getContext().getFilesDir(), path));

		if (placeHolder != 0) {
			requestCreator.placeholder(placeHolder);
		}

		requestCreator.into(view);
	}

	@BindingAdapter(value = {"circleImagePath", "placeHolder"}, requireAll = false)
	public static void setCircleImagePath(ImageView view, String path, int placeHolder) {
		if (path == null) {
			view.setImageDrawable(null);
			return;
		}

		RequestCreator requestCreator = Picasso
				.with(view.getContext())
				.load(new File(view.getContext().getFilesDir(), path))
				.transform(new CircleTransformation());

		if (placeHolder != 0) {
			requestCreator.placeholder(placeHolder);
		}

		requestCreator.into(view);
	}

	@BindingAdapter(value = {"roundedImageUrl"})
	public static void setRoundedImageUrl(ImageView view, String url) {
		if (url == null) {
			view.setImageDrawable(null);
			return;
		}

		RequestCreator requestCreator = Picasso
				.with(view.getContext())
				.load(url);

		requestCreator.into(view);
	}
}
