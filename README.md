#### Launching tests locally: 
```
./gradlew clean test
```

#### Launching tests with Selenium Grid
To launch tests locally on Grid:
```
docker-compose up
./gradlew test -Pbrowser=firefox -Pgrid=localhost
```