package transportapisdk;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import transportapisdk.models.Stop;

public class StopTests 
{

private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static String defaultBoundingBox = "-33.94,18.36,-33.89,18.43";
	private static double defaultLatitude = -33.986342;  
	private static double defaultLongitude = 18.468806;  
	private static int defaultRadiusInMeters = 5000;
	private static String defaultStopId = "S1twiBqUm0ul6ZMtCnfOcg";
	private static String defaultAgencyId = "A1JHSPIg_kWV5XRHIepCLw";
	
	private static List<String> defaultOnlyAgencies = new ArrayList<String>(Arrays.asList(defaultAgencyId));

	@Test
	public void Stops_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Stop>> stops = client.getStops(StopQueryOptions.defaultQueryOptions());
		
		assertTrue(stops.isSuccess);
		assertTrue(stops.data.size() > 0);
	}
	
	@Test
	public void StopsNearby_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Stop>> stopNearby = client.getStopsNearby(StopQueryOptions.defaultQueryOptions(), defaultLatitude, defaultLongitude, defaultRadiusInMeters);
		
		assertTrue(stopNearby.isSuccess);
		assertTrue(stopNearby.data.size() > 0);
	}
	
	@Test
	public void StopsByBoundingBox_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Stop>> stopNearby = client.getStopsByBoundingBox(StopQueryOptions.defaultQueryOptions(), defaultBoundingBox);
		
		assertTrue(stopNearby.isSuccess);
		assertTrue(stopNearby.data.size() > 0);
	}
	
	@Test
	public void Stop_DefaultValues_IsSuccess() {
		
		TransportApiResult<Stop> stop = client.getStop(defaultStopId);
		
		assertTrue(stop.isSuccess);
		assertTrue(stop.data.getId().equals(defaultStopId));
	}
	
	@Test
	public void Stops_OnlyAgency_IsSuccess() {
		
		StopQueryOptions options = new StopQueryOptions(
				defaultOnlyAgencies, 
				StopQueryOptions.defaultAgencies, 
				StopQueryOptions.defaultModes, 
				StopQueryOptions.defaultModes, 
				StopQueryOptions.defaultServesLines, 
				StopQueryOptions.defaultShowChildren, 
				StopQueryOptions.defaultLimit, 
				StopQueryOptions.defaultOffset, 
				StopQueryOptions.defaultExclude);
		
		TransportApiResult<List<Stop>> stops = client.getStops(options);
		
		assertTrue(stops.isSuccess);
		assertTrue(stops.data.size() > 0);
		for (Stop stop : stops.data) 
		{
			assertTrue(stop.getAgency().getId().equals(defaultAgencyId));
		} 
	}

}
