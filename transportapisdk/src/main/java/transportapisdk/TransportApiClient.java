package transportapisdk;

import transportapisdk.models.*;
import java.util.List;

public class TransportApiClient {
	
	private TokenComponent tokenComponent;

    public TransportApiClient(TransportApiClientSettings settings)
    {
    	if (settings == null)
    	{
    		throw new IllegalArgumentException("Settings cannot be null.");
    	}
    	
    	this.tokenComponent = new TokenComponent(settings.ClientId, settings.ClientSecret);
    }
    
    public List<Agency> GetAgencies(AgencyOptions options)
    {
    	if (options == null)
    	{
    		options = AgencyOptions.Default();
    	}
    	
    	return TransportApiClientCalls.GetAgencies(tokenComponent, options, null, null, null);
    }
    
    public List<Agency> GetAgenciesNearby(AgencyOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = AgencyOptions.Default();
    	}
    	
    	if (radiusInMeters < 0)
    	{
    		throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers only.");
    	}
    	
    	return TransportApiClientCalls.GetAgencies(tokenComponent, options, new Point(longitude, latitude), radiusInMeters, null);
    }
    
    /**
     * Gets a list of all agencies within a bounding box.
     *
     * @param  options  	options to limit the results by.
     * @param  boundingBox 	the bounding box from where to retrieve agencies. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		a list of agencies within a bounding box.
     */
    public List<Agency> GetAgenciesByBoundingBox(AgencyOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = AgencyOptions.Default();
    	}
    	
    	if (boundingBox == null)
    	{
    		throw new IllegalArgumentException("BoundingBox is required.");
    	}
    	
    	String[] bbox = boundingBox.split(",", -1);
    	if (bbox.length != 4)
    	{
    		throw new IllegalArgumentException("Invalid bounding box. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.");
    	}
    	
    	return TransportApiClientCalls.GetAgencies(tokenComponent, options, null, null, boundingBox);
    }

   /* interface TapiApiInterface {

    	@Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("agencies")
    Call<List<Agency>>  getAgencies();

   /* 	
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("api/agencies?point={point}&radius={radius}&bbox={bbox}&agencies={agencies}&limit={limit}&offset={offset}&at={at}")
        Call<List<Agency>>  getAgencies(@Path("point") Point point, @Path("radius") int radius, @Path("bbox") List<String> bbox,@Path("agencies") List<String> agencies,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at );
*/
/*
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("agencies/{agencyId}?at={at}")
        Call<Agency>  getAgencyByID(@Path("agencyId") String id,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("stops?point={point}&radius={radius}&bbox={bbox}&modes={modes}&agencies={agencies}&servesLines={lineIds}&limit={limit}&offset={offset}&at={at}")
        Call<List<Stop>> getStops(@Path("point") Point point,@Path("radius") int radius,@Path("bbox") List<String> bbox,@Path("modes") List<String> modes,@Path("agencies") List<String> agencies,@Path("servesLine") String servesLine,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);


        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("stops/{stopId}?at={at}")
        Call<Stop> getStopByID(@Path("stopId") String stopId,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("stops/{stopId}/timetables?earliestArrivalTime={earliestArrivalTime}&limit={limit}&at={at}")
        Call<StopTimetable> getStopTimetable(@Path("stopId") String stopId,@Path("earliestArrivalTime") String earliestArrivalTime,@Path("limit") int limit,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines?agencies={agencies}&servesStops={servesStops}&limit={limit}&offset={offset}&at={at}")
        Call<List<Line>> getLines(@Path("agencies") List<String> agencies,@Path("servesStops") String servesStops,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines/{lineId}?at={at}")
        Call<Line> getLineByID(@Path("lineId") String lineId);


        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines/{lineId}/timetables?earliestDepartureTime={earliestDepartureTime}&limit={limit}&at={at}")
        Call<LineTimetable> getLineTimetable(@Path("lineId") String lineId,@Path("earliestDepartureTime") String earliestDepartureTime,@Path("limit") int limit,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines/{lineId}/shape?at={at}")
        Call<LineShape> getLineShape(@Path("lineId") String lineId,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("journeys/{journeyId}?fareproducts={fareProductIds}")
        Call<Journey> getJourneyByID(@Path("journeyId") String journeyId,@Path("fareproducts") String fareProducts);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("journeys/{journeyId}/itineraries/{itineraryId}?fareproducts={fareProductIds}")
        Call<Itinerary> getItineraryByID(@Path("journeyId") String journeyId,@Path("itineraryId") String itineraryId,@Path("fareProductIds") List<String> fareProducts);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("journeys/{journeyId}/itineraries?fareproducts={fareProductIds}")
        Call<Itinerary> getItineraryWithApplicationOfFareProduct(@Path("journeyId") String journeyId,@Path("fareProductIds") List<String> fareProductIds);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("fareproducts?agencies={agencies}&limit={limit}&offset={offset}&at={at}")
        Call<List<FareProduct>> getFareProducts(@Path("agencies") List<String> agencies,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("fareproducts/{fareProductId}/faretables?limit={limit}&offset={offset}&at={at}")
        Call<List<FareTable>> getFareTables(@Path("fareProductId") String fareProductId, @Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("fareproducts/{fareProductId}/faretables/{fareTableId}?limit={limit}&offset={offset}&at={at}")
        Call<FareTable> getFareTableByID(@Path("fareProductId") String fareProductId,@Path("fareTableId") String fareTableId, @Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);


        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @POST("journeys")
        Call<Journey> getJourney(@Body JourneyPOST journey);
    }*/
}
