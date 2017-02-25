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
     * @param  options  		Options to limit the results by. Default: JourneyOptions.Default()
     * @param  startLatitude	Latitude in decimal degrees to depart from.
     * @param  startLongitude	Longitude in decimal degrees to depart from.
     * @param  endLatitude		Latitude in decimal degrees of the desitnation.
     * @param  endLongitude		Longitude in decimal degrees of the desitnation.
     * @return      			A journey from A to B using public transport.
     */
    public Journey PostJourney(JourneyOptions options, double startLatitude, double startLongitude, double endLatitude, double endLongitude)
    {
    	if (options == null)
    	{
    		options = JourneyOptions.Default();
    	}
    	
    	return TransportApiClientCalls.PostJourney(tokenComponent, options, new Point(startLongitude, startLatitude), new Point(endLongitude, endLatitude));
    }
    
    
    /**
     * Gets a list of all agencies in the system.
     *
     * @param  options  Options to limit the results by. Default: AgencyOptions.Default()
     * @return      	A list of all agencies.
     */
    public List<Agency> GetAgencies(AgencyOptions options)
    {
    	if (options == null)
    	{
    		options = AgencyOptions.Default();
    	}
    	
    	return TransportApiClientCalls.GetAgencies(tokenComponent, options, null, null, null);
    }
    
    /**
     * Gets a list of agencies nearby ordered by distance from the point specified.
     *
     * @param  options  		Options to limit the results by. Default: AgencyOptions.Default()
     * @param  latitude			Latitude in decimal degrees.
     * @param  longitude		Longitude in decimal degrees.
     * @param  radiusInMeters	Radius in meters to filter results by.
     * @return      			A list of agencies nearby the specified point.
     */
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
     * @param  options  	Options to limit the results by. Default: AgencyOptions.Default()
     * @param  boundingBox 	The bounding box from where to retrieve agencies. See valid examples here: http://developer.whereismytransport.com/documentation#bounding-box.
     * @return      		A list of agencies within a bounding box.
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
}
