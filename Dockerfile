FROM openjdk:8-jdk-alpine
COPY ./target/erp-accounts-accounts-be.jar erp-accounts-accounts-be.jar 
ENV JAVA_OPTS="-Xmx512mg -Xms256m" 
ADD VERSION .
ENTRYPOINT ["java","-jar","erp-accounts-accounts-be.jar"]
