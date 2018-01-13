FROM vitorsalgado/android-build-box

ARG ANDROID_HOME

WORKDIR /usr/app

RUN mkdir -p /root/.gradle

VOLUME /usr/app
VOLUME $ANDROID_HOME
VOLUME /root/.gradle
