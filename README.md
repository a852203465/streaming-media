# JAVA 推流器

## 环境说明

- 基于 JDK 8, ffmpeg, srs-3.0，mysql 5.7.x, docker, docker-compose

## 配置说明

- 修改“.env”文件配置
```text
# 空闲时间后停止推流，单位秒
DESTROY_DELAY=15
# 服务器网卡IP
SERVER_HOST=192.168.37.128
# MYSQL 
MYSQL_HOST=192.168.37.128
MYSQL_PORT=3306
MYSQL_USERNAME=root
MYSQL_PASSWORD=123456
# 该数据库需要自行创建
MYSQL_DATABASE=stream-media 

# 用于转发dvr产生的文件
WEB_SERVER_HOST=192.168.37.128
WEB_SERVER_PORT=9077
```

- 切换运行环境, 修改application.yml中的配置
```yaml
spring:
  profiles:
    active: pro
```

## 运行说明

- 将项目下载并导入IDE中运行“mvn clean package”命令，
打包完成后将target下的“.tar.gz” 上传至服务器进行解压
- 进入解压后的目录执行”docker-compose up -d --build“ 

## 使用说明

- 访问http:localhost:9000/swagger-ui.html, 注意IP 请替换成运行所在的服务器IP
















