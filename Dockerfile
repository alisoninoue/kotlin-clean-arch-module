FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk11u-nightly-slim
RUN apk --no-cache add curl
COPY build/libs/*-all.jar kotlin-clean-arch.jar
CMD java ${JAVA_OPTS} -jar kotlin-clean-arch.jar