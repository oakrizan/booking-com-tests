FROM gradle:6.6.1-jdk14
COPY . .
ENTRYPOINT ["gradle", "test", "-Pgrid=hub", "-Pbrowser=firefox"]