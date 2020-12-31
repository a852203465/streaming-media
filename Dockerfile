# 基础镜像
FROM ubuntu:18.04

# 作者
MAINTAINER Rong.Jia "852203465@qq.com"

# 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# 工作目录
WORKDIR /home

# 添加JDK 包
ADD jdk-8u202-linux-x64.tar.gz /home/jdk

# 配置JDK 环境
ENV JAVA_HOME=/home/jdk/jdk1.8.0_202
ENV JRE_HOME=/home/jdk/jdk1.8.0_202/jre
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH=$JAVA_HOME/bin:$PATH

# 配置SRS
ADD srs /home/srs-3.0/srs
ADD srs.conf /home/srs-3.0/srs.conf

# ffmpeg 配置
ADD ffmpeg /home/ffmpeg/bin/ffmpeg
ADD ffmpeg-model.tar.gz /usr/local/share

# 添加APP
ADD target/streaming-media-1.0.0.jar /home/app/app.jar

# 添加运行脚本
ADD entrypoint.sh /home/start/entrypoint.sh

# 创建SRS srs.pid 文件
RUN mkdir -p /home/srs-3.0 && touch /home/srs-3.0/srs.pid && mkdir /home/srs-3.0/objs

# 暴露端口
EXPOSE 8080 9000 1935 8081

# 运行脚本
ENTRYPOINT ["/home/start/entrypoint.sh"]




