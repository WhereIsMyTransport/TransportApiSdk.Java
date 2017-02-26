# TransportApiSdk - WhereIsMyTransport API Client Library for Java

The unofficial Java SDK for the [WhereIsMyTransport](https://www.whereismytransport.com) API. 

Access to the platform is completely free, so for more information and to get credentials, just visit the [developer portal](https://developer.whereismytransport.com).

## Usage

```java

// Setup your credentials.
String clientId = "CLIENT_ID";
String clientSecret = "CLIENT_SECRET";

// Define the api client.
TransportApiClient defaultClient = new TransportApiClient(new TransportApiClientSettings(clientId, clientSecret));

// Make an api call.
List<Agency> agencies = client.GetAgencies(AgencyQueryOptions.Default());

// Do fancy things with the results.
```

## Features

The following end-points are available:

* POST api/journeys
* GET api/journeys/{id}
* GET api/journeys/{id}/itineraries/{id}
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

NOTE: Neither of these work yet.

Import via Maven:
```xml
<dependency>
  <groupId>com.whereismytransport.transportapisdk</groupId>
  <artifactId>transportapisdk</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.whereismytransport.transportapisdk:transportapisdk:1.0.0'
```

## Author

Chris King - https://twitter.com/crkingza

## License

TransportApiSdk is available under the MIT license. See the LICENSE file for more info.
