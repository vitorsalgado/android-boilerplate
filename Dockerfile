FROM centos

ENV GRADLE_VERSION gradle-3.3
ENV ANT_VERSION apache-ant-1.9.7
ENV JAVA_VERSION java-1.8.0-openjdk

RUN yum -y groupinstall "Development Tools" && \ 
    yum -y update && \ 
    yum -y install $JAVA_VERSION-devel && \ 
    yum -y install expect && \ 
    yum -y install glibc.i686 && \ 
    yum -y install libstdc++.i686 && \
    yum -y install glibc-devel.i686 && \ 
    yum -y install zlib-devel.i686 && \ 
    yum -y install ncurses-devel.i686 && \ 
    yum -y install libX11-devel.i686 && \ 
    yum -y install libXrender.i686 && \

    cd /usr/local/ && curl -L -O http://dl.google.com/android/android-sdk_r24.4.1-linux.tgz && tar xf android-sdk_r24.4.1-linux.tgz && \ 
    cd /usr/local/ && curl -L -O https://dl.google.com/android/repository/platform-25_r02.zip && unzip -o platform-25_r02.zip && \ 
    
    echo y | /usr/local/android-sdk-linux/tools/android update sdk --no-ui --force -a --filter tools && \ 
    echo y | /usr/local/android-sdk-linux/tools/android update sdk --no-ui --force -a --filter platform-tools && \ 
    echo y | /usr/local/android-sdk-linux/tools/android update sdk --no-ui --force -a --filter build-tools-25.0.2 && \ 
    echo y | /usr/local/android-sdk-linux/tools/android update sdk --no-ui --force -a --filter android-25 && \ 
    echo y | /usr/local/android-sdk-linux/tools/android update sdk --no-ui --force -a --filter extra && \ 
    echo y | /usr/local/android-sdk-linux/tools/android update sdk --no-ui --force -a --filter sys-img-armeabi-v7a-android-19 && \ 
    
    cd /usr/local/ && curl -L -O https://www.apache.org/dist/ant/binaries/$ANT_VERSION-bin.tar.gz && tar xf $ANT_VERSION-bin.tar.gz && \ 
    cd /usr/local/ && curl -L -O http://services.gradle.org/distributions/$GRADLE_VERSION-all.zip && unzip -o $GRADLE_VERSION-all.zip

ENV JAVA_HOME /usr/lib/jvm/$JAVA_VERSION
ENV ANDROID_HOME /usr/local/android-sdk-linux
ENV ANT_HOME /usr/local/apache-ant-1.9.6
ENV GRADLE_HOME /usr/local/$GRADLE_VERSION
ENV PATH $PATH:$ANDROID_HOME/tools
ENV PATH $PATH:$ANDROID_HOME/platform-tools
ENV PATH $PATH:$ANT_HOME/bin
ENV PATH $PATH:$MAVEN_HOME/bin
ENV PATH $PATH:$GRADLE_HOME/bin

RUN rm -rf /usr/local/android-sdk_r24.4.1-linux.tgz && \
    rm -rf /usr/local/platform-25_r02.zip && \
    rm -rf /usr/local/android-ndk-r9b-linux-x86_64.tar.bz2 && \
    rm -rf /usr/local/apache-ant-1.9.6-bin.tar.gz && \
    rm -rf /usr/local/apache-maven-3.1.1-bin.tar.gz && \ 
    rm -rf /usr/local/$GRADLE_VERSION-all.zip && \ 
    yum clean all