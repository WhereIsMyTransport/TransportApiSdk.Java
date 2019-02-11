# TransportApiSdk - WhereIsMyTransport API Client Library for Java

[![Version](https://img.shields.io/maven-central/v/com.whereismytransport.transportapisdk/transportapisdk.svg?style=flat)](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22transportapisdk%22)

The official Java SDK for the [WhereIsMyTransport](https://www.whereismytransport.com) API. 

Access to the platform is free, so for more information and to get credentials, just visit the [developer portal](https://developer.whereismytransport.com).

## Usage

```java

// Setup your credentials.
String clientId = "CLIENT_ID";
String clientSecret = "CLIENT_SECRET";

// Define the api client.
TransportApiClient defaultClient = new TransportApiClient(new TransportApiClientSettings(clientId, clientSecret));

// Make an api call to get agencies:
TransportApiResult<List<Agency>> agencies = defaultClient.getAgencies(AgencyQueryOptions.defaultQueryOptions());

// Make an api call to get journeys:
// Note: The coordinate order expected is "Latitude", "Longitude"

// Example of a journey call made in Cape Town, South Africa (Southern Hemisphere)
TransportApiResult<Journey> journey = client.postJourney(null, -33.986342, 18.468806, -33.918842, 18.389256, "");

// Example of a journey call made in Cairo, Egypt (Northern Hemisphere)
TransportApiResult<Journey> journey = client.postJourney(null, 30.07444, 31.26091, 30.01203, 31.25061, "");

// Do fancy things with the results.
```

## Features

The following end-points are available:

* POST api/journeys
* GET api/journeys/{id}
* GET api/journeys/{id}/itineraries/{id}
** Note: Itineraries are only available for up to 30 days after a journey request is made. Requesting past this period will result in a 404 Not Found.
* GET api/agencies
* GET api/agencies/{id}
* GET api/stops
* GET api/stops/{id}
* GET api/stops/{id}/timetables
* GET api/lines
* GET api/lines/{id}
* GET api/lines/{id}/timetables
* GET api/fareproducts
* GET api/fareproducts/{id}

## Installation

Download [the latest JAR](https://search.maven.org/remotecontent?filepath=com/whereismytransport/transportapisdk/transportapisdk/1.0.2/transportapisdk-1.0.2.jar) or grab via Maven:
```xml
<dependency>
  <groupId>com.whereismytransport.transportapisdk</groupId>
  <artifactId>transportapisdk</artifactId>
  <version>1.0.2</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.whereismytransport.transportapisdk:transportapisdk:1.0.2'
```

## Author

Chris King - https://twitter.com/crkingza

## License

TransportApiSdk is available under the MIT license. See the LICENSE file for more info.
