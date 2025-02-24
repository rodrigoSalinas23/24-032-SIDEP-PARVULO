FROM frolvlad/alpine-java:jdk8-slim
RUN apk add -U tzdata
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN date
RUN mkdir -p /api-sidep-parvulo/lib/
RUN mkdir -p /api-sidep-parvulo/config/
RUN mkdir -p /api-sidep-parvulo/logs/
ADD bootstrap.yml /api-sidep-parvulo/config
ADD logback.xml /api-sidep-parvulo/config
ADD application-api-sidep-parvulo-messages.properties /api-sidep-parvulo/config
WORKDIR /api-sidep-parvulo
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lib/app.jar"]
EXPOSE 9306
HEALTHCHECK --interval=2s --retries=15 --timeout=2s --start-period=8s CMD curl --connect-timeout 1 -f http://127.0.0.1:9306/api-sidep-parvulo
ADD api-sidep-parvulo.jar /api-sidep-parvulo/lib/app.jar
