# Crawler

## Description

This application will visit all pages within a given domain (e.g. http://www.monzo.com) and print a JSON representation of the links between the site's pages.

## Running the application

Via Gradle

```
./gradlew run --args='http://www.monzo.com'
```

Via Docker

```
docker run dermotmburke/crawler http://www.monzo.com
```

## Building the application

```
./gradlew test
```
