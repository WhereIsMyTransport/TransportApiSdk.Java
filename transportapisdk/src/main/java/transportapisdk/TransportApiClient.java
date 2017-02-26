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
    
    /**
     * Gets a list of agencies nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: JourneyBodyOptions.Default()
     * @param  startLatitude	Latitude in decimal degrees to depart from.
     * @param  startLongitude	Longitude in decimal degrees to depart from.
     * @param  endLatitude		Latitude in decimal degrees of the desitnation.
     * @param  endLongitude		Longitude in decimal degrees of the desitnation.
     * @param  exclude			Entities to exclude from the call to reduce the payload. See https://developer.whereismytransport.com/documentation#excluding-data
     * @return      			A journey from A to B using public transport.
     */
    public Journey PostJourney(JourneyBodyOptions options, double startLatitude, double startLongitude, double endLatitude, double endLongitude, String exclude)
    {
    	if (options == null)
    	{
    		options = JourneyBodyOptions.Default();
    	}
    	
    	return TransportApiClientCalls.PostJourney(tokenComponent, options, new Point(startLongitude, startLatitude), new Point(endLongitude, endLatitude), exclude);
    }
    
    /**
     * Gets a journey previously requested through the POST journey call.
     *
     * @param  journeyId  	The id of the journey you want to get. Previously made in a POST journey call.
     * @param  exclude  	Entities to exclude from the call to reduce the payload. See https://developer.whereismytransport.com/documentation#excluding-data
     * @return      		A previously requested journey.
     */
    public Journey GetJourney(String journeyId, String exclude)
    {
    	if (journeyId == null || journeyId.isEmpty())
    	{
    		throw new IllegalArgumentException("JourneyId is required.");
    	}
    	
    	return TransportApiClientCalls.GetJourney(tokenComponent, journeyId, exclude);
    }
    
    /**
     * Gets a specific itinerary of a journey previously requested through the POST journey call.
     *
     * @param  journeyId  	The id of the journey you want to get an itinerary for. Previously made in a POST journey call.
     * @param  itineraryId 	The id of the itinerary you want to get. Previously made in a POST journey call.
     * @param  exclude  	Entities to exclude from the call to reduce the payload. See https://developer.whereismytransport.com/documentation#excluding-data
     * @return      		A previously requested itinerary.
     */
    public Itinerary GetItinerary(String journeyId, String itineraryId, String exclude)
    {
    	if (journeyId == null || journeyId.isEmpty())
    	{
    		throw new IllegalArgumentException("JourneyId is required.");
    	}
    	
    	if (itineraryId == null || itineraryId.isEmpty())
    	{
    		throw new IllegalArgumentException("ItineraryId is required.");
    	}
    	
    	return TransportApiClientCalls.GetItinerary(tokenComponent, journeyId, itineraryId, exclude);
    }
    
    /**
     * Gets a list of all agencies in the system.
     *
     * @param  options  Options to limit the results by. Default: AgencyQueryOptions.Default()
     * @return      	A list of all agencies.
     */
    public List<Agency> GetAgencies(AgencyQueryOptions options)
    {
    	if (options == null)
    	{
    		options = AgencyQueryOptions.Default();
    	}
    	
    	return TransportApiClientCalls.GetAgencies(tokenComponent, options, null, null, null);
    }
    
    /**
     * Gets a list of agencies nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: AgencyQueryOptions.Default()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of agencies nearby the specified point.
     */
    public List<Agency> GetAgenciesNearby(AgencyQueryOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = AgencyQueryOptions.Default();
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
     * @param  options  	Options to limit the results by. Default: AgencyQueryOptions.Default()
     * @param  boundingBox 	The bounding box from where to retrieve agencies. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of agencies within a bounding box.
     */
    public List<Agency> GetAgenciesByBoundingBox(AgencyQueryOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = AgencyQueryOptions.Default();
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
    
    /**
     * Gets a specific agency.
     *
     * @param  agencyId  	The id of the agency you want to get.
     * @return      		An agency.
     */
    public Agency GetAgency(String agencyId)
    {
    	if (agencyId == null || agencyId.isEmpty())
    	{
    		throw new IllegalArgumentException("AgencyId is required.");
    	}
    	
    	return TransportApiClientCalls.GetAgency(tokenComponent, agencyId);
    }
    
    /**
     * Gets a list of all lines in the system.
     *
     * @param  options  Options to limit the results by. Default: LineQueryOptions.Default()
     * @return      	A list of all lines.
     */
    public List<Line> GetLines(LineQueryOptions options)
    {
    	if (options == null)
    	{
    		options = LineQueryOptions.Default();
    	}
    	
    	return TransportApiClientCalls.GetLines(tokenComponent, options, null, null, null);
    }
    
    /**
     * Gets a list of lines nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: LineQueryOptions.Default()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of lines nearby the specified point.
     */
    public List<Line> GetLinesNearby(LineQueryOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = LineQueryOptions.Default();
    	}
    	
    	if (radiusInMeters < 0)
    	{
    		throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers only.");
    	}
    	
    	return TransportApiClientCalls.GetLines(tokenComponent, options, new Point(longitude, latitude), radiusInMeters, null);
    }
    
    /**
     * Gets a list of all lines within a bounding box.
     *
     * @param  options  	Options to limit the results by. Default: LineQueryOptions.Default()
     * @param  boundingBox 	The bounding box from where to retrieve lines. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of lines within a bounding box.
     */
    public List<Line> GetLinesByBoundingBox(LineQueryOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = LineQueryOptions.Default();
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
    	
    	return TransportApiClientCalls.GetLines(tokenComponent, options, null, null, boundingBox);
    }
    
    /**
     * Gets a specific line.
     *
     * @param  lineId  	The id of the line you want to get.
     * @return      	An line.
     */
    public Line GetLine(String lineId)
    {
    	if (lineId == null || lineId.isEmpty())
    	{
    		throw new IllegalArgumentException("LineId is required.");
    	}
    	
    	return TransportApiClientCalls.GetLine(tokenComponent, lineId);
    }
    
    /**
     * Gets a list of all stops in the system.
     *
     * @param  options  Options to limit the results by. Default: StopQueryOptions.Default()
     * @return      	A list of all stops.
     */
    public List<Stop> GetStops(StopQueryOptions options)
    {
    	if (options == null)
    	{
    		options = StopQueryOptions.Default();
    	}
    	
    	return TransportApiClientCalls.GetStops(tokenComponent, options, null, null, null);
    }
    
    /**
     * Gets a list of stops nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: StopQueryOptions.Default()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of stops nearby the specified point.
     */
    public List<Stop> GetStopsNearby(StopQueryOptions options, double latitude, double longitude, int radiusInMeters)
    {
    	if (options == null)
    	{
    		options = StopQueryOptions.Default();
    	}
    	
    	if (radiusInMeters < 0)
    	{
    		throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers only.");
    	}
    	
    	return TransportApiClientCalls.GetStops(tokenComponent, options, new Point(longitude, latitude), radiusInMeters, null);
    }
    
    /**
     * Gets a list of all stops within a bounding box.
     *
     * @param  options  	Options to limit the results by. Default: StopQueryOptions.Default()
     * @param  boundingBox 	The bounding box from where to retrieve stop. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of stop within a bounding box.
     */
    public List<Stop> GetStopsByBoundingBox(StopQueryOptions options, String boundingBox)
    {
    	if (options == null)
    	{
    		options = StopQueryOptions.Default();
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
    	
    	return TransportApiClientCalls.GetStops(tokenComponent, options, null, null, boundingBox);
    }
    
    /**
     * Gets a specific stop.
     *
     * @param  stopId  	The id of the stop you want to get.
     * @return      	An stop.
     */
    public Stop GetStop(String stopId)
    {
    	if (stopId == null || stopId.isEmpty())
    	{
    		throw new IllegalArgumentException("StopId is required.");
    	}
    	
    	return TransportApiClientCalls.GetStop(tokenComponent, stopId);
    }
}
