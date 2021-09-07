FROM openjdk:8
COPY player-market.jar .
EXPOSE 8080
CMD java -jar player-market.jar