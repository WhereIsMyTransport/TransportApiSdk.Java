package transportapisdk;

import transportapisdk.models.*;
import java.util.List;

public class TransportApiClient {
	
	private TokenComponent tokenComponent;
	private int timeoutInSeconds;

    public TransportApiClient(TransportApiClientSettings settings)
    {
    	if (settings == null)
    	{
    		throw new IllegalArgumentException("Settings cannot be null.");
    	}
    	
    	this.timeoutInSeconds = settings.timeoutInSeconds;
    	this.tokenComponent = new TokenComponent(settings.clientId, settings.clientSecret);
    }
    
    /**
     * Gets a list of agencies nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: JourneyBodyOptions.defaultQueryOptions()
     * @param  startLatitude	Latitude in decimal degrees to depart from.
     * @param  startLongitude	Longitude in decimal degrees to depart from.
     * @param  endLatitude		Latitude in decimal degrees of the desitnation.
     * @param  endLongitude		Longitude in decimal degrees of the desitnation.
     * @param  exclude			Entities to exclude from the call to reduce the payload. See https://developer.whereismytransport.com/documentation#excluding-data
     * @return      			A journey from A to B using public TransportApiResult<transport.
     */
    public TransportApiResult<Journey> postJourney(JourneyBodyOptions options, double startLatitude, double startLongitude, double endLatitude, double endLongitude, String exclude)
    {
    	if (options == null)
    	{
    		options = JourneyBodyOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.postJourney(tokenComponent, timeoutInSeconds, options, new Point(startLongitude, startLatitude), new Point(endLongitude, endLatitude), exclude);
    }
    
    /**
     * Gets a journey previously requested through the POST journey call.
     *
     * @param  journeyId  	The id of the journey you want to get. Previously made in a POST journey call.
     * @param  exclude  	Entities to exclude from the call to reduce the payload. See https://developer.whereismytransport.com/documentation#excluding-data
     * @return      		A previously requested journey.
     */
    public TransportApiResult<Journey> getJourney(String journeyId, String exclude)
    {
    	if (Extensions.isNullOrWhiteSpace(journeyId))
    	{
    		throw new IllegalArgumentException("JourneyId is required.");
    	}
    	
    	return TransportApiClientCalls.getJourney(tokenComponent, timeoutInSeconds, journeyId, exclude);
    }
    
    /**
     * Gets a specific itinerary of a journey previously requested through the POST journey call.
     *
     * @param  journeyId  	The id of the journey you want to get an itinerary for. Previously made in a POST journey call.
     * @param  itineraryId 	The id of the itinerary you want to get. Previously made in a POST journey call.
     * @param  exclude  	Entities to exclude from the call to reduce the payload. See https://developer.whereismytransport.com/documentation#excluding-data
     * @return      		A previously requested itinerary.
     */
    public TransportApiResult<Itinerary> getItinerary(String journeyId, String itineraryId, String exclude)
    {
    	if (Extensions.isNullOrWhiteSpace(journeyId))
    	{
    		throw new IllegalArgumentException("JourneyId is required.");
    	}
    	
    	if (Extensions.isNullOrWhiteSpace(itineraryId))
    	{
    		throw new IllegalArgumentException("ItineraryId is required.");
    	}
    	
    	return TransportApiClientCalls.getItinerary(tokenComponent, timeoutInSeconds, journeyId, itineraryId, exclude);
    }
    
    /**
     * Gets a list of all agencies in the system.
     *
     * @param  options  Options to limit the results by. Default: AgencyQueryOptions.defaultQueryOptions()
     * @return      	A list of all agencies.
     */
    public TransportApiResult<List<Agency>> getAgencies(AgencyQueryOptions options)
    {
    	if (options == null)
    	{
    		options = AgencyQueryOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.getAgencies(tokenComponent, timeoutInSeconds, options, null, null, null);
    }
    
    /**
     * Gets a list of agencies nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: AgencyQueryOptions.defaultQueryOptions()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of agencies nearby the specified point.
     */
    public TransportApiResult<List<Agency>> getAgenciesNearby(AgencyQueryOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = AgencyQueryOptions.defaultQueryOptions();
    	}
    	
    	if (radiusInMeters < 0)
    	{
    		throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers only.");
    	}
    	
    	return TransportApiClientCalls.getAgencies(tokenComponent, timeoutInSeconds, options, new Point(longitude, latitude), radiusInMeters, null);
    }
    
    /**
     * Gets a list of all agencies within a bounding box.
     *
     * @param  options  	Options to limit the results by. Default: AgencyQueryOptions.defaultQueryOptions()
     * @param  boundingBox 	The bounding box from where to retrieve agencies. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of agencies within a bounding box.
     */
    public TransportApiResult<List<Agency>> getAgenciesByBoundingBox(AgencyQueryOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = AgencyQueryOptions.defaultQueryOptions();
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
    	
    	return TransportApiClientCalls.getAgencies(tokenComponent, timeoutInSeconds, options, null, null, boundingBox);
    }
    
    /**
     * Gets a specific agency.
     *
     * @param  agencyId  	The id of the agency you want to get.
     * @return      		An agency.
     */
    public TransportApiResult<Agency> getAgency(String agencyId)
    {
    	if (Extensions.isNullOrWhiteSpace(agencyId))
    	{
    		throw new IllegalArgumentException("AgencyId is required.");
    	}
    	
    	return TransportApiClientCalls.getAgency(tokenComponent, timeoutInSeconds, agencyId);
    }
    
    /**
     * Gets a list of all lines in the system.
     *
     * @param  options  Options to limit the results by. Default: LineQueryOptions.defaultQueryOptions()
     * @return      	A list of all lines.
     */
    public TransportApiResult<List<Line>> getLines(LineQueryOptions options)
    {
    	if (options == null)
    	{
    		options = LineQueryOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.getLines(tokenComponent, timeoutInSeconds, options, null, null, null);
    }
    
    /**
     * Gets a list of lines nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: LineQueryOptions.defaultQueryOptions()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of lines nearby the specified point.
     */
    public TransportApiResult<List<Line>> getLinesNearby(LineQueryOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = LineQueryOptions.defaultQueryOptions();
    	}
    	
    	if (radiusInMeters < 0)
    	{
    		throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers only.");
    	}
    	
    	return TransportApiClientCalls.getLines(tokenComponent, timeoutInSeconds, options, new Point(longitude, latitude), radiusInMeters, null);
    }
    
    /**
     * Gets a list of all lines within a bounding box.
     *
     * @param  options  	Options to limit the results by. Default: LineQueryOptions.defaultQueryOptions()
     * @param  boundingBox 	The bounding box from where to retrieve lines. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of lines within a bounding box.
     */
    public TransportApiResult<List<Line>> getLinesByBoundingBox(LineQueryOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = LineQueryOptions.defaultQueryOptions();
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
    	
    	return TransportApiClientCalls.getLines(tokenComponent, timeoutInSeconds, options, null, null, boundingBox);
    }
    
    /**
     * Gets a specific line.
     *
     * @param  lineId  	The id of the line you want to get.
     * @return      	An line.
     */
    public TransportApiResult<Line> getLine(String lineId)
    {
    	if (Extensions.isNullOrWhiteSpace(lineId))
    	{
    		throw new IllegalArgumentException("LineId is required.");
    	}
    	
    	return TransportApiClientCalls.getLine(tokenComponent, timeoutInSeconds, lineId);
    }
    
    /**
     * Gets a timetable for a specific line.
     *
     * @param  stopId  	The id of the line you want to get a timetable for.
     * @param  options  Options to limit the results by. Default: LineTimetableQueryOptions.defaultQueryOptions()
     * @return      	The line timetable.
     */
    public TransportApiResult<List<LineTimetable>> getLineTimetable(String lineId, LineTimetableQueryOptions options)
    {
    	if (Extensions.isNullOrWhiteSpace(lineId))
    	{
    		throw new IllegalArgumentException("LineId is required.");
    	}
    	
    	if (options == null)
    	{
    		options = LineTimetableQueryOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.getLineTimetable(tokenComponent, timeoutInSeconds, lineId, options);
    }
    
    /**
     * Gets a list of all stops in the system.
     *
     * @param  options  Options to limit the results by. Default: StopQueryOptions.defaultQueryOptions()
     * @return      	A list of all stops.
     */
    public TransportApiResult<List<Stop>> getStops(StopQueryOptions options)
    {
    	if (options == null)
    	{
    		options = StopQueryOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.getStops(tokenComponent, timeoutInSeconds, options, null, null, null);
    }
    
    /**
     * Gets a list of stops nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: StopQueryOptions.defaultQueryOptions()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of stops nearby the specified point.
     */
    public TransportApiResult<List<Stop>> getStopsNearby(StopQueryOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = StopQueryOptions.defaultQueryOptions();
    	}
    	
    	if (radiusInMeters < 0)
    	{
    		throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers only.");
    	}
    	
    	return TransportApiClientCalls.getStops(tokenComponent, timeoutInSeconds, options, new Point(longitude, latitude), radiusInMeters, null);
    }
    
    /**
     * Gets a list of all stops within a bounding box.
     *
     * @param  options  	Options to limit the results by. Default: StopQueryOptions.defaultQueryOptions()
     * @param  boundingBox 	The bounding box from where to retrieve stop. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of stop within a bounding box.
     */
    public TransportApiResult<List<Stop>> getStopsByBoundingBox(StopQueryOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = StopQueryOptions.defaultQueryOptions();
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
    	
    	return TransportApiClientCalls.getStops(tokenComponent, timeoutInSeconds, options, null, null, boundingBox);
    }
    
    /**
     * Gets a specific stop.
     *
     * @param  stopId  	The id of the stop you want to get.
     * @return      	An stop.
     */
    public TransportApiResult<Stop> getStop(String stopId)
    {
    	if (Extensions.isNullOrWhiteSpace(stopId))
    	{
    		throw new IllegalArgumentException("StopId is required.");
    	}
    	
    	return TransportApiClientCalls.getStop(tokenComponent, timeoutInSeconds, stopId);
    }
    
    /**
     * Gets a timetable for a specific stop.
     *
     * @param  stopId  	The id of the stop you want to get a timetable for.
     * @param  options  Options to limit the results by. Default: StopTimetableQueryOptions.defaultQueryOptions()
     * @return      	The stop timetable.
     */
    public TransportApiResult<List<StopTimetable>> getStopTimetable(String stopId, StopTimetableQueryOptions options)
    {
    	if (Extensions.isNullOrWhiteSpace(stopId))
    	{
    		throw new IllegalArgumentException("StopId is required.");
    	}
    	
    	if (options == null)
    	{
    		options = StopTimetableQueryOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.getStopTimetable(tokenComponent, timeoutInSeconds, stopId, options);
    }
    
    /**
     * Gets a list of all fare products in the system.
     *
     * @param  options  Options to limit the results by. Default: FareProductQueryOptions.defaultQueryOptions()
     * @return      	A list of all fare products.
     */
    public TransportApiResult<List<FareProduct>> getFareProducts(FareProductQueryOptions options)
    {
    	if (options == null)
    	{
    		options = FareProductQueryOptions.defaultQueryOptions();
    	}
    	
    	return TransportApiClientCalls.getFareProducts(tokenComponent, timeoutInSeconds, options);
    }
    
    /**
     * Gets a specific fare product.
     *
     * @param  fareProductId  	The id of the fare product you want to get.
     * @return      			An fare product.
     */
    public TransportApiResult<FareProduct> getFareProduct(String fareProductId)
    {
    	if (Extensions.isNullOrWhiteSpace(fareProductId))
    	{
    		throw new IllegalArgumentException("FareProductId is required.");
    	}
    	
    	return TransportApiClientCalls.getFareProduct(tokenComponent, timeoutInSeconds, fareProductId);
    }
}
