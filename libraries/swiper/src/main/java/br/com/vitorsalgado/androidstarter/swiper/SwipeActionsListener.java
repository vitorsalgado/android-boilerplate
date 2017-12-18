package br.com.vitorsalgado.androidstarter.swiper;

interface SwipeActionsListener {
	void onTouchDown();

	void onTouchUp();

	void onCardExited();

	void onBeforeLeftCardExit(ActionCallback done);

	void onBeforeRightCardExit(ActionCallback done);

	void leftExit(Object dataObject);

	void rightExit(Object dataObject);

	void onClick(Object dataObject);

	void onScroll(float scrollProgressPercent);
}
