FROM openjdk:11

ENV BROKER_URL=''
ENV JAR_NAME='price-service-0.0.1.jar'

COPY ./target/$JAR_NAME /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT java -jar $JAR_NAME \
	--broker-url=$BROKER_URL
