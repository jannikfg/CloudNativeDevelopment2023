FROM maven:3-amazoncorretto-17 AS builder

WORKDIR /tmp
COPY pom.xml /tmp
RUN mvn dependency:go-offline -B
COPY src /tmp/src/
RUN mvn package -DskipTests

FROM registry.access.redhat.com/ubi8/openjdk-17:1.17 AS app
ENV LANGUAGE='en_US:en'
# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --from=builder --chown=185 tmp/target/quarkus-app/lib/ /deployments/lib/
COPY --from=builder --chown=185 tmp/target/quarkus-app/*.jar /deployments/
COPY --from=builder --chown=185 tmp/target/quarkus-app/app/ /deployments/app/
COPY --from=builder --chown=185 tmp/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]