FROM java:8-jre

ENV JAVA_MEM_OPTS="-Xms128M -Xmx256M -XX:+PrintGCDetails"
ENV TELEMETRY_LOG_LEVEL="INFO"

ADD ./target/finangular-0.0.1-SNAPSHOT-dist/finangular-0.0.1-SNAPSHOT /finangular/

CMD [ "sh", "-c", "/finangular/bin/run.sh" ]

EXPOSE 18100 18106