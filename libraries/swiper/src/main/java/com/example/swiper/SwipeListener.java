package com.example.swiper;

public interface SwipeListener<T> {
	void onTouchDown();

	void onTouchUp();

	void removeFirstObjectInAdapter();

	void onBeforeLeftCardExit(ActionCallback done);

	void onBeforeRightCardExit(ActionCallback done);

	void onLeftCardExit(T model);

	void onRightCardExit(T model);

	void onAdapterAboutToEmpty(int itemsInAdapter);

	void onScroll(float scrollProgressPercent);

	void onClick(T model);
}
